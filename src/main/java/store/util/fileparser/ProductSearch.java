package store.util.fileparser;

import java.util.List;
import store.model.Product;

public class ProductSearch {

    private List<Product> promoItems;
    private List<Product> nonPromoItems;

    public ProductSearch(List<Product> promoItems, List<Product> nonPromoItems) {
        this.promoItems = promoItems;
        this.nonPromoItems = nonPromoItems;
    }

    // 상품 검색 및 출력
    public void searchAndPrintProduct(String name) {
        System.out.println("비프로모션 상품 목록에서 검색:");
        boolean foundNonPromo = searchInList(nonPromoItems, name);

        System.out.println("\n프로모션 상품 목록에서 검색:");
        boolean foundPromo = searchInList(promoItems, name);

        if (!foundNonPromo && !foundPromo) {
            System.out.println("해당 이름의 상품을 찾을 수 없습니다: " + name);
        }
    }

    private boolean searchInList(List<Product> list, String name) {
        boolean found = false;
        for (Product product : list) {
            if (product.getName().equalsIgnoreCase(name)) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("해당 목록에서 상품을 찾을 수 없습니다.");
        }
        return found;
    }

    // 상품을 구매하고 재고를 차감하는 메서드
    public void purchaseProduct(String name) {
        boolean found = false;

        // Promotion이 있는 상품에서 우선 검색
        for (Product product : promoItems) {
            if (product.getName().equalsIgnoreCase(name)) {
                if (product.purchase()) {
                    System.out.println("Promotion 상품을 구매하였습니다: " + product);
                } else {
                    System.out.println("Promotion 상품의 재고가 부족합니다: " + product);
                }
                found = true;
                break; // Promotion에서 찾으면 더 이상 검색하지 않음
            }
        }

        // Promotion이 없는 상품에서 검색 (Promotion 상품이 없다면 NonPromo에서 재고 차감)
        if (!found) {
            for (Product product : nonPromoItems) {
                if (product.getName().equalsIgnoreCase(name)) {
                    if (product.purchase()) {
                        System.out.println("Non-Promotion 상품을 구매하였습니다: " + product);
                    } else {
                        System.out.println("Non-Promotion 상품의 재고가 부족합니다: " + product);
                    }
                    found = true;
                    break; // Non-Promotion에서 찾으면 더 이상 검색하지 않음
                }
            }
        }

        if (!found) {
            System.out.println("해당 이름의 상품이 존재하지 않습니다: " + name);
        }
    }
}
