package store.model.payment;

import java.util.ArrayList;
import java.util.List;
import store.model.product.Product;
import store.model.product.PurchaseProduct;

public class Payment {
    int totalpayment;

    static int realtotal = 0;
    static int setPurchaseQuantity = 0;

    public List<PurchaseProductDetails> calculateTotalPay(List<Product> totalItemsList, List<PurchaseProduct> products) {
        List<PurchaseProductDetails> receiptDetails = new ArrayList<>();

        for (PurchaseProduct purchaseProduct : products) {
            String productName = purchaseProduct.getName();
            int purchaseQuantity = purchaseProduct.getAmount();
            int totalPaymentForItem = 0;

            for (Product item : totalItemsList) {
                if (item.getName().equals(productName)) {
                    int payment = item.getPrice();
                    int finalQuantity = purchaseQuantity + purchaseProduct.getExtra();

                    totalPaymentForItem = payment * finalQuantity;
                    totalpayment = payment * finalQuantity;

                    receiptDetails.add(new PurchaseProductDetails(productName, finalQuantity, totalPaymentForItem));

                    realtotal += totalPaymentForItem;

                    break;
                }
            }
        }

        return receiptDetails;
    }


    public static int getRealTotal() {
        return realtotal;
    }


    public static void resetRealTotal() {
        realtotal = 0;
    }
}
