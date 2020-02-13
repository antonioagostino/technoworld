package model.help;

import java.sql.Date;

public class TicketMessage {
    private int id;
    private Date date;
    private String message;
    private boolean sender;

    public TicketMessage(int id, Date date, String message, boolean sender) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.sender = sender;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSender() {
        return sender;
    }

    public void setSender(boolean sender) {
        this.sender = sender;
    }
}
