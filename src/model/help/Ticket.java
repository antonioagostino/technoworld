package model.help;

import model.users.Administrator;
import model.users.User;

import java.sql.Date;
import java.util.ArrayList;

public abstract class Ticket {
    private int id;
    private Date date;
    private User user;
    private int status;
    private Administrator admin;
    protected ArrayList<TicketMessage> messages;

    public Ticket(int id, Date date, User user, int status, Administrator admin) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.status = status;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public void setMessages(ArrayList<TicketMessage> messages) {
        this.messages = messages;
    }

    public abstract ArrayList<TicketMessage> getMessages();
}
