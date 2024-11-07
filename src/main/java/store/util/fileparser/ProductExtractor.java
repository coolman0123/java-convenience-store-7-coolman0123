package store.util.fileparser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProductExtractor {

    public static List<Product> extractProductsFromMarkdown(String filePath) {
        List<Product> promoItems = new ArrayList<>();
        List<Product> nonPromoItems = new ArrayList<>();

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
                        promoItems.add(product);  // promotion이 null이 아닌 경우
                    } else {
                        nonPromoItems.add(product);  // promotion이 null인 경우
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 결과를 반환하는 메서드
        return new ArrayList<>(promoItems);
    }
}
