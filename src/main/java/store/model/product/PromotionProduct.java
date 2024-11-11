package store.model.product;

import java.text.DecimalFormat;

public class PromotionProduct extends Product{
    private String promotion;

    public PromotionProduct(String name, int price, int quantity, String promotion) {
        super(name, price, quantity);
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("###,###");

        if (amount == 0) {
            return "-" + " " + name + " " + formatter.format(price)+"원" + " 재고 없음 " + promotion;
        }

        // amount가 0이 아니면 정상적으로 출력
        return "-" + " " + name + " " + formatter.format(price) + "원 " + amount + "개 " + promotion;
    }


    public String getPromotionType() {
        return promotion;
    }

    @Override
    public int getAmount() {
        return super.getAmount();
    }
}
