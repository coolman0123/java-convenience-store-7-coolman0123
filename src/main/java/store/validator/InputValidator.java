package store.validator;

public class InputValidator {

    public static void validateProductFormat(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("[Error] 상품명과 수량을 입력하지 않았습니다. 올바른 형식으로 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        }
        if (input.contains(" ")) {
            throw new IllegalArgumentException("[Error] 입력에서 띄어쓰기가 포함되었습니다. 띄어쓰기 없이 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        }

        String[] products = input.split(",");
        for (String product : products) {
            if (!product.matches("\\[[^\\]]+\\-\\d+\\]")) {
                throw new IllegalArgumentException("[Error] 잘못된 상품 형식입니다. 상품명과 수량을 올바르게 입력해 주세요. (예: [사이다-2], [감자칩-1])");
            }
        }
    }

    public static void validateYesNoInput(String input, String context) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("[Error] 입력이 비어 있습니다. " + context + "에서 Y 또는 N을 입력해 주세요.");
        }

        input = input.trim();

        if (input.equals(input.toLowerCase())) {
            throw new IllegalArgumentException("[Error] " + context + "에서 소문자를 입력하셨습니다. 대문자로 Y 또는 N을 입력해 주세요.");
        }

        if (!input.equals("Y") && !input.equals("N")) {
            throw new IllegalArgumentException("[Error] " + context + "에서 잘못된 입력이 발생했습니다. Y 또는 N을 입력해 주세요.");
        }
    }
}
