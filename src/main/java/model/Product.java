package model;
/**
 * Product class -
 * Resembles the product table in the database and has the fields exactly the same type as the columns from the
 * corresponding table. Contains constructors, getters and setters for creating and accessing the Product object.
 *
 */
public class Product {
    private int id;
    private String name;
    private int price;
    private int stock;

    /**
     * constructor for the product object with id
     * @param id
     * @param name
     * @param price
     * @param stock
     */
    public Product(int id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * constructor for the product object without id
     * @param name
     * @param price
     * @param stock
     */
    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * getter for the product id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * setter for the product id
     * @param id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for the product name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * setter for the product name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for the product price
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     * setter for the product price
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * getter for the product stock
     * @return
     */
    public int getStock() {
        return stock;
    }

    /**
     * setter for the product stock
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
