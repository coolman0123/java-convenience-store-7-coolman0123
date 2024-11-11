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


}


