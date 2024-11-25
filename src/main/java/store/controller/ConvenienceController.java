package store.controller;

import java.util.List;
import store.model.payment.Payment;
import store.model.payment.PurchaseProductDetails;
import store.model.product.GiftProduct;
import store.model.product.Product;
import store.model.product.PurchaseProduct;
import store.model.promotion.Promotion;
import store.service.MembershipService;
import store.service.PurchaseService;
import store.util.parser.PurchaseProductParser;
import store.util.reader.ProductFileReader;
import store.util.reader.PromotionFileReader;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceController {
    public void run() {
        ProductFileReader productFileReader = new ProductFileReader();
        PromotionFileReader promotionFileReader = new PromotionFileReader();

        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        List<Product> totalItemsList = productFileReader.extractProductsFromMarkdown();
        List<Promotion> promotions = promotionFileReader.getPromotions();

        boolean continueShopping = true;
        while (continueShopping) {
            inputView.printGreetingMessage();
            outputView.printFileContents(totalItemsList);

            List<PurchaseProduct> purchaseProducts;
            while (true) {
                inputView.requestPurchase();
                String userInput = inputView.getPurchaseList();
                purchaseProducts = PurchaseProductParser.parse(userInput);

                if (!validatePurchaseProducts(purchaseProducts, totalItemsList)) {
                    inputView.productNotExist();
                    continue;
                }

                break;
            }

            PurchaseService purchaseService = new PurchaseService(purchaseProducts, promotions);
            purchaseService.findpurchase(totalItemsList, purchaseProducts);
            List<GiftProduct> giftProducts = purchaseService.getGiftProducts();

            Payment payment = new Payment();
            List<PurchaseProductDetails> receiptDetails = payment.calculateTotalPay(totalItemsList, purchaseProducts);

            inputView.requestMembershipDiscount();
            String input = inputView.inputMembershipDiscount();
            MembershipService membershipService = new MembershipService();
            int nonPromoTotal = purchaseService.nonPromoService.nonPromoTotalPayment.getTotalPayment();
            int membershipDiscount = membershipService.goMembershipDiscount(input, nonPromoTotal);

            int totalPay = Payment.getRealTotal();

            outputView.printReceipt(receiptDetails);
            outputView.printGift(giftProducts);
            outputView.printPay();

            int discount = calculateGiftProductDiscount(giftProducts);
            outputView.printGiftProducts(discount);
            outputView.printMembershipDiscount(membershipDiscount);

            int discountTotalPay = totalPay - discount - membershipDiscount;
            outputView.printMoneyToPay(discountTotalPay);

            Payment.resetRealTotal();

            inputView.requestPurchaseAgain();
            String againInput = inputView.inputPurchaseAgain();
            continueShopping = againInput.equalsIgnoreCase("Y");
        }
    }

    private boolean validatePurchaseProducts(List<PurchaseProduct> purchaseProducts, List<Product> totalItemsList) {
        for (PurchaseProduct purchaseProduct : purchaseProducts) {
            boolean productExists = totalItemsList.stream()
                    .anyMatch(product -> product.getName().equalsIgnoreCase(purchaseProduct.getName()));
            if (!productExists) {
                return false;
            }
        }
        return true;
    }

    private int calculateGiftProductDiscount(List<GiftProduct> giftProducts) {
        int discountPrice = 0;
        for (GiftProduct giftProduct : giftProducts) {
            discountPrice += giftProduct.getTotalPrice();
        }
        return discountPrice;
    }
}
