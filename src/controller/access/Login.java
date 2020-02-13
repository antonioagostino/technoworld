package controller.access;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.products.Product;
import model.users.Administrator;
import model.users.User;
import technicalservices.persistence.DBManager;

public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		
		String isAdmin = req.getParameter("admin");
		
		if(isAdmin != null && isAdmin.equals("true")) {
			req.getSession().setAttribute("adminNotAuthenticated", true);
			rd = req.getRequestDispatcher("login.jsp");
		}
		
		else if(isAdmin != null && isAdmin.equals("false")) {
			req.getSession().removeAttribute("adminNotAuthenticated");
		}
		
		String logout = req.getParameter("logout");
		Object o1 = req.getSession().getAttribute("user");
		Object o2 = req.getSession().getAttribute("administrator");
		
		if(logout != null && logout.equals("true")) {
			
			if(o1 != null)
				req.getSession().removeAttribute("user");
			else if(o2 != null) 
				req.getSession().removeAttribute("administrator");
			
			rd = req.getRequestDispatcher("home.jsp");
		}
		
		else {
			if(o1 == null && o2 == null) {
				rd = req.getRequestDispatcher("login.jsp");
			}
		}
		
		rd.forward(req, resp);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DBManager db = DBManager.getInstance();
		
		RequestDispatcher rd = null;
		String password = req.getParameter("password");
		
		String isAdmin = req.getParameter("admin");
		
		if(isAdmin != null && isAdmin.equals("true")) {
			String id = req.getParameter("id");
			Administrator admin = db.getAdministrator(id, password);
			if(admin != null) {
				req.getSession().removeAttribute("adminNotAuthenticated");
				req.getSession().setAttribute("administrator", admin);
				req.getSession().setAttribute("firstLogin", true);
				rd = req.getRequestDispatcher("home.jsp");
			}
			else {
				req.setAttribute("loginError", true);
				rd = req.getRequestDispatcher("login.jsp");
			}
		}
		
		else if(isAdmin == null) {
			String username = req.getParameter("username");
			
			User user = db.getUser(username, password);
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
				
				rd = req.getRequestDispatcher("home.jsp");
			}
			else {
				req.setAttribute("loginError", true);
				rd = req.getRequestDispatcher("login.jsp");
			}
		}
		
		rd.forward(req, resp);
	}
}
