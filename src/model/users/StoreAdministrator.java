package model.users;

import model.purchases.Store;

public class StoreAdministrator {

	private String id;
	private String name;
	private String password;
	private Store store;

	public StoreAdministrator(String id, String name, String password, Store store) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.store = store;
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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
}
