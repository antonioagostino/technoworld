package technicalservices.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.users.User;
import technicalservices.persistence.DataSource;
import technicalservices.persistence.dao.UserDao;

public class UserDaoJDBC implements UserDao {

	private DataSource dataSource;
	private Connection connection;
	
	public UserDaoJDBC(DataSource dataS) {
		dataSource = dataS;
		connection = null;
	}
	
	@Override
	public void registerUser(User user) {
		try {
			connection = dataSource.getConnection();
			
			String registration = "insert into \"user\"(surname, name, \"birthDate\", email, username, password, \"securityQuestion\", \"securityAnswer\") values (?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(registration);
			statement.setString(1, user.getSurname());
			statement.setString(2, user.getName());
			statement.setDate(3, new java.sql.Date(user.getBirthDate().getTime()));
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getUsername());
			statement.setString(6, user.getPassword());
			statement.setString(7, user.getSecurityQuestion());
			statement.setString(8, user.getSecurityAnswer());
			
			statement.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public void updatePassword(String username, String newPassword) {
		try {
			connection = dataSource.getConnection();
			
			String query = "update \"user\" set password = ? where username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newPassword);
			statement.setString(2, username);
			
			statement.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findUser(String username, String password) {
		User user = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select * from \"user\" where username = ? and password = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				user = new User(username, result.getString(7), result.getString(3), result.getString(2), result.getString(5), result.getDate(4), result.getString(8), result.getString(9));
				user.setId(result.getInt(1));
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
		
		return user;
	}
	
	@Override
	public User findUserByUsername(String username) {
		User user = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select * from \"user\" where username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				user = new User(username, result.getString(7), result.getString(3), result.getString(2), result.getString(5), result.getDate(4), result.getString(8), result.getString(9));
				user.setId(result.getInt(1));
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
		
		return user;
	}
	
	@Override
	public User findUserByEmail(String email) {
		User user = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select * from \"user\" where email = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				user = new User(result.getString(6), result.getString(7), result.getString(3), result.getString(2), email, result.getDate(4), result.getString(8), result.getString(9));
				user.setId(result.getInt(1));
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
		
		return user;
	}
	

	@Override
	public String findSecurityQuestion(String username) {
		String question = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select \"securityQuestion\" from \"user\" where username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if(result.next()) 
				question = result.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return question;
	}
	
	
	@Override
	public String findPassword(String username, String answer) {
		String password = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select password from \"user\" where username = ? and \"securityAnswer\" = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, answer);
			
			ResultSet result = statement.executeQuery();
			if(result.next()) 
				password = result.getString(1);
			
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return password;
	}

	
	@Override
	public int findId(String username) {
		int id = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select id from \"user\" where username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			
			ResultSet result = statement.executeQuery();
			if(result.next()) 
				id = result.getInt(1);
			
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return id;
	}
	
	
	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public String getEmailByUserId(int id) {
		String email = null;
			try {
				connection = dataSource.getConnection();
				
				String query = "select email from \"user\" where id = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, id);
				
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					email = result.getString(1);
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
		return email;
	}

	@Override
	public void registerGoogleUser(User user) {
		try {
			connection = dataSource.getConnection();
			
			String registration = "insert into \"user\"(surname, name, email, username) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(registration);
			statement.setString(1, user.getSurname());
			statement.setString(2, user.getName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getUsername());
			
			statement.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
