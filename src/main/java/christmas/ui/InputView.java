package christmas.ui;

import camp.nextstep.edu.missionutils.Console;

import static christmas.util.Validator.validateDate;
import static christmas.util.Validator.validateOrder;

public class InputView {
    public static final String INPUT_DATE_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
            "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String INPUT_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    public int inputDate(){
        System.out.println(INPUT_DATE_MESSAGE);
        String input = null;
        while (true) {
            input = Console.readLine();
            try {
                validateDate(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return Integer.parseInt(input);
    }

    public String inputOrder(){
        System.out.println(INPUT_ORDER_MESSAGE);
        String input = null;
        while (true) {
            input = Console.readLine();
            try {
                validateOrder(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }



}
