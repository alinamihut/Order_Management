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

    /**
     * order object constructtor with id
     * @param idOrder
     * @param idClient
     * @param idProduct
     * @param quantity
     */
    public Order(int idOrder, int idClient, int idProduct, int quantity) {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    /**
     * order object constructor without id
     * @param idClient
     * @param idProduct
     * @param quantity
     */
    public Order(int idClient, int idProduct, int quantity) {
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    /**
     * getter for the order id
     * @return
     */
    public int getIdOrder() {
        return idOrder;
    }

    /**
     * setter for the order id
     * @param idOrder
     */
    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    /**
     * getter fot the client id
     * @return
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * setter for the client id
     * @param idClient
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * getter for the product id
     * @return
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * setter for the product id
     * @param idProduct
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * getter for the order quantity
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * setter for the order quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
