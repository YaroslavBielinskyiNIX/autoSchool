package helper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private String itemName;
    private int itemCount;
    private double itemPrice;

    public Product(String itemName, int itemCount, double itemPrice) {
        this.itemCount = itemCount;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}
