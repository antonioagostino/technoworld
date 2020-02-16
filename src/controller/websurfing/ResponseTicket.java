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

public class ResponseTicket extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ticketId;

        try {
            ticketId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e){
            ticketId = 0;
        }

        String message = req.getParameter("tickettext");

        if(message == null || message.equals("")){
            req.setAttribute("status", 3);
        } else {

            if (ticketId > 0) {
                User actualUser = (User) req.getSession().getAttribute("user");
                Administrator administrator = (Administrator) req.getSession().getAttribute("administrator");
                boolean sender = true;
                if (actualUser != null || administrator != null) {
                    if (actualUser == null)
                        sender = false;

                    if (DBManager.getInstance().insertTicketMessage(ticketId, message, sender)) {
                        if((req.getParameter("aid") == null || req.getParameter("aid").equals("")) &&
                            !sender) {
                            if (DBManager.getInstance().setTicketAdmin(ticketId, administrator))
                                req.setAttribute("status", 1);
                            else
                                req.setAttribute("status", 2);
                        } else {
                            req.setAttribute("status", 1);
                        }
                    } else {
                        req.setAttribute("status", 2);
                    }

                    Ticket ticket = DBManager.getInstance().getTicket(ticketId);
                    req.setAttribute("ticket", ticket);
                }
            }
        }

        RequestDispatcher rd = req.getRequestDispatcher("ticket.jsp");
        rd.forward(req, resp);
    }
}
