package bll;

import dao.ClientDAO;
import dao.OrderDAO;
import model.Client;
import model.Order;
import presentation.View;

import java.util.ArrayList;
import java.util.List;
/**
 * Order BLL class - contains insertOrder(Order order) method which encapsulates the application logic for validating
 * and inserting a new entry in the orders table
 **/
public class OrderBLL {
    private List<Validator<Order>> validators;
    public OrderBLL() {
        validators = new ArrayList<>();
        validators.add(new OrderValidator());
    }

    /**
     * application logic for inseting an order in the table
     * @param order
     */
    public void insertOrder(Order order) {
        for (Validator<Order> v : validators) {
            v.validate(order);
        }
        if (OrderDAO.insert(order)!=-1){
            View.showAlert("The order was inserted successfully!");
        }
    }

}
