package model.purchases;

import java.sql.Date;

public class Payment {

	private int id;
	private Date date;
	private float amount;
	private String transaction;
	
	public Payment() {}
	public Payment(int id, Date date, float amount, String transaction) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.transaction = transaction;
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
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	
	
	
}
