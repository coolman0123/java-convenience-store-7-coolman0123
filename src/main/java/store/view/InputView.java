package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public void printGreetingMessage(){
        System.out.println("안녕하세요. W편의점입니다.\n"
                + "현재 보유하고 있는 상품입니다.\n");
    }

    public void requestPurchase(){
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
    }

    public String getPurcahseList(){
        String purchaseInput = Console.readLine();
        return purchaseInput;
    }





}
