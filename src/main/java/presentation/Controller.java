package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Controller class:
 * - creates the connection between the GUI and the model, initialises the main scene and contains the methods which create and load the
 * next three scenes in the application (clients scene, products scene and orders scene)
 */
public class Controller {
    public Button btnClients;
    public Button btnProducts;
    public Button btnOrders;

    /**
     * sets the Clients scene on the action of the corresponding button
     * @param actionEvent
     * @throws IOException
     */

    public void setSceneClients(ActionEvent actionEvent) throws IOException {
        URL url= new File("src/main/java/clients.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        Scene sceneClients = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneClients);
        window.setTitle("Clients");
        window.show();

    }

    /**
     * sets the Products scene on the action of the corresponding button
     * @param actionEvent
     * @throws IOException
     */
    public void setSceneProducts(ActionEvent actionEvent) throws IOException {
        URL url= new File("src/main/java/products.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        Scene sceneProducts = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneProducts);
        window.setTitle("Products");
        window.show();

    }
    /**
     * sets the Orders scene on the action of the corresponding button
     * @param actionEvent
     * @throws IOException
     */
    public void setSceneOrders(ActionEvent actionEvent) throws IOException {
        URL url= new File("src/main/java/orders.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        Scene sceneOrders = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneOrders);
        window.setTitle("Orders");
        window.show();

    }
}
