package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import dao.OrderDAO;
import dao.ProductDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Client;
import model.Order;
import model.Product;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Orders controller - creates the flow between the orders' scene and the application. Has the methods which perform the operations
 * insert and display all on the orders table, on a top level by sending the data from the user to the business layer
 * of the application.
  */
public class ordersController {
    public Button btnAddOrder;
    public Button btnDisplayAll;
    public Button btnGenerateBill;
    public ChoiceBox cbClient;
    public ChoiceBox cbProduct;
    public TextField tfProductQuantity;
    public TableView tbOrders;
    ProductBLL productBLL = new ProductBLL();
    OrderBLL orderBll = new OrderBLL();
    /**
     * initializes the main scene on the action of the go back button
     * @param actionEvent
     * @throws IOException
     */
    public void setSceneMain(ActionEvent actionEvent) throws IOException {
        URL url= new File("src/main/java/sample.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        Scene sceneMain = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneMain);
        window.show();

    }

    /**
     * set the values for the choice box where the user selects the client
     */
    public void setValuesForCbClients(){
        ArrayList<Client> clientsList = ClientBLL.selectAllClients();
        cbClient.getItems().clear();
        for (Client c:clientsList){
            cbClient.getItems().add(c.getId());
        }
    }
    /**
     * set the values for the choice box where the user selects the product
     */
    public void setValuesForCbProducts(){
        ArrayList<Product> productsList = ProductBLL.selectAllProducts();
        cbProduct.getItems().clear();
        for (Product p:productsList){
            cbProduct.getItems().add(p.getId());
        }
    }

    /**
     * creates the order object and sends it to the business layer, where it is inserted in the corresponding table.
     * @throws Exception
     */
    public void insertOrder() throws Exception {
        String cbClientString = cbClient.getSelectionModel().getSelectedItem().toString();
        int idClient = Integer.parseInt(cbClientString);
        int idProduct = Integer.parseInt( cbProduct.getSelectionModel().getSelectedItem().toString());
        int quantity = Integer.parseInt(tfProductQuantity.getText());
        int stock;
        Client client = ClientBLL.findClientById(idClient);
        Product product = ProductBLL.findProductById(idProduct);
        if (product.getStock() < quantity){
            View.showAlert("The product's stock is smaller than the quantity! ");
            throw new Exception("The product's stock is smaller than the quantity! ");
        }
        else {
            Order newOrder = new Order (idClient,idProduct,quantity);
            orderBll.insertOrder(newOrder);
            stock = product.getStock() - quantity;
            Product newProduct = new Product(product.getName(),product.getPrice(),stock);
            if (stock==0) productBLL.deleteProductById(idProduct);
            else productBLL.updateProduct(newProduct,idProduct);
            writeBillToTextFile(newOrder);
        }

    }

    /**
     * displays all orders on the action of the display button
     * @param actionEvent
     * @throws SQLException
     */
    public void showAllOrders(ActionEvent actionEvent ) throws SQLException {
        ArrayList<Order> ordersList;
        ordersList = OrderDAO.selectAll();
        View.createTable(ordersList,tbOrders);
    }


    public void writeBillToTextFile (Order o) throws IOException {
        File file = new File("bill.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write("The details of your order: \n");
        fw.write("----------------------------------------\n");
        Client c= ClientBLL.findClientById(o.getIdClient());
        fw.write("CLIENT:" + c.getName());
        fw.write("\n");
        Product p=ProductBLL.findProductById(o.getIdProduct());
        fw.write("PRODUCT: " + p.getName());
        fw.write("\n");
        fw.write("QUANTITY: " + o.getQuantity());
        fw.write("\n");
        int price = o.getQuantity() * p.getPrice();
        fw.write("TOTAL PRICE:" + price);
        fw.write("\n");
        fw.write("----------------------------------------\n");
        fw.write("Thank you for your purchase \n");
        fw.close();
    }

}
