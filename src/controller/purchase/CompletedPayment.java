package controller.purchase;
import com.google.gson.Gson;
import model.products.ProductQuantity;
import model.purchases.PayPalOrder;
import model.purchases.Payment;
import model.purchases.Purchase;
import technicalservices.GetOrder;
import technicalservices.MailUtility;
import technicalservices.persistence.DBManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.ArrayList;

public class CompletedPayment extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

        StringBuffer input = new StringBuffer();
        boolean completed = false;

        try {
            BufferedReader bufferedReader = request.getReader();
            String line = bufferedReader.readLine();
            while (line != null){
                input.append(line);
                line = bufferedReader.readLine();
            }

            PayPalOrder payPalOrder = gson.fromJson(input.toString(), PayPalOrder.class);
            GetOrder getOrder = new GetOrder();
            String paymentId = getOrder.getOrder(payPalOrder.getOrderID());
            if(!paymentId.equals("-1")){
                if(DBManager.getInstance().insertPayment(payPalOrder.getAmount(), paymentId)){
                    Payment payment = DBManager.getInstance().getPayment(paymentId);
                    if(DBManager.getInstance().insertPurchase(payPalOrder.getUserID(), payment.getId(),
                            payPalOrder.getAddress())){
                        Purchase purchase = DBManager.getInstance().getPurchase(payment);
                        int[] quantities = payPalOrder.getProductsQuantity();
                        int[] products = payPalOrder.getProducts();
                        ArrayList<ProductQuantity> productQuantities = new ArrayList<>();
                        for (int i = 0; i < products.length; i++) {
                            productQuantities.add(new ProductQuantity(DBManager.getInstance().getProduct(products[i]),
                                    quantities[i]));
                            DBManager.getInstance().insertPurchaseProductAssociation(quantities[i], products[i], purchase.getId());
                        }

                        purchase.setProducts(productQuantities);
                        DBManager.getInstance().deleteAllCartProducts(payPalOrder.getUserID());
                        String imagePath = getServletContext().getRealPath("/img/logo.png");
                        MailUtility.sendRecipt(purchase, DBManager.getInstance().getUserByUsername(purchase.getUser()).getEmail(),
                                imagePath);
                        completed = true;
                    }
                }
            }

            if(completed)
                response.getOutputStream().print(1);
            else
                response.getOutputStream().print(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
