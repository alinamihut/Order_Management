package bll;

import model.Client;
import presentation.View;

public class PhoneValidator implements Validator<Client>{
    @Override
    public void validate(Client client) {
        if (!client.getPhoneNumber().matches("^\\d{10}$")){
            View.showAlert("Please enter a valid phone number!");
            throw new IllegalArgumentException("Phone number is not valid!");
        }

    }
}
