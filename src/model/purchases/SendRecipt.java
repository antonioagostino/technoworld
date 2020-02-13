package model.purchases;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.users.User;
import technicalservices.MailUtility;
import technicalservices.persistence.DBManager;

public class SendRecipt extends HttpServlet{
	
	private static final long serialVersionUID = -5774151176648784274L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int purchaseId;
		
		try {
			purchaseId = Integer.parseInt(req.getParameter("purchase"));
		} catch (NumberFormatException e) {
			purchaseId = -1;
		}
		
		if(purchaseId != -1) {
			Purchase p = DBManager.getInstance().getPurchaseById(purchaseId);
			String imagePath = getServletContext().getRealPath("/img/logo.png");
            try {
				MailUtility.sendRecipt(p, DBManager.getInstance().getEmailByUserId(p.getIdUser()),imagePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		User user = (User) req.getSession().getAttribute("user");
		ArrayList<Purchase> purchases = DBManager.getInstance().getPurchasesForUser(user.getId());
		if(purchases != null) {
			req.setAttribute("purchases", purchases);
		}

		if(purchases == null || purchases.isEmpty() == true)
			req.setAttribute("emptyOrders", true);
		else
			req.setAttribute("emptyOrders", false);
		
		
		RequestDispatcher rd = req.getRequestDispatcher("orders.jsp");
		rd.forward(req, resp);	
	}


}
