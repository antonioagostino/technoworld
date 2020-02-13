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

public class Ticket extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int ticketId;
        User user = (User) req.getSession().getAttribute("user");
        Administrator admin = (Administrator) req.getSession().getAttribute("administrator");

        if(user != null || admin != null) {

            try {
                ticketId = Integer.parseInt(req.getParameter("id"));
            } catch (NumberFormatException e) {
                ticketId = 0;
            }

            if (ticketId > 0) {
                model.help.Ticket ticket = DBManager.getInstance().getTicket(ticketId);
                if(ticket == null){
                    req.setAttribute("wrongticket", true);
                } else {
                    if (user != null) {
                        if (user.getId() != ticket.getUser().getId()) {
                            req.setAttribute("permissions", false);
                        } else {
                            req.setAttribute("ticket", ticket);
                        }
                    } else {
                        if (admin.getId().equals(ticket.getAdmin().getId()) || ticket.getAdmin().getId() == null) {
                            req.setAttribute("ticket", ticket);
                        } else {
                            req.setAttribute("differentadmin", true);
                        }
                    }
                }
            } else {
                req.setAttribute("wrongticket", true);
            }
        } else {
            req.setAttribute("permissions", false);
        }

        RequestDispatcher rd = req.getRequestDispatcher("ticket.jsp");
        rd.forward(req, resp);
    }
}
