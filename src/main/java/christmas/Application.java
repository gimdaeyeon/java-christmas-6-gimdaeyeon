package christmas;

import christmas.data.MenuData;
import christmas.domain.MenuBoard;
import christmas.dto.menu.Menu;
import christmas.dto.order.OrderItem;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        MenuBoard menuBoard = new MenuBoard();
        OrderItem orderItem = new OrderItem(menuBoard.getMenu("티본스테이크"),3   );
        System.out.println(orderItem);
    }
}
