package bll;

import dao.ClientDAO;
import dao.ProductDAO;
import model.Product;
import presentation.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Product BLL class - contains the methods which encapsulate the application logic for the operations on the products table
 * (finding a product by id, inserting a product in the table, deleting a product, updating and displaying all entries in the
 * table).
 **/
public class ProductBLL {

    private List<Validator<Product>> validators;


    public ProductBLL() {
        validators = new ArrayList<>();
        validators.add(new ProductValidator());
    }
    /**
     * application logic for finding a product by id
     * @return
     */
    public static Product findProductById(int id) {
        Product p = ProductDAO.findById(id);
        if (p == null) {
            View.showAlert("The product with id =" + id + " was not found!");
            throw new NoSuchElementException("The product with id =" + id + " was not found!");

        }
        return p;
    }

    /**
     * application logic for inserting a product in the table
     * @param product
     */

    public void insertProduct(Product product) {
        for (Validator<Product> v : validators) {
            v.validate(product);

        }
        if (ProductDAO.insert(product)!=0){
            View.showAlert("The new product was inserted successfully!");
        }
    }

    /**
     * application logic for deleting a product from the table
     * @param id
     * @throws SQLException
     */
    public static void deleteProductById(int id) throws SQLException {
        Product p = ProductDAO.findById(id);
        if (p == null) {
            View.showAlert("The product with id =" + id + " was not found!");
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        else ProductDAO.delete(p.getId());
        View.showAlert("The product with id =" + id + " was successfully deleted");
    }

    /**
     * application logic for updating a product in the table
     * @param product
     * @param id
     */

    public void updateProduct (Product product, int id){
        Product p = ProductDAO.findById(id);
        if (p == null) {
            View.showAlert("The product with id =" + id + " was not found!");
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        if (ProductDAO.update(product,id)!=0){
            View.showAlert("The product was updated successfully!");
        }
    }

    /**
     * application logic for finding all the products in the table
     * @return
     */
    public static ArrayList<Product> selectAllProducts(){
        ArrayList<Product> productsList;
        productsList = ProductDAO.selectAll();
        return productsList;
    }
}
