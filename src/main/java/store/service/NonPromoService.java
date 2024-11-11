package store.service;

import store.model.payment.NonPromoTotalPayment;
import store.model.product.PromotionProduct;
import store.model.product.RegularProduct;

public class NonPromoService {
    public NonPromoTotalPayment nonPromoTotalPayment = new NonPromoTotalPayment();

    public int restPromo(PromotionProduct promotionProduct, int purchasAmount) {
        int nowProductAmount = promotionProduct.getAmount();

        if (purchasAmount <= nowProductAmount) {
            promotionProduct.setAmount(nowProductAmount - purchasAmount);
            nonPromoTotalPayment.setTotalPayment(promotionProduct.getPrice() * purchasAmount);
            return 0;
        }

        promotionProduct.setAmount(0);
        purchasAmount = purchasAmount - nowProductAmount;
        nonPromoTotalPayment.setTotalPayment(promotionProduct.getPrice() * nowProductAmount);
        return purchasAmount;
    }

    public int nonPromoSell(RegularProduct regularProduct, int purchaseQuantity) {
        int amountProduct = regularProduct.getAmount();
        int nonPromoPayment = nonPromoTotalPayment.getTotalPayment();
        if (purchaseQuantity <= amountProduct){
            regularProduct.setAmount(amountProduct - purchaseQuantity);
            nonPromoTotalPayment.setTotalPayment(nonPromoPayment + purchaseQuantity * regularProduct.getPrice());

            return nonPromoPayment;
        }

        System.out.println("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        return 0;
    }


}
