package helper;

public class Product {

    private String itemName;
    private int itemCount;
    private double itemPrice;

    public Product(String itemName, int itemCount, double itemPrice) {
        this.itemCount = itemCount;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public double getItemPrice() {
        return itemPrice;
    }
}
