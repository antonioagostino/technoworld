package controller.websurfing;

import model.help.Ticket;
import model.users.User;
import technicalservices.persistence.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MyTickets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User actualUser = (User) req.getSession().getAttribute("user");
        if(actualUser != null){
            ArrayList<Ticket> tickets = DBManager.getInstance().getTicketsForUser(actualUser);
            req.setAttribute("tickets", tickets);
        }

        RequestDispatcher rd = req.getRequestDispatcher("myTickets.jsp");
        rd.forward(req, resp);
    }
}
