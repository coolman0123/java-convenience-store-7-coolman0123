package store.model.product;

public class GiftProduct {
    private String name;


    private int amount;
    private int price;

    public GiftProduct(String name, int amount, int price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public int getTotalPrice() {
        return amount * price;
    }

    @Override
    public String toString() {
        return "giftProduct{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }





}
