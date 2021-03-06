package dao;

import connection.ConnectionFactory;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *	ClientDAO - contains the queries and the methods that define the common operations for accessing the clients table:
 *  findById(int clientId);
 *	insert(Client client);
 *  delete(int clientId);
 *  update (Client client, int clientID);
 *  selectAll();
 */
public class ClientDAO{
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO client (name,email, phoneNumber)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM client where id = ?";
    private static final String deleteStatementString = "DELETE FROM client WHERE id = ?";
    private static final String updateStatementString = "UPDATE client SET name = ?, email = ?, phoneNumber = ? WHERE id = ?";
    private final static String findAllStatementString = "SELECT * FROM client";

    /**
     * defines the operation of finding an entry in the clients table by id
     * @param clientId
     * @return
     */
    public static Client findById(int clientId) {
            Client toReturn = null;

            Connection dbConnection = ConnectionFactory.getConnection();
            PreparedStatement findStatement = null;
            ResultSet rs = null;
            try{
                findStatement = dbConnection.prepareStatement(findStatementString);
                findStatement.setLong(1, clientId);
                rs = findStatement.executeQuery();
                rs.next();

                String name = rs.getString("name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                toReturn = new Client( clientId, name, email, phoneNumber);
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
            } finally {
                ConnectionFactory.close(rs);
                ConnectionFactory.close(findStatement);
                ConnectionFactory.close(dbConnection);
            }
            return toReturn;
        }

    /**
     * defines the operation of inserting an entry in the clients table
     * @param client
     * @return
     */
    public static int insert(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getEmail());
            insertStatement.setString(3, client.getPhoneNumber());
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

    /**
     * defines the operation of deleting an entry from the clients table by id
     * @param clientId
     * @throws SQLException
     */
    public static void delete(int clientId) throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        ResultSet rs = null;

        try{
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setLong(1, clientId);
            deleteStatement.executeUpdate();
            //rs = deleteStatement.executeQuery();
            //rs.next();
        }
        catch (SQLException e) {
        LOGGER.log(Level.WARNING,"ClientDAO:delete " + e.getMessage());
    } finally {
        ConnectionFactory.close(rs);
        ConnectionFactory.close(deleteStatement);
        ConnectionFactory.close(dbConnection);
    }
    }

    /**
     * defines the operation of updating an entry in the clients table
     * @param client
     * @param clientID
     * @return
     */
    public static int update (Client client, int clientID){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, client.getName());
            updateStatement.setString(2, client.getEmail());
            updateStatement.setString(3, client.getPhoneNumber());
            updateStatement.setString(4, String.valueOf(clientID));
            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updatedId;
    }

    /**
     * defines the operation of finding all the entries in the clients table
     * @return
     */
    public static ArrayList<Client> selectAll () {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findAllStatement = null;
        ResultSet rs = null;
        ArrayList<Client> clientsList = new ArrayList<>();
        try{
            findAllStatement = dbConnection.prepareStatement(findAllStatementString);
            rs=findAllStatement.executeQuery();
            while (rs.next()){
                Client newClient = new Client(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                        rs.getString("phoneNumber"));
                clientsList.add(newClient);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:select all " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return  clientsList;
    }



}
