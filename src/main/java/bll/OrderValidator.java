package bll;

import model.Order;
import model.Product;
import presentation.View;

public class OrderValidator implements Validator<Order>{
    @Override
    public void validate(Order order) {
            if (order.getQuantity() <= 0){
                View.showAlert("Please enter a valid quantity!");
                throw new IllegalArgumentException("Please enter a valid quantity!");
            }
        }
    }
