package store.model.product;

public class PurchaseProduct {
    String name;
    int amount;
    private int extra = 0;

    public PurchaseProduct(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getExtra() {
        return extra;
    }

    public void setExtra() {
        this.extra = 1;
    }

    @Override
    public String toString() {
        return "PurchaseProduct{name='" + name + "', amount=" + amount + ", extra=" + extra + "}";
    }
}
