package technicalservices.persistence.dao;

import java.util.ArrayList;

import model.products.Category;

public interface CategoryDao {
    Category findCategory(int categoryId);
    ArrayList<Category> findCategories();
}
