package technicalservices.persistence;

import java.util.ArrayList;

import model.help.Ticket;
import model.help.TicketMessage;
import model.products.Category;
import model.products.Manufacturer;
import model.products.Product;
import model.products.Review;
import model.purchases.Payment;
import model.purchases.Purchase;
import model.purchases.Store;
import model.users.Administrator;
import model.users.User;
import technicalservices.persistence.dao.*;
import technicalservices.persistence.dao.jdbc.*;

public class DBManager {

	private static DBManager instance;
	private static DataSource dataSource;

	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			//questi vanno messi in file di configurazione!!!	
//			dataSource=new DataSource("jdbc:postgresql://52.39.164.176:5432/xx","xx","p@xx");
			dataSource = new DataSource("jdbc:postgresql://manny.db.elephantsql.com:5432/ndbzhnht","ndbzhnht","orWOykJIylSTUE8z1z2yXSFfvAn1jyIH");
		}
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}


	private DBManager() {}

	public static DBManager getInstance() {
		if(instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public User getUser(String username, String password) {
		return getUserDao().findUser(username, password);
	}

	public User getUserByEmail(String email) {
		return getUserDao().findUserByEmail(email);
	}

	public User getUserByUsername(String username) {
		return getUserDao().findUserByUsername(username);
	}

	public UserDao getUserDao() {
		return new UserDaoJDBC(dataSource);
	}

	public String getSecurityQuestion(String username) {
		return getUserDao().findSecurityQuestion(username);
	}

	public String getPassword(String username, String answer) {
		return getUserDao().findPassword(username, answer);
	}

	public int getId(String username) {
		return getUserDao().findId(username);
	}

	public void registerUser(User user) {
		getUserDao().registerUser(user);
	}

	public void updatePassword(String username, String newPassword) {
		getUserDao().updatePassword(username, newPassword);
	}

	public Administrator getAdministrator(String id, String password) {
		return getAdministratorDao().findAdmin(id, password);
	}

	public AdministratorDao getAdministratorDao() {
		return new AdministratorDaoJDBC(dataSource);
	}

	public ProductDao getProductDao(){
		return new ProductDaoJDBC(this.dataSource);
	}

	public CategoryDao getCategoryDao(){
		return new CategoryDaoJDBC(this.dataSource);
	}

	public PurchaseDao getPurchaseDao(){ return new PurchaseDaoJDBC(this.dataSource);}

	public TicketDao getTicketDao(){ return new TicketDaoJDBC(this.dataSource); }

	public Category getCategory(int categoryId){
		return getCategoryDao().findCategory(categoryId);
	}

	public Product getProduct(int productId){
		return getProductDao().findProduct(productId);
	}

	public ArrayList<Product> getProducts(int categoryId, int page, String manufacturer, float lowerBound,
										  float upperBound, int orderType, String keyword){
		return getProductDao().findProducts(categoryId, page, manufacturer, lowerBound, upperBound, orderType, keyword);
	}

	public int getProductsCount(int categoryId, String manufacturer, float lowerBound, float upperBound, String keyword){
		return getProductDao().findProductsNumber(categoryId, manufacturer, lowerBound, upperBound, keyword);
	}

	public ArrayList<Review> getLastReviews(int productId, int offset){
		return getProductDao().findLastReviews(productId, offset);
	}

	public ArrayList<Manufacturer> getManufacturers(int categoryId, String keyword){
		return getProductDao().findManufacturers(categoryId, keyword);
	}

	public void insertProduct(Product p){ getProductDao().saveProduct(p);}

	public void insertIntoCart(int idUser, int idProduct, int quantity) {
		getCartDao().insert(idUser, idProduct, quantity);
	}

	public void updateQuantity(int idUser, int idProduct, int quantity) {
		getCartDao().updateQuantity(idUser, idProduct, quantity);
	}

	public boolean isInCart(int idUser, int idProduct) {
		return getCartDao().isInCart(idUser, idProduct);
	}

	public ArrayList<Product> getCartProducts(int idUser) {
		return getCartDao().getCartProducts(idUser);
	}

	public float getTotalPrice(ArrayList<Product> products) {
		float sum = 0;
		for(Product p : products) {
			sum += p.getPricePerQuantity();
		}
		return sum;
	}

	public void deleteAllCartProducts(int userId){ getCartDao().deleteAll(userId);}

	public void deleteCartProduct(int idUser, int idProduct) {
		getCartDao().delete(idUser, idProduct);
	}

	public CartDao getCartDao() {
		return new CartDaoJDBC(dataSource);
	}

	public ReviewDao getReviewDao() {
		return new ReviewDaoJDBC(dataSource);
	}

	public void postReview(Review r) { getReviewDao().postReview(r);}

	public void updateProduct(Product p){ getProductDao().updateProduct(p);}

	public void deleteProduct(int id) {getProductDao().deleteProduct(id);}

	public ArrayList<Purchase> getPurchasesForUser(int idUser) { return getPurchaseDao().getPurchasesForUser(idUser);}
	public boolean purchasedProduct(int usrId, int productID) {return getProductDao().purchasedBy(usrId, productID);}

	public ArrayList<String> getMapCoords() {
		return getMapDao().getCoords();
	}

	public MapDao getMapDao() {
		return new MapDaoJDBC(dataSource);
	}

	public boolean insertPayment(float amount, String transactionCode){
		return getPurchaseDao().insertPayment(amount, transactionCode);
	}

	public boolean insertPurchase(int userId, int paymentId, String shipmentMode, int storeId, int status){
		return getPurchaseDao().insertPurchase(userId, paymentId, shipmentMode, storeId, status);
	}

	public Payment getPayment(String transactionCode){
		return getPurchaseDao().findPayment(transactionCode);
	}

	public Purchase getPurchase(Payment payment){
		return getPurchaseDao().findPurchase(payment);
	}

	public boolean insertPurchaseProductAssociation(int quantity, int productId, int purchaseId){
		return getPurchaseDao().insertPurchaseProductAssociation(quantity, productId, purchaseId);
	}

	public boolean existsProduct(String model, String manufacturer)
	{
		return getProductDao().existsProduct(model, manufacturer);
	}

	public int insertTicket(User user){ return getTicketDao().insertTicket(user); }

	public boolean insertTicketMessage(int ticketId, String message, boolean sender){
		return getTicketDao().insertTicketMessage(ticketId, message, sender);
	}

	public ArrayList<Ticket> getTicketsForUser(User user){
		return getTicketDao().getTicketsForUser(user);
	}

	public Ticket getTicket(int ticketId){
		return getTicketDao().getTicket(ticketId);
	}

	public ArrayList<TicketMessage> getTicketMessages(int ticketId){
		return getTicketDao().getTicketMessages(ticketId);
	}

	public Purchase getPurchaseById(int id) {return getPurchaseDao().getPurchaseById(id);}

	public String getEmailByUserId(int id) {
		return getUserDao().getEmailByUserId(id);
	}

	public boolean setTicketAdmin(int ticketId, Administrator admin){
		return getTicketDao().setTicketAdmin(ticketId, admin);
	}

	public boolean closeTicket(int ticketId){
		return getTicketDao().closeTicket(ticketId);
	}

	public ArrayList<Ticket> getTicketsWithoutAdmin(){
		return getTicketDao().getTicketsWithoutAdmin();
	}

	public ArrayList<Ticket> getTicketsForAdmin(Administrator administrator){
		return getTicketDao().getTicketsForAdmin(administrator);
	}
	
	public void registerGoogleUser(User user) { getUserDao().registerGoogleUser(user);}
	
	public ArrayList<Store> getAllStore() { return getPurchaseDao().getAllStore(); }

	public Store getStoreByAdmin(String admin) { return getPurchaseDao().getStoreByAdmin(admin); }

	public ArrayList<Purchase> getPurchaseForStore(int storeId) { return getPurchaseDao().getPurchaseForStore(storeId); }
	
	public void updateStatus(int status, int orderId) { getPurchaseDao().updateStatus(status, orderId); }
}