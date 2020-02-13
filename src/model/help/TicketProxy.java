package model.help;

import model.users.Administrator;
import model.users.User;
import technicalservices.persistence.DBManager;

import java.sql.Date;
import java.util.ArrayList;

public class TicketProxy extends Ticket {

    public TicketProxy(int id, Date date, User user, int status, Administrator admin) {
        super(id, date, user, status, admin);
    }

    @Override
    public ArrayList<TicketMessage> getMessages() {
        if(super.messages == null)
            setMessages(DBManager.getInstance().getTicketMessages(super.getId()));

        return super.messages;
    }


}
