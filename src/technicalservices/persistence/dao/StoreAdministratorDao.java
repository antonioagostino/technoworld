package technicalservices.persistence.dao;

import model.users.StoreAdministrator;

public interface StoreAdministratorDao {
    StoreAdministrator findAdministrator(String id, String password);
}
