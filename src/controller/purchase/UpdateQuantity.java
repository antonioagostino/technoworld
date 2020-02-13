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

public class UpdateQuantity extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User) req.getSession().getAttribute("user");
		int idProduct = Integer.parseInt(req.getParameter("idProduct"));
		String newQuantity = req.getParameter("newQuantity");
	
		float newPrice = 0;
		float totalPrice = 0;
		
		if(user != null) {
			DBManager.getInstance().updateQuantity(user.getId(), idProduct, Integer.parseInt(newQuantity));
			newPrice = DBManager.getInstance().getProduct(idProduct).getPrice()*Integer.parseInt(newQuantity);
			totalPrice = DBManager.getInstance().getTotalPrice(DBManager.getInstance().getCartProducts(user.getId()));
		}
		
		else {
			ArrayList<Product> cart = (ArrayList<Product>) req.getSession().getAttribute("cartArray");
			for(Product p : cart) {
				if(p.getId() == idProduct) {
					p.setOrderQuantity(Integer.parseInt(newQuantity));
					p.setPricePerQuantity(p.getPrice()*Integer.parseInt(newQuantity));
					newPrice = p.getPricePerQuantity();
				}
				totalPrice += p.getPricePerQuantity();
			}
		}
		
		resp.getOutputStream().print(""+newPrice+" ");
		resp.getOutputStream().print(""+totalPrice);
	}
}
