package model.products;


import java.util.ArrayList;

public abstract class Product {
    private int id;
    private String model;
    private String manufacturer;
    private float price;
    private String specs;
    private String description;
    private Category category;
    private int starsAvg;
    private String imagePath;
    protected ArrayList<Review> reviews;
    
    private int orderQuantity;
	public float pricePerQuantity;

    public Product(int id, String model, String manufacturer, float price, String specs, String description, Category category, int starsAvg, String imagePath) {
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
        this.specs = specs;
        this.description = description;
        this.category = category;
        this.starsAvg = starsAvg;
        this.imagePath = imagePath;
    }

    public Product(){}

    public Product(int id, String model, String manufacturer, float price) {
		this.id = id;
		this.model = model;
		this.manufacturer = manufacturer;
		this.price = price;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public int getStarsAvg() {
        return starsAvg;
    }

    public void setStarsAvg(int starsAvg) {
        this.starsAvg = starsAvg;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public abstract ArrayList<Review> getReviews();

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
    
    public int getOrderQuantity() {
    	return orderQuantity;
    }
    
    public void setOrderQuantity(int quantity) {
    	this.orderQuantity = quantity;
    }
    
    public float getPricePerQuantity() {
    	return pricePerQuantity;
    }
    
    public void setPricePerQuantity(float pricePerQuantity) {
    	this.pricePerQuantity = pricePerQuantity;
    }
    
}
