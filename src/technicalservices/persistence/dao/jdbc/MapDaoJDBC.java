package technicalservices.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import technicalservices.persistence.DataSource;
import technicalservices.persistence.dao.MapDao;

public class MapDaoJDBC implements MapDao {

	private DataSource dataSource;
    private Connection connection;

    public MapDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }
    
	@Override
	public void insert(String coord) {
		
	}

	@Override
	public ArrayList<String> getCoords() {
		ArrayList<String> coord = new ArrayList<String>();
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select position from map";
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				coord.add(result.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return coord;
	}
}
