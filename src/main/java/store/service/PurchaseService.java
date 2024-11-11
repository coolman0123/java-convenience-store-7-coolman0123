package store.service;

import store.model.product.GiftProduct;
import store.model.product.Product;
import store.model.product.PurchaseProduct;
import store.model.product.PromotionProduct;
import store.model.product.RegularProduct;
import java.util.List;
import store.model.promotion.Promotion;

public class PurchaseService {

    private final PromotionService promotionService;
    public final NonPromoService nonPromoService = new NonPromoService();

    private static List<PurchaseProduct> products;

    public PurchaseService(List<PurchaseProduct> products, List<Promotion> promotions) {
        this.products = products;
        this.promotionService = new PromotionService(promotions);
    }

    public void findpurchase(List<Product> totalItemsList, List<PurchaseProduct> products) {
        for (PurchaseProduct purchaseProduct : products) {
            String productName = purchaseProduct.getName();
            int extra = purchaseProduct.getExtra();
            int purchaseQuantity = purchaseProduct.getAmount();
            for (Product item : totalItemsList) {
                if (item.getName().equals(productName)) {
                    purchaseQuantity = handlePromotionProduct(item, purchaseQuantity);

                    if (purchaseQuantity > 0) {
                        handleRegularProduct(item, purchaseQuantity);
                    }
                }
            }
        }
    }

    private int handlePromotionProduct(Product item, int purchaseQuantity) {
        if (item instanceof PromotionProduct) {
            PromotionProduct promoItem = (PromotionProduct) item;
            purchaseQuantity = promotionService.applyPromotion(promoItem, purchaseQuantity);

            if (purchaseQuantity > 0 && promoItem.getAmount() > 0) {
                purchaseQuantity = nonPromoService.restPromo(promoItem, purchaseQuantity);
            }
        }
        return purchaseQuantity;
    }

    private void handleRegularProduct(Product item, int purchaseQuantity) {
        if (item instanceof RegularProduct && purchaseQuantity > 0) {
            nonPromoService.nonPromoSell((RegularProduct) item, purchaseQuantity);
        }
    }

    public List<GiftProduct> getGiftProducts() {
        return promotionService.getGiftProducts();
    }

    public static void handleExtra(String productName) {
        for (PurchaseProduct product : products) {
            if (product.getName().equals(productName)) {
                product.setExtra();
            }


        }

    }


}