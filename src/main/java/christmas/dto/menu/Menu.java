package christmas.dto.menu;

import java.text.NumberFormat;

public record Menu(String menuName, Integer menuPrice, MenuCategory menuCategory) {

    public String getMenuPriceFormat() {
        return NumberFormat.getNumberInstance().format(menuPrice);
    }
}
