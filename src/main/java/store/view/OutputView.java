package store.view;

import java.util.List;
import store.model.payment.Payment;
import store.model.payment.PurchaseProductDetails;
import store.model.product.GiftProduct;
import store.model.product.Product;

public class OutputView {


    public void printFileContents(List<Product> totalItemslist) {
        totalItemslist.forEach(System.out::println);
        System.out.println();
    }


    public void printReceipt(List<PurchaseProductDetails> receiptDetails) {
        System.out.println("==============W 편의점==============");

        // 헤더 출력
        String header = String.format("%-15s\t%-6s\t%-10s", "상품명", "수량", "금액");
        System.out.println(header);

        // 각 상품에 대한 세부 사항 출력
        for (PurchaseProductDetails details : receiptDetails) {
            System.out.println(String.format("%-15s\t%-6d\t%,d",
                    details.getProductName(),
                    details.getQuantity(),
                    details.getTotalPrice()));
        }


    }

    public void printGift(List<GiftProduct> giftProducts) {
        System.out.println("==============증   정==============");
        for (GiftProduct gift : giftProducts) {
            System.out.printf("%-15s\t%d\n", gift.getName(), gift.getAmount());
        }
        System.out.println("====================================");


    }

    public void printPay() {
        System.out.println(String.format("%-15s\t%-6s\t%,d", "총구매액", "", Payment.getRealTotal()));
    }

    public void printGiftProducts(int discount) {
        // 행사할인 출력 부분 수정
        System.out.printf("행사할인\t\t\t%-10s\t-%,d\n", "", Math.abs(discount));  // 행사할인 출력
    }

    public void printMembershipDiscount(int discount) {
        System.out.printf("멤버십할인\t\t\t%-7s\t-%,d\n", "", Math.abs(discount));
    }

    public void printMoneyToPay(int lastprice) {
        System.out.printf("내실돈\t\t\t%-10s\t%,d\n", "", lastprice);
    }
}


