package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.validator.InputValidator;

public class InputView {

    private InputValidator validator = new InputValidator();

    public void printGreetingMessage() {
        System.out.println("안녕하세요. W편의점입니다.\n"
                + "현재 보유하고 있는 상품입니다.\n");
    }

    public void requestPurchase() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
    }

    public String getPurchaseList() {
        String plist = Console.readLine();
        try {
            InputValidator.validateProductFormat(plist);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPurchaseList();
        }
        return plist;
    }

    public String plusTwoGetUserInput(String productName) {
        System.out.println("현재 " + productName + "은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
        String userInput = Console.readLine();
        try {
            InputValidator.validateYesNoInput(userInput, "무료 추가");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return plusTwoGetUserInput(productName);
        }
        return userInput;
    }

    public void requestMembershipDiscount() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
    }

    public String inputMembershipDiscount() {
        String inputMembership = Console.readLine();
        try {
            InputValidator.validateYesNoInput(inputMembership, "멤버십 할인");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputMembershipDiscount();
        }
        return inputMembership;
    }

    public void requestPurchaseAgain() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
    }

    public String inputPurchaseAgain() {
        String input = Console.readLine();
        try {
            InputValidator.validateYesNoInput(input, "다시 구매");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPurchaseAgain();
        }
        return input;
    }
}
