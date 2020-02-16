package controller.websurfing;

import model.help.Ticket;
import model.users.Administrator;
import model.users.User;
import technicalservices.persistence.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class NewTickets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Administrator admin = (Administrator) req.getSession().getAttribute("administrator");
        if(admin != null){
            ArrayList<Ticket> tickets = DBManager.getInstance().getTicketsWithoutAdmin();
            req.setAttribute("tickets", tickets);
        }

        RequestDispatcher rd = req.getRequestDispatcher("newtickets.jsp");
        rd.forward(req, resp);
    }
}
