package model;
/**
 * Order class -
 * Resembles the order table in the database and has the fields exactly the same type as the columns from the
 * corresponding table. Contains constructors, getters and setters for creating and accessing the Order object.
 *
 */
public class Order {
    private int idOrder;
    private int idClient;
    private int idProduct;
    private int quantity;

    public Order(int idOrder, int idClient, int idProduct, int quantity) {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public Order(int idClient, int idProduct, int quantity) {
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
