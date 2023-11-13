package christmas.util;

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

    private static final MenuBoard menuBoard;
    private static final String regex = "^([가-힣a-zA-Z]+-\\d+)(,[가-힣a-zA-Z]+-\\d+)*$";
    public static final int MIN_ORDER_QUANTITY = 1;
    public static final int MAX_ORDER_QUANTITY = 20;

    static {
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
        if (!Pattern.matches(regex, orderInput)) {
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }

        String[] orders = orderInput.split(DATA_DELIMITER);
        List<String> menuNames = getMenuNameValues(orders);
        List<Integer> menuCounts = getOrderCountValues(orders);

        validateMenuListed(menuNames);
        validateMenuInputUnique(menuNames);
        validateOnlyBeverages(menuNames);
        validateMenuItemsWithinRange(menuCounts);
        validateMenuCountWithinLimit(menuCounts);
    }

    private static void validateMenuListed(List<String> menuNames){
        if(!isMenuListed(menuNames)){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }
    private static boolean isMenuListed(List<String> orderMenus) {
        return orderMenus.stream()
                .allMatch(menuBoard::isContains);
    }

    private static void validateOnlyBeverages(List<String> orderMenus){
        if(checkIfOnlyBeverages(orderMenus)){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }
    private static boolean checkIfOnlyBeverages(List<String> orderMenus) {
        return orderMenus.stream()
                .map(menuBoard::getMenu)
                .allMatch(menu -> menu.menuCategory() == MenuCategory.BEVERAGE);
    }

    private static List<String> getMenuNameValues(String[] orders) {
        return Arrays.stream(orders)
                .map(menu -> menu.substring(0, menu.indexOf(INPUT_DATA_DELIMITER)))
                .toList();
    }

    private static List<Integer> getOrderCountValues(String[] orders) {
        return Arrays.stream(orders)
                .map(menu -> menu.substring(menu.indexOf(INPUT_DATA_DELIMITER) + 1))
                .map(Integer::parseInt)
                .toList();
    }

    private static void validateMenuItemsWithinRange(List<Integer> counts){
        if(!checkMenuItemsWithinRange(counts)){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }
    private static boolean checkMenuItemsWithinRange(List<Integer> counts) {
        return counts.stream()
                .allMatch(i -> i >= MIN_ORDER_QUANTITY && i <= MAX_ORDER_QUANTITY);
    }

    private static void validateMenuCountWithinLimit(List<Integer> counts){
        if(!isMenuCountWithinLimit(counts)){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }
    private static boolean isMenuCountWithinLimit(List<Integer> counts) {
        return counts.stream().mapToInt(i -> i)
                .sum() <= MAX_ORDER_QUANTITY;
    }

    private static void validateMenuInputUnique(List<String> orderMenus){
        if(!isMenuInputUnique(orderMenus)){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }
    private static boolean isMenuInputUnique(List<String> orderMenus) {
        int size = orderMenus.size();
        return orderMenus.stream().distinct()
                .count() == size;
    }

}
