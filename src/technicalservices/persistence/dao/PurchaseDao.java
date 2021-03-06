package technicalservices.persistence.dao;

import model.purchases.Payment;
import model.purchases.Purchase;
import model.purchases.Store;

import java.util.ArrayList;

public interface PurchaseDao {
    ArrayList<Purchase> getPurchasesForUser(int idUser);
    boolean insertPayment(float amount, String transactionCode);
    boolean insertPurchase(int userId, int paymentId, String shipmentMode, int storeId, int status);
    Payment findPayment(String transactionCode);
    Purchase findPurchase(Payment payment);
    boolean insertPurchaseProductAssociation(int quantity, int productId, int purchaseId);
	Purchase getPurchaseById(int id);
	ArrayList<Store> getAllStore();
	Store getStoreByAdmin(String admin);
	ArrayList<Purchase> getPurchaseForStore(int storeId);
	void updateStatus(int orderStatus, int orderId);
}
