package store.util.fileparser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import store.model.Product;

public class ProductExtractor {

    private static List<Product> promoItems = new ArrayList<>();
    private static List<Product> nonPromoItems = new ArrayList<>();

    public static List<Product> extractProductsFromMarkdown(String filePath) {
        promoItems.clear();
        nonPromoItems.clear();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    int price = Integer.parseInt(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    String promotion = parts[3].trim();

                    Product product = new Product(name, price, quantity, promotion);

                    if (!promotion.equals("null")) {
                        promoItems.add(product);
                    } else {
                        nonPromoItems.add(product);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>(promoItems);
    }

    public static List<Product> getPromoItems() {
        return promoItems;
    }

    public static List<Product> getNonPromoItems() {
        return nonPromoItems;
    }
}
