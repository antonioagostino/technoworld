package controller.websurfing;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import technicalservices.persistence.DBManager;

public class Map extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("map.jsp");
		
		ArrayList<String> coords = DBManager.getInstance().getMapCoords();
		req.getSession().setAttribute("coords", coords);
		
		rd.forward(req, resp);
	}
}
