package technicalservices.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.products.Review;
import technicalservices.persistence.DataSource;
import technicalservices.persistence.dao.ReviewDao;

public class ReviewDaoJDBC implements ReviewDao {

	
	private DataSource dataSource;
    private Connection connection;

    public ReviewDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
    
    
	@Override
	public void postReview(Review r) {
		try {
            connection = dataSource.getConnection();
            String query = "insert into review(title,body,stars,author,product) values(?,?,?,?,?)";
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, r.getTitle());
            stat.setString(2, r.getBody());
            stat.setInt(3, r.getStars());
            stat.setInt(4, r.getAuthor());
            stat.setInt(5, r.getProduct());
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

	}
}
