package technicalservices.persistence.dao;

import java.util.ArrayList;

import model.products.Product;

public interface CartDao {

	public void insert(int idUser, int idProduct, int quantity);
	public void delete(int idUser, int idProduct);
	public void deleteAll(int idUser);
	public void updateQuantity(int idUser, int idProduct, int quantity);
	public ArrayList<Product> getCartProducts(int idUser);
	public boolean isInCart(int idUser, int idProduct);
}
