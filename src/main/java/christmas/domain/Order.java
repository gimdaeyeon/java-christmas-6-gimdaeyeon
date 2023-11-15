package christmas.domain;

import christmas.dto.event.Event;
import christmas.dto.menu.Menu;
import christmas.dto.menu.MenuCategory;
import christmas.dto.order.OrderItem;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static christmas.data.MenuData.*;
import static christmas.dto.order.OrderItem.MENU_NAME_INDEX;
import static christmas.dto.order.OrderItem.MENU_QUANTITY_INDEX;

public class Order {
    private final List<OrderItem> orderItems;
    private final int visitDate;
    private final int totalAmount;
    private EventManager eventManager;

    private final String ORDER_HEADER ="<주문 메뉴>\n";
    private final String TOTAL_AMOUNT_HEADER ="<할인 전 총주문 금액>\n";
    private final String AMOUNT_AFTER_DISCOUNT_HEADER = "<할인 후 예상 결제 금액>\n";


    public Order(String orderInput, int visitDate) {
        this.orderItems = initializeOrder(orderInput);
        this.visitDate = visitDate;
        totalAmount =calculateTotalOrderAmount();
    }

    public void createEventManager(Order order){
        eventManager = new EventManager(order);
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

    public String formatTotalOrderAmountSummary(){
        return TOTAL_AMOUNT_HEADER+
                NumberFormat.getNumberInstance()
                        .format(calculateTotalOrderAmount())+"원\n";
    }

    public String formatAmountAfterDiscount(){
        return String.format("%s%s원\n",AMOUNT_AFTER_DISCOUNT_HEADER,
                NumberFormat.getNumberInstance().format(calculateAfterDiscountAmount()));
    }

    private int calculateAfterDiscountAmount(){
        return totalAmount - eventManager.getTotalDiscountAmount();
    }


    public int calculateTotalOrderAmount(){
        return orderItems.stream()
                .mapToInt(i-> i.quantity()*i.getMenuPrice())
                .sum();
    }
    public boolean isEventSubject(){
        return totalAmount >=10000;
    }


    public int getVisitDate() {
        return visitDate;
    }

    public int getDessertCount(){
        return (int)orderItems.stream()
                .filter(i->i.getMenuCategory()== MenuCategory.DESSERT)
                .mapToInt(OrderItem::quantity)
                .sum();
    }
    public int getMainCount(){
        return (int)orderItems.stream()
                .filter(i->i.getMenuCategory()== MenuCategory.MAIN)
                .count();
    }

    public String getGiftMessage(){
        return eventManager.formatGiftSummary();
    }

    public String getBenefitMessage(){
        return eventManager.formatBenefitSummary();
    }
    public String getDiscountMessage(){
        return eventManager.formatTotalBenefitAmountSummary()+"\n";
    }

    public String getEventBadgeMessage(){
        return eventManager.formatEventBadgeSummary();
    }


}
