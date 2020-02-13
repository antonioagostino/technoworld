package model.purchases;

public class PayPalOrder {
    private String orderID;
    private int userID;
    private int[] products;
    private int[] productsQuantity;
    private String address;
    private float amount;

    public PayPalOrder(String orderID, int userID, int[] products, int[] productsQuantity, String address, float amount) {
        this.orderID = orderID;
        this.userID = userID;
        this.products = products;
        this.productsQuantity = productsQuantity;
        this.address = address;
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int[] getProducts() {
        return products;
    }

    public void setProducts(int[] products) {
        this.products = products;
    }

    public int[] getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(int[] productsQuantity) {
        this.productsQuantity = productsQuantity;
    }
}
