package technicalservices.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.products.Product;
import model.products.ProductProxy;
import technicalservices.persistence.DataSource;
import technicalservices.persistence.dao.CartDao;

public class CartDaoJDBC implements CartDao {

	private DataSource dataSource;
	private Connection connection;
	
	public CartDaoJDBC(DataSource dataS) {
		dataSource = dataS;
		connection = null;
	}
	
	@Override
	public void insert(int idUser, int idProduct, int quantity) {
		try {
			connection = dataSource.getConnection();
			
			String query = "insert into cart(\"idUser\", \"idProduct\", quantity) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idUser);
			statement.setInt(2, idProduct);
			statement.setInt(3, quantity);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(int idUser, int idProduct) {
		try {
			connection = dataSource.getConnection();
			
			String query = "delete from cart where \"idUser\" = ? and \"idProduct\" = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idUser);
			statement.setInt(2, idProduct);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void deleteAll(int idUser) {
		try {
			connection = dataSource.getConnection();
			
			String query = "delete from cart where \"idUser\" = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idUser);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void updateQuantity(int idUser, int idProduct, int quantity) {
		try {
			connection = dataSource.getConnection();
			String query = "update cart set quantity = ? where \"idUser\" = ? and \"idProduct\" = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, quantity);
			statement.setInt(2, idUser);
			statement.setInt(3, idProduct);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean isInCart(int idUser, int idProduct) {
		boolean inCart = false;
			
		try {
			connection = dataSource.getConnection();
			
			String query = "select * from cart where \"idUser\" = ? and \"idProduct\" = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idUser);
			statement.setInt(2, idProduct);
			
			ResultSet result = statement.executeQuery();
			if(result.next())
				inCart = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return inCart;
	}

	@Override
	public ArrayList<Product> getCartProducts(int idUser) {
		ArrayList<Product> cart = new ArrayList<Product>();
			
		try {
			connection = dataSource.getConnection();
			
			String query = "select \"idProduct\", quantity from cart where \"idUser\" = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idUser);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				
				String query2 = "select * from product where id = ?";
				PreparedStatement statement2 = connection.prepareStatement(query2);
				statement2.setInt(1, result.getInt(1));
				
				ResultSet result2 = statement2.executeQuery();
				if(result2.next()) {
					
					Product p = new ProductProxy();
					p.setId(result.getInt(1));
					p.setModel(result2.getString(2));
					p.setManufacturer(result2.getString(3));
					p.setPrice(result2.getFloat(4));
					p.setSpecs(result2.getString(5));
					p.setDescription(result2.getString(6));
					p.setImagePath(result2.getString(8));
					p.setOrderQuantity(result.getInt(2));
					p.setPricePerQuantity(p.getPrice()*p.getOrderQuantity());
					cart.add(p);
				}
				
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cart;
	}
}
