package model.products;

public class Manufacturer {
    private String name;
    private int products;

    public Manufacturer(String name, int products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProducts() {
        return products;
    }

    public void setProducts(int products) {
        this.products = products;
    }
}
