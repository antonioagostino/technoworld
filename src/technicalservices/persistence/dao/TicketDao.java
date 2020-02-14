package technicalservices.persistence.dao;

import model.help.Ticket;
import model.help.TicketMessage;
import model.users.Administrator;
import model.users.User;

import java.util.ArrayList;

public interface TicketDao {
    int insertTicket(User user);
    boolean insertTicketMessage(int ticketId, String message, boolean sender);
    ArrayList<Ticket> getTicketsForUser(User user);
    Ticket getTicket(int ticketId);
    ArrayList<TicketMessage> getTicketMessages(int ticketId);
    boolean setTicketAdmin(int ticketId, Administrator admin);
    boolean closeTicket(int ticketId);
}
