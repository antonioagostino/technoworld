package model.purchases;

public class Store {

	private int id;
	private String street;
	private String coordinates;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
	public Store(int id, String street, String coordinates) {
		super();
		this.id = id;
		this.street = street;
		this.coordinates = coordinates;
	}
	
	
	
	public Store() {}
	
	public Store(int id, String street) {
		super();
		this.id = id;
		this.street = street;
	}
	
	
	
	
	
	
}
