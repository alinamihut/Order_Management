package bll;

import dao.ClientDAO;
import model.Client;
import presentation.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 *  Client BLL class - contains the methods which encapsulate the application logic for the operations on the clients table
 * (finding a client by id, inserting a client in the table, deleting a client, updating and displaying all entries in the table).
 **/
public class ClientBLL {
    private List<Validator<Client>> validators;

    /**
     * constructor for the bussiness layer of the client logic
     */
    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new PhoneValidator());
    }

    /**
     * application logic for finding a client by id
     * @param id
     * @return
     */
    public static Client findClientById(int id) {
        Client c = ClientDAO.findById(id);
        if (c == null) {
            View.showAlert("The client with id =" + id + " was not found!");
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return c;
    }

    /**
     * application logic for inserting a client in the table
     *  @param client
     */
    public void insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        if (ClientDAO.insert(client)!=0){
            View.showAlert("The client was inserted successfully!");
        }
    }

    /**
     * application logic for deleting a client from the table
     * @param id
     * @throws SQLException
     */
    public void deleteClientById (int id) throws SQLException {
        Client c = ClientDAO.findById(id);
        if (c == null) {
            View.showAlert("The client with id = " + id + " was not found!");
            throw new NoSuchElementException("The client with id =" + id + " was not found!");


        }
        else ClientDAO.delete(c.getId());
        View.showAlert("The client with id =" + id + " was successfully deleted");
    }

    /**
     * application logic for updating a client in the table
     * @param client
     * @param id
     */
    public void updateClient (Client client, int id){
        Client c = ClientDAO.findById(id);
        if (c == null) {
            View.showAlert("The client with id = " + id + " was not found!");
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        if (ClientDAO.update(client, id)!=0){
            View.showAlert("The client with id = " + id + " was updated successfully!");
        }

    }

    /**
     * application logic for selecting all the clientd from the table
     * @return
     */

    public static ArrayList<Client> selectAllClients(){
        ArrayList<Client> clientsList;
        clientsList = ClientDAO.selectAll();
        return clientsList;
    }
}
