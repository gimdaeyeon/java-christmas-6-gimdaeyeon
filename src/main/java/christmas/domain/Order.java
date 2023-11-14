package christmas.domain;

import christmas.dto.menu.Menu;
import christmas.dto.order.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static christmas.data.MenuData.*;
import static christmas.dto.order.OrderItem.MENU_NAME_INDEX;
import static christmas.dto.order.OrderItem.MENU_QUANTITY_INDEX;

public class Order {
    List<OrderItem> orderItems;
    int visitDate;
    int totalAmount;
    int discountAmount;

    private final String ORDER_HEADER ="<주문 메뉴>\n";

    public Order(String orderInput, int visitDate) {
        this.orderItems = initializeOrder(orderInput);
        this.visitDate = visitDate;
        totalAmount =calculateTotalOrderAmount();
    }

    private List<OrderItem> initializeOrder(String orderInput) {
        List<OrderItem> list = new ArrayList<>();

        addOrderItems(orderInput.split(DATA_DELIMITER), list);
        return list;
    }

    private static void addOrderItems(String[] order, List<OrderItem> orderItems) {
        for (String orderItem : order) {
            createAndAddOrderItem(orderItem.split(INPUT_DATA_DELIMITER), orderItems);
        }
    }

    private static void createAndAddOrderItem(String[] itemInfo, List<OrderItem> orderItems) {
        Menu menu = MenuBoard.getMenu(itemInfo[MENU_NAME_INDEX]);
        int menuQuantity = Integer.parseInt(itemInfo[MENU_QUANTITY_INDEX]);
        orderItems.add(new OrderItem(menu, menuQuantity));
    }

    public String formatOrderMenuSummary(){
        return ORDER_HEADER +
                orderItems.stream().map(OrderItem::toString)
                        .collect(Collectors.joining(MENU_DELIMITER))
                +MENU_DELIMITER;
    }

    public int calculateTotalOrderAmount(){
        return orderItems.stream()
                .mapToInt(i-> i.quantity()*i.getMenuPrice())
                .sum();
    }

}
