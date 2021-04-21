package bll;

import model.Product;
import presentation.View;

public class ProductValidator implements Validator<Product>{


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
