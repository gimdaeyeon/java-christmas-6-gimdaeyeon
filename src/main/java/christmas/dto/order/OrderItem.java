package christmas.dto.order;

import christmas.dto.menu.Menu;

public record OrderItem(Menu menu, int quantity) {
    public static final int MENU_NAME_INDEX = 0;
    public static final int MENU_QUANTITY_INDEX = 1;

    public int getMenuPrice(){
        return menu.menuPrice();
    }
    @Override
    public String toString() {
        return String.format("%s %d개",menu.menuName(),quantity);
    }
}
