package technicalservices.persistence.dao.jdbc;

import technicalservices.persistence.DataSource;
import technicalservices.persistence.dao.CategoryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.products.Category;

public class CategoryDaoJDBC implements CategoryDao {

    private DataSource dataSource;
    private Connection connection;

    public CategoryDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Category findCategory(int categoryId) {
        Category category = null;
        if(categoryId > 0) {
            try {
                this.connection = this.dataSource.getConnection();
                String query = "select * from \"category\" where id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, categoryId);
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    category = new Category(categoryId, result.getString(2));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    this.connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return category;
        } else {
            return new Category(0, "Prodotti");
        }
    }

    @Override
    public ArrayList<Category> findCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            this.connection = this.dataSource.getConnection();
            String query = "select * from \"category\"";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                categories.add(new Category(result.getInt(0), result.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return categories;

    }
}
