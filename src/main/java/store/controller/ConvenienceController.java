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
            inputView.requestPurchase();

            String userInput = inputView.getPurcahseList();
            List<PurchaseProduct> purchaseProducts = PurchaseProductParser.parse(userInput);

            PurchaseService purchaseService = new PurchaseService(purchaseProducts, promotions);
            purchaseService.findpurchase(totalItemsList, purchaseProducts);
            List<GiftProduct> giftProducts = purchaseService.getGiftProducts();


            Payment payment = new Payment();
            List<PurchaseProductDetails> receiptDetails = payment.calculateTotalPay(totalItemsList, purchaseProducts);

            inputView.requestMembershipDiscount();
            String input = inputView.inputMemberShipDiscount();
            MembershipService membershipService = new MembershipService();
            int nonPromoTotal = purchaseService.nonPromoService.nonPromoTotalPayment.getTotalPayment();
            int rpdiscountamount = membershipService.goMembershipDiscount(input, nonPromoTotal);

            int firstPay = Payment.getRealTotal();

            outputView.printReceipt(receiptDetails);
            outputView.printGift(giftProducts);
            outputView.printPay();

            int discount = calculateGiftProductDiscount(giftProducts);
            outputView.printGiftProducts(discount);
            outputView.printRpdisount(rpdiscountamount);

            int lastprice = firstPay - discount - rpdiscountamount;
            outputView.printMoneyToPay(lastprice);

            Payment.resetRealTotal();

            inputView.requestPurchaseAgain();
            String againInput = inputView.inputPurchaseAgain();
            continueShopping = againInput.equalsIgnoreCase("Y");
        }
    }

    private int calculateGiftProductDiscount(List<GiftProduct> giftProducts) {
        int discountPrice = 0;
        for (GiftProduct giftProduct : giftProducts) {
            discountPrice += giftProduct.getTotalPrice();
        }
        return discountPrice;
    }
}
