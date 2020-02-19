package controller.administration;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.purchases.Purchase;
import model.purchases.Store;
import technicalservices.MailUtility;
import technicalservices.persistence.DBManager;

public class MyStore extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String admin = null;
		admin = (String) req.getSession().getAttribute("storeAdmin");
		ArrayList<Purchase> purchases = null;
		Store store = null;
		if(admin != null) {
			store = DBManager.getInstance().getStoreByAdmin(admin);
			purchases = DBManager.getInstance().getPurchaseForStore(store.getId());
			if(purchases.isEmpty())
				req.setAttribute("emptyOrders", true);
			else
				req.setAttribute("emptyOrders", false);

			req.setAttribute("purchases", purchases);
			req.setAttribute("store", store);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("myStore.jsp");
			requestDispatcher.forward(req, resp);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int orderId = Integer.parseInt(req.getParameter("orderId"));
		int orderStatus = Integer.parseInt(req.getParameter("orderStaus"));
		
		DBManager.getInstance().getPurchaseDao().updateStatus(orderStatus, orderId);
		Purchase p = DBManager.getInstance().getPurchaseById(orderId);
		
		try {
			MailUtility.notifyChangeStatus(p, DBManager.getInstance().getEmailByUserId(p.getIdUser()));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		resp.getOutputStream().print(orderStatus);
	}
}
