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

    public Client( int id, String name, String email, String phoneNumber) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public Client(  String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

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
