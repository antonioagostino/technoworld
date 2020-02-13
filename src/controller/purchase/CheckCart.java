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

public class CheckCart extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idProduct = req.getParameter("idProduct");
		String product = null;
		
		User user = (User) req.getSession().getAttribute("user");
		
		if(user != null) {
			boolean inCart = DBManager.getInstance().isInCart(user.getId(), Integer.parseInt(idProduct));
			if(inCart) 
				product = "inCart";
		}
		
		else {
			ArrayList<Product> cart = (ArrayList<Product>) req.getSession().getAttribute("cartArray");
			if(cart != null) {
				for(Product p : cart) {
					if(p.getId() == Integer.parseInt(idProduct))
						product = "inCart";
				}
			}
		}
		
		resp.getOutputStream().print(""+product);
	}
}
