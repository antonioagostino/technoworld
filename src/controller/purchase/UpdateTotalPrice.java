package controller.purchase;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.products.Product;
import model.users.User;
import technicalservices.persistence.DBManager;

public class UpdateTotalPrice extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User) req.getSession().getAttribute("user");
		
		float totalPrice = 0;

		if(user != null)
			totalPrice = DBManager.getInstance().getTotalPrice(DBManager.getInstance().getCartProducts(user.getId()));
		
		else {
			ArrayList<Product> cart = (ArrayList<Product>) req.getSession().getAttribute("cartArray");
			for(Product p : cart) {
				totalPrice += p.getPricePerQuantity();
			}
		}
		
		resp.getOutputStream().print(""+totalPrice);
	}
}
