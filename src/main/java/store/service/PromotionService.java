package store.service;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.DateTimes;
import store.model.product.GiftProduct;
import store.model.product.PromotionProduct;
import store.view.InputView;
import store.model.promotion.Promotion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PromotionService {
    private final InputView inputView = new InputView();
    private List<GiftProduct> giftProducts = new ArrayList<>();
    private List<Promotion> promotions;

    public PromotionService(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public int applyPromotion(PromotionProduct promotionProduct, int purchaseQuantity) {
        LocalDate currentDate = DateTimes.now().toLocalDate();

        Promotion activePromotion = findActivePromotion(promotionProduct.getPromotionType(), currentDate);

        if (activePromotion == null) {
            return purchaseQuantity;
        }

        return applyPromotion(promotionProduct, purchaseQuantity, activePromotion.getBuy() + activePromotion.getGet());
    }

    private Promotion findActivePromotion(String promotionType, LocalDate currentDate) {
        for (Promotion promotion : promotions) {
            if (promotion.getName().equals(promotionType) &&
                    !currentDate.isBefore(promotion.getStartDate()) &&
                    !currentDate.isAfter(promotion.getEndDate())) {
                return promotion;
            }
        }
        return null;
    }

    private int applyPromotion(PromotionProduct promotionProduct, int purchaseQuantity, int promotionFactor) {
        int promoSet = purchaseQuantity / promotionFactor;
        int restQuantity = purchaseQuantity % promotionFactor;
        int productAmount = promotionProduct.getAmount() / promotionFactor;
        int applicable = Math.min(productAmount, promoSet);
        int originalAmount = promotionProduct.getAmount();



        if (applicable >= 1) {
            promotionProduct.setAmount(originalAmount - applicable * promotionFactor);
            purchaseQuantity -= applicable * promotionFactor;
        }
        if (restQuantity == promotionProduct.getAmount()) {
            giftProducts.add(new GiftProduct(promotionProduct.getName(), applicable, promotionProduct.getPrice()));
            return purchaseQuantity;
        }

        if (restQuantity == promotionFactor - 1 && promotionProduct.getAmount() >= 1) {
            applicable = setRestPlus(applicable, promotionProduct.getName(), promotionProduct);
            if (promotionProduct.getAmount() < originalAmount) {
                promotionProduct.setAmount(promotionProduct.getAmount() - promotionFactor + 1);
                purchaseQuantity = 0;
            }
        }

        giftProducts.add(new GiftProduct(promotionProduct.getName(), applicable, promotionProduct.getPrice()));
        return purchaseQuantity;
    }

    private int setRestPlus(int applicable, String productName, PromotionProduct promotionProduct) {
        String input = inputView.plusTwoGetUserInput(productName);
        if ("Y".equalsIgnoreCase(input)) {
            promotionProduct.setAmount(promotionProduct.getAmount() - 1);

            PurchaseService.handleExtra(productName);


            return applicable + 1;
        }
        return applicable;
    }

    public List<GiftProduct> getGiftProducts() {
        return giftProducts;
    }


}
