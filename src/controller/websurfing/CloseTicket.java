package controller.websurfing;

import model.users.Administrator;
import model.users.User;
import technicalservices.persistence.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CloseTicket extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int ticketId;
        Administrator admin = (Administrator) req.getSession().getAttribute("administrator");

        try {
            ticketId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            ticketId = 0;
        }

        if (ticketId > 0 && admin != null) {
            model.help.Ticket ticket = DBManager.getInstance().getTicket(ticketId);
            if (admin.getId().equals(ticket.getAdmin().getId())) {
                if(DBManager.getInstance().closeTicket(ticketId))
                    req.setAttribute("closed", true);

            }

            req.setAttribute("ticket", ticket);
        }

        RequestDispatcher rd = req.getRequestDispatcher("ticket.jsp");
        rd.forward(req, resp);
    }
}
