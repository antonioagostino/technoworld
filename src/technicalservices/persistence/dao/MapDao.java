package technicalservices.persistence.dao;

import java.util.ArrayList;

public interface MapDao {

	public void insert(String coord);
	public ArrayList<String> getCoords();
}
