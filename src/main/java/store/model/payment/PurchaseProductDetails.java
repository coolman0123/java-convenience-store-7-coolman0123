package store.model.payment;

public class PurchaseProductDetails {
    private String productName;
    private int quantity;
    private int totalPrice;

    public PurchaseProductDetails(String productName, int quantity, int totalPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
