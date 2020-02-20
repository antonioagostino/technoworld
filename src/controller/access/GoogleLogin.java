package controller.access;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.products.Product;
import model.users.User;
import technicalservices.MailUtility;
import technicalservices.persistence.DBManager;

public class GoogleLogin extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		
		
		DBManager db = DBManager.getInstance();
		User user = db.getUserByEmail(email);
		
		if(user == null) {
			
			user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setSurname(surname);
			
			int index = email.indexOf('@');
			
			String username1 = email.substring(0,index);
			String username = username1;
			int actual = 1;
			while(db.getUserByUsername(username) != null) {
				username = username1 + actual;
				actual ++;
			}
			user.setUsername(username);
			db.registerGoogleUser(user);
			try {
				MailUtility.sendRegistrationMail(email, username, null);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
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

}
