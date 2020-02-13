package model.products;

public class Category {
    private int id;
    private String name;

    public Category(String name){
        this.name = name;
        if(name.toLowerCase().equals("smartphone"))
            this.id = 2;
        else if(name.toLowerCase().equals("laptop"))
            this.id = 1;
        else if(name.toLowerCase().equals("hardware"))
            this.id = 3;
        else if(name.toLowerCase().equals("accessori"))
            this.id = 4;
    }

    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
