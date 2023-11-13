package christmas.util;

import christmas.data.MenuData;
import christmas.domain.MenuBoard;
import christmas.dto.menu.MenuCategory;

import java.util.Arrays;

import static christmas.data.MenuData.DATA_DELIMITER;
import static christmas.data.MenuData.INPUT_DATA_DELIMITER;
import static christmas.util.ErrorMessage.INVALID_DATE_RANGE;
import static christmas.util.ErrorMessage.INVALID_ORDER_FORMAT;

public class Validator {

    private static MenuBoard menuBoard;

    static{
        menuBoard = new MenuBoard();
    }

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

    public static void validateOrder(String orderInput) {
        String[] orders = orderInput.split(DATA_DELIMITER);
        if (!(isMenuListed(orders))) {
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
        if((checkIfOnlyBeverages(orders))){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private static boolean isMenuListed(String[] orders) {
        return Arrays.stream(orders)
                .map(menu -> menu.substring(0, menu.indexOf(INPUT_DATA_DELIMITER)))
                .allMatch(menuBoard::isContains);
    }

    private static boolean checkIfOnlyBeverages(String[] orders) {
        return Arrays.stream(orders)
                .map(menu -> menu.substring(0, menu.indexOf(INPUT_DATA_DELIMITER)))
                .map(menuBoard::getMenu)
                .allMatch(menu -> menu.menuCategory() == MenuCategory.BEVERAGE);
    }

}
