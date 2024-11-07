package store;

import java.util.List;  // AWT List -> util List로 수정

import store.model.Product;
import store.util.fileparser.ProductExtractor;
import store.util.fileparser.ProductSearch;

public class Application {

    public static void main(String[] args) {

        String filePath = "src/main/resources/products.md";
        List<Product> promoItems = ProductExtractor.extractProductsFromMarkdown(filePath);
        List<Product> nonPromoItems = ProductExtractor.getNonPromoItems();

        ProductSearch productSearch = new ProductSearch(promoItems, nonPromoItems);

        System.out.println("Promotion이 있는 상품들:");
        promoItems.forEach(System.out::println);

        System.out.println("\nPromotion이 없는 상품들:");
        nonPromoItems.forEach(System.out::println);

        String searchName = "콜라";
        productSearch.searchAndPrintProduct(searchName);

        productSearch.purchaseProduct(searchName);

        System.out.println("\n구매 후 결과:");
        promoItems.forEach(System.out::println);
        nonPromoItems.forEach(System.out::println);
    }
}
