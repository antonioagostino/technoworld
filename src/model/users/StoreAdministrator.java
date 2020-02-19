package model.users;

public class StoreAdministrator {

	private String id;
	private String name;
	private String password;
	private int store;
	
	public StoreAdministrator(String id, String name, String password, int store) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.store = store;
	}

	public StoreAdministrator() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStore() {
		return store;
	}

	public void setStore(int store) {
		this.store = store;
	}
	
	
	
	
}
