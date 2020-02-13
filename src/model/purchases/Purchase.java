package model.purchases;

import java.sql.Date;
import java.util.ArrayList;

import javafx.util.Pair;
import model.products.ProductQuantity;

public class Purchase {

	private int id;
	private Date date;
	private Payment payment;
	private int idUser;
	private String user;
	private String shipment;
	private ArrayList<ProductQuantity> products;
	
	public Purchase() {}
	
	public Purchase(int id, Date date, Payment payment, String user, String shipment, ArrayList<ProductQuantity> products) {
		this.id = id;
		this.date = date;
		this.payment = payment;
		this.user = user;
		this.shipment = shipment;
		this.setProducts(products);
	}

	

	public Purchase(int id, Date date, Payment payment, int idUser, String shipment,
			ArrayList<ProductQuantity> products) {
		this.id = id;
		this.date = date;
		this.payment = payment;
		this.idUser = idUser;
		this.shipment = shipment;
		this.products = products;
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

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getShipment() {
		return shipment;
	}

	public void setShipment(String shipment) {
		this.shipment = shipment;
	}

	public ArrayList<ProductQuantity> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<ProductQuantity> products) {
		this.products = products;
	}
	
	
	
	
	
}
