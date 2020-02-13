package controller.administration;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.products.Product;
import technicalservices.persistence.DBManager;

public class ModifyProduct extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("modifyProduct.jsp");

		String id = req.getParameter("id");
		
		Product clicked = DBManager.getInstance().getProduct(Integer.parseInt(id));
		req.getSession().setAttribute("product", clicked);
		
		
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		
		String description = req.getParameter("description");
		String path = req.getParameter("path");
		Float price = Float.parseFloat(req.getParameter("price"));
		
		
			Product p = (Product) req.getSession().getAttribute("product");
			if(p.getDescription().equals(description) && p.getImagePath().equals(path) && p.getPrice() == price)
			{
				req.setAttribute("notModified", true);
			}
			else
			{
				req.setAttribute("notModified", false);
				p.setPrice(price);
				p.setDescription(description);
				if(path != "")
					p.setImagePath(path);
				db.updateProduct(p);
			}
			
		req.setAttribute("product", p);
			
		RequestDispatcher rd = req.getRequestDispatcher("modifyProduct.jsp");
		rd.forward(req, resp);
	}
	
	
	
}
