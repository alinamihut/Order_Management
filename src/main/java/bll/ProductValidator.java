package bll;

import model.Product;
import presentation.View;

public class ProductValidator implements Validator<Product>{
    /**
     * Override of the validate() method - verifies if the number representing the price and the stock of the product
     * to be inserted in the database is valid and if not, the method throws an exception
     * */

    @Override
    public void validate(Product product) {
        if (product.getPrice() <= 0){
            View.showAlert("Price is not valid!");
            throw new IllegalArgumentException("Price is not valid!");
        }
        if (product.getStock() <= 0){
            View.showAlert("Stock is not valid!");
            throw new IllegalArgumentException("Stock is not valid!");
        }
    }
}
