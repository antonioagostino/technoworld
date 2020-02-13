package controller.administration;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.products.Category;
import model.products.Product;
import model.products.ProductProxy;
import technicalservices.persistence.DBManager;

public class InsertProduct  extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DBManager db = DBManager.getInstance();
		
		String category = req.getParameter("category");
		String model = req.getParameter("model");
		String manufacturer = req.getParameter("manufacturer");
		String specifics = req.getParameter("specifics");
		String description = req.getParameter("description");
		String path = req.getParameter("path");
		Float price = Float.parseFloat(req.getParameter("price"));
		
		boolean alreadyExists = false;
		alreadyExists = db.existsProduct(model, manufacturer);
		req.setAttribute("exists", alreadyExists);
		
		
			
		if(!alreadyExists)
		{
			Product p = new ProductProxy();
			
			p.setId(0);
			p.setModel(model);
			p.setManufacturer(manufacturer);
			p.setPrice(price);
			p.setSpecs(specifics);
			p.setDescription(description);
			p.setCategory(new Category(category));
			p.setStarsAvg(0);
			p.setImagePath(path);
			db.insertProduct(p);
		}

		RequestDispatcher rd = req.getRequestDispatcher("insertProduct.jsp");
		rd.forward(req, resp);
	} 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("insertProduct.jsp");
		requestDispatcher.forward(req, resp);
	}
}
