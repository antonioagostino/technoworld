package model.products;

import java.util.ArrayList;

public class ProductQuantity {
	
	private Product product;
	private int quantity;
	
	public ProductQuantity() {}
	public ProductQuantity(Product p, int q) {
		this.product = p;
		this.quantity = q;
	}
	public ProductQuantity(Product p) {
		this.product = p;
		this.quantity = 1;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
