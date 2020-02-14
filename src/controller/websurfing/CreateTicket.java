package controller.websurfing;

import model.users.User;
import technicalservices.persistence.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateTicket extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("createTicket.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("user") != null) {
            User actualUser = (User) req.getSession().getAttribute("user");
            int ticketId = DBManager.getInstance().insertTicket(actualUser);
            String message = req.getParameter("tickettext");
            if(message.equals(""))
                req.setAttribute("status", 3);
            else
                if (ticketId > 0)
                    if(DBManager.getInstance().insertTicketMessage(ticketId, message, true))
                        req.setAttribute("status", 1);
                    else
                        req.setAttribute("status", 2);
                else
                    req.setAttribute("status", 2);
        }

        RequestDispatcher rd = req.getRequestDispatcher("createTicket.jsp");
        rd.forward(req, resp);
    }
}
