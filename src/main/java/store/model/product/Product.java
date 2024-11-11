package store.model.product;
// name,price,quantity,promotion
public abstract class Product {
    protected String name;
    protected int price;
    protected int amount;




    public Product(String name, int price, int amount){
        this.name = name;
        this.price = price;
        this.amount = amount;


    }

    public String getName() {
        return name;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "PurchaseProduct{name='" + name + "', amount=";
    }

}
