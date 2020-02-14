package technicalservices.persistence.dao.jdbc;

import model.help.Ticket;
import model.help.TicketMessage;
import model.help.TicketProxy;
import model.users.Administrator;
import model.users.User;
import technicalservices.persistence.DataSource;
import technicalservices.persistence.dao.TicketDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDaoJDBC implements TicketDao {

    private DataSource dataSource;
    private Connection connection;

    public TicketDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public int insertTicket(User user) {

        try {
            connection = dataSource.getConnection();
            String query = "insert into tickets(\"date\", \"userId\", \"status\") values(now(), ?, 0) RETURNING id";
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setInt(1, user.getId());
            ResultSet resultSet = stat.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean insertTicketMessage(int ticketId, String message, boolean sender) {

        try {
            connection = dataSource.getConnection();
            String query = "insert into \"ticketMessages\"(\"ticketId\", \"date\", \"messagebody\", \"sender\") values(?, now(), ?, ?)";
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setInt(1, ticketId);
            stat.setString(2, message);
            stat.setBoolean(3, sender);
            stat.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Ticket> getTicketsForUser(User user) {

        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            String query = "select * from tickets full outer join administrator on \"adminId\" = administrator.id where " +
                    "\"userId\" = ?";
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setInt(1, user.getId());
            ResultSet resultSet = stat.executeQuery();
            while (resultSet.next()){
                TicketProxy ticket = new TicketProxy(resultSet.getInt("id"),
                        resultSet.getDate("date"), user, resultSet.getInt("status"),
                        new Administrator(resultSet.getString("adminId"),
                                resultSet.getString("password")));
                tickets.add(ticket);
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

        return tickets;
    }

    @Override
    public Ticket getTicket(int ticketId) {
        Ticket ticket = null;

        try {
            connection = dataSource.getConnection();
            String query = "select * from tickets full outer join administrator on \"adminId\" = administrator.id, " +
                    "\"user\" where tickets.\"userId\" = \"user\".id and tickets.id = ?";
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setInt(1, ticketId);
            ResultSet resultSet = stat.executeQuery();
            if (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(8));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setUsername(resultSet.getString("username"));
                ticket = new TicketProxy(ticketId, resultSet.getDate("date"), user, resultSet.getInt("status"),
                            new Administrator(resultSet.getString("adminId"),
                                    resultSet.getString("password")));;
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

        return ticket;
    }

    @Override
    public ArrayList<TicketMessage> getTicketMessages(int ticketId) {
        ArrayList<TicketMessage> ticketMessages = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            String query = "select * from \"ticketMessages\" where \"ticketId\" = ? order by \"date\" asc";
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setInt(1, ticketId);
            ResultSet resultSet = stat.executeQuery();
            while (resultSet.next()){
                ticketMessages.add(new TicketMessage(resultSet.getInt("id"),
                        resultSet.getDate("date"), resultSet.getString("messagebody"),
                        resultSet.getBoolean("sender")));
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

        return ticketMessages;
    }

    @Override
    public boolean setTicketAdmin(int ticketId, Administrator admin) {
        try {
            connection = dataSource.getConnection();
            String query = "update \"tickets\" set \"adminId\" = ? where id = ?";
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, admin.getId());
            stat.setInt(2, ticketId);
            stat.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean closeTicket(int ticketId) {
        try {
            connection = dataSource.getConnection();
            String query = "update \"tickets\" set \"status\" = 1 where id = ?";
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setInt(1, ticketId);
            stat.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
