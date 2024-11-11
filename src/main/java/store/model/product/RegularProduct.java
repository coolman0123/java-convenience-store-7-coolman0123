package store.model.product;

import java.text.DecimalFormat;

public class RegularProduct extends Product{
    public RegularProduct(String name, int price, int quantity ) {
        super(name, price, quantity);
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("###,###");

        if (amount == 0) {
            return "-" + " " + name + " " + formatter.format(price) +"원" +" 재고 없음";
        }

        // amount가 0이 아니면 정상적으로 출력
        return "-" + " " + name + " " + formatter.format(price) + "원 " + amount + "개";
    }


    @Override
    public int getAmount() {
        return super.getAmount();
    }
}
