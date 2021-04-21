package presentation;

import bll.ClientBLL;
import dao.ClientDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Client;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clients controller - creates the flow between the clients' scene and the application. Has the methods which perform the operations
 * delete, update, insert and show all on the clients table, on a top level by sending the data from the user to the bussiness layer
 * of the application.
 */
public class clientsController {
    public Button btnGoBack;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnDisplayAll;
    public Button btnInsert;
    public TextField tfClientID;
    public TextField tfClientEmail;
    public TextField tfClientName;
    public TextField tfClientPhone;
    public TableView tableCustomers;
    ClientBLL clientBLL = new ClientBLL();
    public void setSceneMain(ActionEvent actionEvent) throws IOException {
        URL url= new File("src/main/java/sample.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        Scene sceneMain = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneMain);
        window.show();

    }

    public void deleteClient(ActionEvent actionEvent ) throws SQLException {
            clientBLL.deleteClientById(Integer.parseInt(tfClientID.getText()));

    }

    public void updateClient(ActionEvent actionEvent ) throws SQLException {
        int id = Integer.parseInt(tfClientID.getText());
        String name = tfClientName.getText();
        String email = tfClientEmail.getText();
        String phoneNumber = tfClientPhone.getText();
        Client newClient = new Client (id, name, email, phoneNumber);
        clientBLL.updateClient(newClient, id);

    }

    public void insertClient(ActionEvent actionEvent ) throws SQLException {
        String name = tfClientName.getText();
        String email = tfClientEmail.getText();
        String phoneNumber = tfClientPhone.getText();
        Client newClient = new Client (name, email, phoneNumber);
        clientBLL.insertClient(newClient);
        }

        public void showAllClients(ActionEvent actionEvent ) throws SQLException{
            ArrayList<Client> clientsList;
            clientsList = ClientDAO.selectAll();
            View.createTable(clientsList,tableCustomers);
        }



}

