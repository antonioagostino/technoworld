package technicalservices.persistence.dao.jdbc;

import model.purchases.Store;
import model.users.Administrator;
import model.users.StoreAdministrator;
import technicalservices.persistence.DataSource;
import technicalservices.persistence.dao.StoreAdministratorDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreAdministratorDaoJDBC implements StoreAdministratorDao {

    private DataSource dataSource;
    private Connection connection;

    public StoreAdministratorDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public StoreAdministrator findAdministrator(String id, String password) {
        StoreAdministrator administrator = null;

        try {
            connection = dataSource.getConnection();
            String query = "select * from \"storeAdmin\", \"map\" where \"storeAdmin\".\"storeId\" = \"map\".\"idStore\"" +
                    " and \"storeAdmin\".id = ?" +
                    " and \"storeAdmin\".password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                Store store = new Store(result.getInt("storeId"), result.getString("position"),
                        result.getString(7));
                administrator = new StoreAdministrator(result.getString("id"), result.getString(2),
                        result.getString("password"), store);
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
}
