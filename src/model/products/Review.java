package model.products;

public class Review {
    private int id;
    private String title;
    private String body;
    private int stars;
    private int author;
    private int product;
    
    private String username;

    
    public Review() {}
    
    public Review(int id, String title, String body, int stars, int author, int product) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.stars = stars;
        this.author = author;
        this.product = product;
    }

    public Review(int id, String title, String body, int stars, int product, String username) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.stars = stars;
		this.product = product;
		this.username = username;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int user) {
        this.author = user;
    }
    
    public void setProduct(int product) {
		this.product = product;
	}
    
    public int getProduct() {
		return product;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    
}
