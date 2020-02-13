package technicalservices.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.users.Administrator;
import technicalservices.persistence.DataSource;
import technicalservices.persistence.dao.AdministratorDao;

public class AdministratorDaoJDBC implements AdministratorDao {

	private DataSource dataSource;
	private Connection connection;
	
	public AdministratorDaoJDBC(DataSource dataS) {
		dataSource = dataS;
		connection = null;
	}
	
	@Override
	public Administrator findAdmin(String id, String password) {
		Administrator administrator = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from administrator where id = ? and password = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				administrator = new Administrator(result.getString(1), result.getString(2));
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
		return administrator;
	}

	@Override
	public ArrayList<Administrator> findAll() {
		ArrayList<Administrator> administrators = new ArrayList<Administrator>();
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from amministratore";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Administrator admin = new Administrator(result.getString(0), result.getString(1));
				administrators.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return administrators;
	}

}
