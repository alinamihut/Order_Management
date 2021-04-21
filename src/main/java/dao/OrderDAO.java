package dao;

import connection.ConnectionFactory;
import model.Client;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO{
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO ptassignment.order (idClient, idProduct, quantity)"
            + " VALUES (?,?,?)";
    private final static String findAllStatementString = "SELECT * FROM ptassignment.order";

    public static int insert(Order order) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1,String.valueOf(order.getIdClient()));
            insertStatement.setString(2,String.valueOf(order.getIdProduct()));
            insertStatement.setString(3, String.valueOf(order.getQuantity()));
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static ArrayList<Order> selectAll () {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findAllStatement = null;
        ResultSet rs = null;
        ArrayList<Order> ordersList = new ArrayList<>();
        try{
            findAllStatement = dbConnection.prepareStatement(findAllStatementString);
            rs=findAllStatement.executeQuery();
            while (rs.next()){
                Order newOrder = new Order (rs.getInt("idOrder"), rs.getInt("idClient"), rs.getInt("idProduct"), rs.getInt ("quantity"));
                ordersList.add(newOrder);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:select all " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return  ordersList;
    }
}
