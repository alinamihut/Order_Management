package bll;

import model.Order;
import model.Product;
import presentation.View;

public class OrderValidator implements Validator<Order>{
    /**
     * Override of the validate() method - verifies if the number representing the quantity of the order
     * to be inserted in the database is valid and if not, the method throws an exception
     * @param order
     */
    @Override
    public void validate(Order order) {
            if (order.getQuantity() <= 0){
                View.showAlert("Please enter a valid quantity!");
                throw new IllegalArgumentException("Please enter a valid quantity!");
            }
        }
    }
