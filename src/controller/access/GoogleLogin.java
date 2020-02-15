package controller.access;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.products.Product;
import model.users.User;
import technicalservices.persistence.DBManager;

public class GoogleLogin extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		
		
		DBManager db = DBManager.getInstance();
		RequestDispatcher rd = null;
		User user = db.getUserByEmail(email);
		
		if(user != null) {
			req.getSession().setAttribute("user", user);
			req.getSession().setAttribute("firstLogin", true);
			
			ArrayList<Product> cart = (ArrayList<Product>) req.getSession().getAttribute("cartArray");
			
			if(cart != null) {
				for(Product p : cart) {
					if(!DBManager.getInstance().isInCart(user.getId(), p.getId()))
						DBManager.getInstance().insertIntoCart(user.getId(), p.getId(), p.getOrderQuantity());
				}
			}
			
			resp.getOutputStream().print(1);
		}
		else {
			//regitrare
		}
	
	
		
	}
}
