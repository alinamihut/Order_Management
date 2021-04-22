package presentation;

import bll.ProductBLL;
import dao.ClientDAO;
import dao.ProductDAO;
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
import model.Product;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Products controller - creates the flow between the products' scene and the application. Has the methods which perform the operations
 * delete, update, insert and show all on the products table, on a top level by sending the data from the user to the bussiness layer
 * of the application.
 */
public class productsController {
    public Button btnGoBack;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnInsert;
    public Button btnDisplayAll;
    public TextField tfProductID;
    public TextField tfProductName;
    public TextField tfProductStock;
    public TextField tfProductPrice;
    public TableView tbProducts;
    ProductBLL productBLL = new ProductBLL();

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
     * performs the deletion of a product on the action of the delete button
     * @param actionEvent
     * @throws SQLException
     */
    public void deleteProduct(ActionEvent actionEvent ) throws SQLException {
        ProductBLL.deleteProductById(Integer.parseInt(tfProductID.getText()));
        clearTextFields();
    }
    /**
     * performs the update of a product on the action of the update button
     * @param actionEvent
     * @throws SQLException
     */

    public void updateProduct(ActionEvent actionEvent ) throws SQLException {
        int id = Integer.parseInt(tfProductID.getText());
        String name = tfProductName.getText();
        int price = Integer.parseInt(tfProductPrice.getText());
        int stock = Integer.parseInt(tfProductStock.getText());
        Product newProduct = new Product(id,name,price, stock);
        productBLL.updateProduct(newProduct,id);
        clearTextFields();

    }
    /**
     * performs the insertion of a product on the action of the insert button
     * @param actionEvent
     * @throws SQLException
     */
    public void insertProduct(ActionEvent actionEvent ) throws SQLException {
        String name = tfProductName.getText();
        int price = Integer.parseInt(tfProductPrice.getText());
        int stock = Integer.parseInt(tfProductStock.getText());
        Product newProduct = new Product(name,price, stock);
        productBLL.insertProduct(newProduct);
        clearTextFields();
    }
    /**
     * displays all products on the action of the display button
     * @param actionEvent
     * @throws SQLException
     */

    public void showAllProducts(ActionEvent actionEvent ) throws SQLException{
        ArrayList<Product> productsList;
        productsList = ProductDAO.selectAll();
        View.createTable(productsList,tbProducts);
    }

    private void clearTextFields(){
        tfProductID.clear();
        tfProductName.clear();
        tfProductPrice.clear();
        tfProductStock.clear();
    }
}
