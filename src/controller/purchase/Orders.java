package controller.purchase;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.purchases.Purchase;
import model.users.User;
import technicalservices.persistence.DBManager;

public class Orders  extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("orders.jsp");
		User user = (User) req.getSession().getAttribute("user");
		
		ArrayList<Purchase> purchases = DBManager.getInstance().getPurchasesForUser(user.getId());
		if(purchases != null) {
			req.setAttribute("purchases", purchases);
		}

		if(purchases == null || purchases.isEmpty() == true)
			req.setAttribute("emptyOrders", true);
		else {
			req.setAttribute("emptyOrders", false);
		}
		
			
		
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
