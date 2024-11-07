package store.model;

public class Product {
    private String name;
    private int price;
    private int quantity;
    private String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPromotion() {
        return promotion;
    }

    @Override
    public String toString() {
        return "상품명: " + name + ", 가격: " + price + ", 수량: " + quantity + ", 프로모션: " + promotion;
    }

    // 구매 메서드: 수량을 감소시킴
    public boolean purchase() {
        if (this.quantity > 0) {
            this.quantity--;  // 구매 시 수량 감소
            return true;  // 구매 성공
        }
        return false;  // 재고 부족
    }
}
