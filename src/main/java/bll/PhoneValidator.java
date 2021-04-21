package bll;

import model.Client;
import presentation.View;

public class PhoneValidator implements Validator<Client>{
    /**
     * Override of the validate() method - verifies with a regex if the client to be inserted or updated in the database
     * has a valid phone number and if not, the method throws an exception
     */
    @Override
    public void validate(Client client) {
        if (!client.getPhoneNumber().matches("^\\d{10}$")){
            View.showAlert("Please enter a valid phone number!");
            throw new IllegalArgumentException("Phone number is not valid!");
        }

    }
}
