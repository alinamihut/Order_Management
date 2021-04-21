package dao;

import connection.ConnectionFactory;
import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *	ProductDAO - contains the queries and the methods that define the common operations for accessing the products table:
 *  findById(int productId);
 *	insert(Product product);
 *  delete(int productId);
 *  update (Product product, int productId);
 *  selectAll();
 */
public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product (name,price,stock)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM product where id = ?";
    private static final String deleteStatementString = "DELETE FROM product WHERE id = ?";
    private static final String updateStatementString = "UPDATE product SET name = ?, price = ?, stock = ? WHERE id = ?";
    private final static String findAllStatementString = "SELECT * FROM product";
    public static Product findById(int productId) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try{
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, productId);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            int price = rs.getInt("price");
            int stock = rs.getInt("stock");
            toReturn = new Product( productId, name, price, stock);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setString(2, String.valueOf(product.getPrice()));
            insertStatement.setString(3, String.valueOf(product.getStock()));
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }


    public static void delete(int productId) throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        ResultSet rs = null;

        try{
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setLong(1, productId);
            deleteStatement.executeUpdate();
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static int update (Product product, int productID){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, product.getName());
            updateStatement.setString(2, String.valueOf(product.getPrice()));
            updateStatement.setString(3, String.valueOf(product.getStock()));
            updateStatement.setString(4, String.valueOf(productID));
            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updatedId;
    }

    public static ArrayList<Product> selectAll () {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findAllStatement = null;
        ResultSet rs = null;
        ArrayList<Product> productsList = new ArrayList<>();
        try{
            findAllStatement = dbConnection.prepareStatement(findAllStatementString);
            rs=findAllStatement.executeQuery();
            while (rs.next()){
                Product newProduct = new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("price"),
                        rs.getInt("stock"));
                productsList.add(newProduct);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:select all " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return  productsList;
    }



}
