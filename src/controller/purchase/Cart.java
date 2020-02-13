package controller.purchase;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.products.Product;
import model.users.User;
import technicalservices.persistence.DBManager;

public class Cart extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		RequestDispatcher rd = req.getRequestDispatcher("cart.jsp");
		
		User user = (User) req.getSession().getAttribute("user");
		ArrayList<Product> cart = null;
		
		double totalPrice = 0;

		if(user != null) {
			cart = DBManager.getInstance().getCartProducts(user.getId());
			totalPrice = DBManager.getInstance().getTotalPrice(cart);
		}
		
		else {
			
			cart = (ArrayList<Product>) req.getSession().getAttribute("cartArray");
			if(cart != null) {
				for(Product p : cart) {
					p.setPricePerQuantity(p.getPrice()*p.getOrderQuantity());
					totalPrice += p.getPricePerQuantity();
				}
			}
		}
		
		if((cart != null && cart.isEmpty()) || cart == null) {
			req.getSession().setAttribute("emptyCart", true);
		}
		
		else if(cart != null && !cart.isEmpty()){
			req.getSession().setAttribute("emptyCart", false);
		}
		
		req.getSession().setAttribute("cartProducts", cart);
		req.getSession().setAttribute("totalPrice", totalPrice);
		
		rd.forward(req, resp);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idProduct = req.getParameter("idProduct");
		String quantity = req.getParameter("quantity");
		
		ArrayList<Product> cart = (ArrayList<Product>) req.getSession().getAttribute("cartArray");
		if(cart == null)
			cart = new ArrayList<Product>();
		
		Product p = DBManager.getInstance().getProduct(Integer.parseInt(idProduct));
		p.setOrderQuantity(Integer.parseInt(quantity));
		
		cart.add(p);
		req.getSession().setAttribute("cartArray", cart);
		
		User user = (User) req.getSession().getAttribute("user");
		if(user != null) {
			DBManager.getInstance().insertIntoCart(user.getId(), Integer.parseInt(idProduct), Integer.parseInt(quantity));
		}
	}
}
