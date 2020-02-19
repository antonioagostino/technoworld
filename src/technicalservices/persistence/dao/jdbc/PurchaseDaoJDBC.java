package technicalservices.persistence.dao.jdbc;

import model.products.Category;
import model.products.Manufacturer;
import model.products.Product;
import model.products.ProductProxy;
import model.products.ProductQuantity;
import model.products.Review;
import model.purchases.Payment;
import model.purchases.Purchase;
import model.purchases.Store;
import technicalservices.persistence.DBManager;
import technicalservices.persistence.DataSource;
import technicalservices.persistence.dao.PurchaseDao;

import java.sql.*;
import java.util.ArrayList;

public class PurchaseDaoJDBC implements PurchaseDao {

    private DataSource dataSource;
    private Connection connection;

    public PurchaseDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public ArrayList<Purchase> getPurchasesForUser(int idUser) {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        try {
            connection = dataSource.getConnection();
            String query = "select * from purchase, \"purchaseProducts\",payment,product where product.id = \"purchaseProducts\".product and purchase.id = \"purchaseProducts\".purchase and payment.id=purchase.payment and purchase.user=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet result = statement.executeQuery();
            int previous = -1;
            Purchase purchase = null;
            while (result.next()) {
            	if(result.getInt(1) == previous) {
            		Product p1 = new ProductProxy();
                    p1.setModel(result.getString("model"));
                    p1.setManufacturer(result.getString("manufacturer"));
                    p1.setPrice(result.getFloat("price"));
                    p1.setId(result.getInt("product"));
                    p1.setImagePath(result.getString("image"));
                    purchase.getProducts().add(new ProductQuantity(p1,result.getInt("quantity")));
            	}
            	else {
            		if(purchase != null)
            			purchases.add(purchase);
            		purchase = new Purchase();
                    purchase.setId(result.getInt(1));
                    purchase.setDate(result.getDate("date"));
                    purchase.setShipment(result.getString("shipment"));
                    Payment payment = new Payment();
                    payment.setTransaction(result.getString("transactionCode"));
                    payment.setAmount(result.getFloat("amount"));
                    purchase.setPayment(payment);
                    
                    ArrayList<ProductQuantity> products = new ArrayList<>();
                    Product p1 = new ProductProxy();
                    p1.setModel(result.getString("model"));
                    p1.setManufacturer(result.getString("manufacturer"));
                    p1.setPrice(result.getFloat("price"));
                    p1.setId(result.getInt("product"));
                    p1.setImagePath(result.getString("image"));
                    products.add(new ProductQuantity(p1, result.getInt("quantity")));
                    purchase.setProducts(products);
            	}
                previous = result.getInt(1);
            }
            if(purchase!=null)
            	purchases.add(purchase);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return purchases;
    }

    @Override
    public boolean insertPayment(float amount, String transactionCode) {

        try {
            connection = dataSource.getConnection();
            String query = "insert into payment(\"date\", \"amount\", \"transactionCode\") values(now(), ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, amount);
            statement.setString(2, transactionCode);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean insertPurchase(int userId, int paymentId, String shipmentMode) {
        try {
            connection = dataSource.getConnection();
            String query = "insert into purchase(\"date\", \"user\", \"payment\", \"shipment\") values(now(), ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, paymentId);
            statement.setString(3, shipmentMode);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Payment findPayment(String transactionCode) {
        Payment payment = null;
        try {
            connection = dataSource.getConnection();
            String query = "select * from payment where \"transactionCode\" = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, transactionCode);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                payment = new Payment(result.getInt("id"), result.getDate("date"),
                    result.getFloat("amount"), transactionCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return payment;
    }

    @Override
    public Purchase findPurchase(Payment payment) {
        Purchase purchase = null;
        try {
            connection = dataSource.getConnection();
            String query = "select * from purchase, \"user\" where purchase.payment = ? and purchase.\"user\" = \"user\".id";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, payment.getId());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                purchase = new Purchase();
                purchase.setDate(result.getDate("date"));
                purchase.setId(result.getInt("id"));
                purchase.setUser(result.getString("username"));
                purchase.setPayment(payment);
                purchase.setShipment(result.getString("shipment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return purchase;
    }

    @Override
    public boolean insertPurchaseProductAssociation(int quantity, int productId, int purchaseId) {
        try {
            connection = dataSource.getConnection();
            String query = "insert into \"purchaseProducts\"(\"quantity\", \"product\", \"purchase\") values(?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, quantity);
            statement.setInt(2, productId);
            statement.setInt(3, purchaseId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public Purchase getPurchaseById(int id) {
		Purchase purchase = null;
		try {
            connection = dataSource.getConnection();
            String query = "select * \n" + 
            		"from \"map\", \"purchase\",\"user\", \"purchaseProducts\", payment, product  \n" + 
            		"where 	\"purchase\".\"storeId\" = \"map\".\"idStore\" and product.id = \"purchaseProducts\".product and \n" + 
            		"		purchase.id = \"purchaseProducts\".purchase and payment.id=purchase.payment and purchase.user=\"user\".\"id\" \n" + 
            		"		and \"purchase\".\"storeId\" = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            		if(purchase == null) { 
            			purchase = new Purchase();
            			purchase.setId(id);
                        purchase.setDate(result.getDate("date"));
                        purchase.setShipment(result.getString("shipment"));
                        Payment payment = new Payment();
                        payment.setTransaction(result.getString("transactionCode"));
                        payment.setAmount(result.getFloat("amount"));
                        purchase.setPayment(payment);
                        purchase.setProducts(new ArrayList<ProductQuantity>());
                        purchase.setIdUser(result.getInt("user"));
                        purchase.setUser(result.getString("username"));
            		}
                    
            		Product p1 = new ProductProxy();
                    p1.setModel(result.getString("model"));
                    p1.setManufacturer(result.getString("manufacturer"));
                    p1.setPrice(result.getFloat("price"));
                    p1.setId(result.getInt("product"));
                    p1.setImagePath(result.getString("image"));
                    purchase.getProducts().add(new ProductQuantity(p1,result.getInt("quantity")));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return purchase;
	}

	@Override
	public ArrayList<Store> getAllStore() {
		ArrayList<Store> allStore = null;
		try {
            connection = dataSource.getConnection();
            String query = "select \"idStore\", \"name\" from map";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
            	if(allStore == null) 
            		allStore = new ArrayList<Store>();
            	Store s = new Store(result.getInt("idStore"), result.getString("name"));
            	allStore.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		return allStore;
	}

	@Override
	public Store getStoreByAdmin(String admin) {
		Store store = null;
		try {
            connection = dataSource.getConnection();
            String query = "select * from \"map\", \"storeAdmin\" where \"storeAdmin\".\"storeId\" = \"map\".\"idStore\" and \"storeAdmin\".\"id\" = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, admin);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
            	Store s = new Store(result.getInt("idStore"), result.getString("name"));
            	s.setCoordinates(result.getString("position"));
            }            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		return store;
	}

	@Override
	public ArrayList<Purchase> getPurchaseForStore(int storeId) {
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		try {
            connection = dataSource.getConnection();
            String query = "select * from \"map\", \"purchase\" where \"purchase\".\"storeId\" = \"map\".\"idStore\" and \"purchase\".\"storeId\" = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, storeId);
            ResultSet result = statement.executeQuery();
            int previous = -1;
            Purchase purchase = null;
            while (result.next()) {
            	if(result.getInt(1) == previous) {
            		Product p1 = new ProductProxy();
                    p1.setModel(result.getString("model"));
                    p1.setManufacturer(result.getString("manufacturer"));
                    p1.setPrice(result.getFloat("price"));
                    p1.setId(result.getInt("product"));
                    p1.setImagePath(result.getString("image"));
                    purchase.getProducts().add(new ProductQuantity(p1,result.getInt("quantity")));
            	}
            	else {
            		if(purchase != null)
            			purchases.add(purchase);
            		purchase = new Purchase();
                    purchase.setId(result.getInt(1));
                    purchase.setDate(result.getDate("date"));
                    purchase.setShipment(result.getString("shipment"));
                    Payment payment = new Payment();
                    payment.setTransaction(result.getString("transactionCode"));
                    payment.setAmount(result.getFloat("amount"));
                    purchase.setPayment(payment);
                    
                    ArrayList<ProductQuantity> products = new ArrayList<>();
                    Product p1 = new ProductProxy();
                    p1.setModel(result.getString("model"));
                    p1.setManufacturer(result.getString("manufacturer"));
                    p1.setPrice(result.getFloat("price"));
                    p1.setId(result.getInt("product"));
                    p1.setImagePath(result.getString("image"));
                    products.add(new ProductQuantity(p1, result.getInt("quantity")));
                    purchase.setProducts(products);
            	}
                previous = result.getInt(1);
            }
            if(purchase!=null)
            	purchases.add(purchase);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		return purchases;
	}
	
	@Override
	public void updateStatus(int orderStatus, int orderId) {
		try {
            connection = dataSource.getConnection();
           	String query = "UPDATE purchase SET status = ? WHERE id = ?";
           	PreparedStatement stat = connection.prepareStatement(query);
           	stat.setInt(1, orderStatus);
           	stat.setInt(2, orderId);
           	stat.executeUpdate();
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
	
	
	
}
