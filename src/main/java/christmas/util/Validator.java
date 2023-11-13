package christmas.util;

import christmas.data.MenuData;
import christmas.domain.MenuBoard;
import christmas.dto.menu.MenuCategory;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static christmas.data.MenuData.DATA_DELIMITER;
import static christmas.data.MenuData.INPUT_DATA_DELIMITER;
import static christmas.util.ErrorMessage.INVALID_DATE_RANGE;
import static christmas.util.ErrorMessage.INVALID_ORDER_FORMAT;

public class Validator {

    private static MenuBoard menuBoard;
    private static final String regex = "^([가-힣a-zA-Z]+-\\d+)(,[가-힣a-zA-Z]+-\\d+)*$";

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
//        주문형식이 맞는지 정규표현식으로 검사
        if(!Pattern.matches(regex,orderInput)){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }

        String[] orders = orderInput.split(DATA_DELIMITER);
        List<String> menuNames =getMenuNameValues(orders);
        List<Integer> menuCounts = getOrderCountValues(orders);
        if (!(isMenuListed(menuNames))) {
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
        if((checkIfOnlyBeverages(menuNames))){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private static boolean isMenuListed(List<String> orderMenus) {
        return orderMenus.stream()
                .allMatch(menuBoard::isContains);
    }

    private static boolean checkIfOnlyBeverages(List<String> orderMenus) {
        return orderMenus.stream()
                .map(menuBoard::getMenu)
                .allMatch(menu -> menu.menuCategory() == MenuCategory.BEVERAGE);
    }

    private static List<String> getMenuNameValues(String[] orders){
        return Arrays.stream(orders)
                .map(menu -> menu.substring(0, menu.indexOf(INPUT_DATA_DELIMITER)))
                .toList();
    }

    private static List<Integer> getOrderCountValues(String[] orders){
        return Arrays.stream(orders)
                .map(menu -> menu.substring(menu.indexOf(INPUT_DATA_DELIMITER)+1, menu.length()))
                .map(Integer::parseInt)
                .toList();
    }


}
