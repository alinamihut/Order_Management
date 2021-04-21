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

public class Controller {
    public Button btnClients;
    public Button btnProducts;
    public Button btnOrders;


    public void setSceneClients(ActionEvent actionEvent) throws IOException {
      // Scene sceneClients = btnClients.getScene();
       // Window window = sceneClients.getWindow();
       // Stage stageClients = (Stage) window;
        //FXMLLoader loader= new FXMLLoader(getClass().getResource("src/main/java/clients.fxml"));
        //Stage stageClients = (Stage) btnClients.getScene().getWindow();
        //Scene sceneClients = new Scene(loader.getRoot());
        //.setScene(sceneClients);

        //Parent parent = FXMLLoader.load(getClass().getResource("src/main/java/clients.fxml"));
        URL url= new File("src/main/java/clients.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        Scene sceneClients = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneClients);
        window.setTitle("Clients");
        window.show();

    }

    public void setSceneProducts(ActionEvent actionEvent) throws IOException {
        URL url= new File("src/main/java/products.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        Scene sceneProducts = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneProducts);
        window.setTitle("Products");
        window.show();

    }

    public void setSceneOrders(ActionEvent actionEvent) throws IOException {
        URL url= new File("src/main/java/orders.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        Scene sceneOrders = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneOrders);
        window.setTitle("Orders");
        window.show();
        //ordersController ordersController = new ordersController();
        // ordersController.setValuesForCbClients();

    }
}
