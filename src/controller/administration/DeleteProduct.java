package controller.administration;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import technicalservices.persistence.DBManager;

public class DeleteProduct  extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		
		int productID = Integer.parseInt(req.getParameter("productId"));
		
		db.deleteProduct(productID);
		RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
		rd.forward(req, resp);
	}
}
