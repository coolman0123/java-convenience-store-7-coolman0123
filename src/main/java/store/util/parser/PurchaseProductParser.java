package store.util.parser;

import java.util.ArrayList;
import java.util.List;
import store.model.product.PurchaseProduct;

public class PurchaseProductParser {
    public static List<PurchaseProduct> parse(String input) {
        List<PurchaseProduct> products = new ArrayList<>();

        String[] items = input.split("\\],\\[");

        for (String item : items) {
            item = item.replaceAll("[\\[\\]]", "");

            String[] parts = item.split("-");
            if (parts.length == 2) {
                String name = parts[0];
                int amount = Integer.parseInt(parts[1]);
                products.add(new PurchaseProduct(name, amount));
            }
        }
        return products;
    }
}