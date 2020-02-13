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

public class RemoveCartProduct extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User) req.getSession().getAttribute("user");
		int idProduct = Integer.parseInt(req.getParameter("idProduct"));
		
		if(user == null) {
			ArrayList<Product> cart = (ArrayList<Product>) req.getSession().getAttribute("cartArray");
			for(int i=0; i<cart.size(); i++) {
				if(cart.get(i).getId() == idProduct) {
					cart.remove(i);
					break;
				}
			}
		}
		
		else {
			DBManager.getInstance().deleteCartProduct(user.getId(), idProduct);
		}
	}
}
