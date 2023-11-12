package christmas.util;

import static christmas.util.ErrorMessage.INVALID_DATE_RANGE;
import static christmas.util.ErrorMessage.INVALID_ORDER_FORMAT;

public class Validator {

    public static void validateDate(String dateInput) {
        if (!(isNumericInput(dateInput) && isValidDateRange(dateInput))) {
            throw new IllegalArgumentException(INVALID_DATE_RANGE.getMessage());
        }
    }

    private static boolean isNumericInput(String input) {
        return input.matches("^[0-9]+$");
    }

    private static boolean isValidDateRange(String input) {
        int day = Integer.parseInt(input);
//        이 부분의 매직 넘버 나중에 상수로 교체
        return day >= 1 && day <= 31;
    }

//    public static void validateOrder(String orderInput) {
//        if (true) {
//            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
//        }
//    }


}
