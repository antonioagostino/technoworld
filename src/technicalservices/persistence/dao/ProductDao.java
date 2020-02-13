package technicalservices.persistence.dao;

import java.util.ArrayList;

import model.products.Manufacturer;
import model.products.Product;
import model.products.Review;

public interface ProductDao {
    Product findProduct(int productId);
    ArrayList<Product> findProducts(int categoryId, int page, String manufacturer, float lowerBound, float upperBound,
                                    int orderType, String keyword);
    int findProductsNumber(int categoryId, String manufacturer, float lowerBound, float upperBound, String keyword);
    ArrayList<Manufacturer> findManufacturers(int categoryID, String keyword);
    ArrayList<Review> findLastReviews(int productId, int offset);
    void saveProduct(Product p);
    void updateProduct(Product p);
    void deleteProduct(int id);
    boolean purchasedBy(int userID, int productID);
    boolean existsProduct(String model, String manufacturer); 
}
