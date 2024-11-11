package store.util.reader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import store.model.product.Product;
import store.model.product.PromotionProduct;
import store.model.product.RegularProduct;

public class ProductFileReader {
    private List<Product> totalItemslist = new ArrayList<>();

    private String filePath = "src/main/resources/products.md";

    public List<Product> extractProductsFromMarkdown() {
        totalItemslist.clear();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (int i = 1; i < lines.size(); i++) {
                processLine(lines.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(totalItemslist);
    }

    private void processLine(String line) {
        String[] parts = line.split(",");

        String name = parts[0].trim();
        int price = Integer.parseInt(parts[1].trim());
        int quantity = Integer.parseInt(parts[2].trim());
        String promotion = parts[3].trim();

        RegularProduct existingRegularProduct = findRegularProductByName(name);

        if (promotion.equals("null")) {
            if (existingRegularProduct == null) {
                existingRegularProduct = new RegularProduct(name, price, quantity);
                totalItemslist.add(existingRegularProduct);
            } else {
                existingRegularProduct.setAmount(quantity);
            }
        } else {
            PromotionProduct promotionProduct = new PromotionProduct(name, price, quantity, promotion);
            totalItemslist.add(promotionProduct);
            if (existingRegularProduct == null) {
                RegularProduct fallbackProduct = new RegularProduct(name, price, 0);
                totalItemslist.add(fallbackProduct);
            }
        }
    }

    private RegularProduct findRegularProductByName(String name) {
        for (Product product : totalItemslist) {
            if (product instanceof RegularProduct && product.getName().equals(name)) {
                return (RegularProduct) product;
            }
        }
        return null;
    }
}
