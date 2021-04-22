package model;

/**
 * Client class -
 * Resembles the client table in the database and has the fields exactly the same type as the columns from the
 * corresponding table. Containts constructors, getters and setters for creating and accessing the Client object.
 *
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;

    /**
     *
     * @param id
     * @param name
     * @param email
     * @param phoneNumber
     * Client constructor with id
     */
    public Client( int id, String name, String email, String phoneNumber) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @param name
     * @param email
     * @param phoneNumber
     * Client constructor without id
     */
    public Client(  String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * getter for the client id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * setter for the client id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for the client name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * setter for the client name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     *getter for the email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for the email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for the phone number
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * setter for the phone number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * override of the toString method, for printing the client information
     * @return
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
