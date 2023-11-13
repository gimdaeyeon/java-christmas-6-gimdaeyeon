package christmas.domain;

import christmas.data.MenuData;
import christmas.dto.menu.Menu;
import christmas.dto.menu.MenuCategory;

import java.util.HashMap;
import java.util.Map;

import static christmas.data.MenuData.*;

public class MenuBoard {

    private static Map<String, Menu> menus;

    static{
        menus = initializeMenuMap(MenuData.selectAll());
    }

    private static Map<String, Menu> initializeMenuMap(String menuData) {
        Map<String, Menu> map = new HashMap<>();
        parseAndAddMenus(menuData.split(MENU_DELIMITER), map);
        return map;
    }

    private static void parseAndAddMenus(String[] menus, Map<String, Menu> map) {
        for (String menu : menus) {
            createAndAddMenu(menu.split(DATA_DELIMITER), map);
        }
    }

    private static void createAndAddMenu(String[] menu, Map<String, Menu> map) {
        String name = menu[NAME_INDEX];
        int price = Integer.parseInt(menu[PRICE_INDEX]);
        MenuCategory menuCategory = MenuCategory.fromDescription(menu[CATEGORY_INDEX]);
        map.put(name, new Menu(name, price, menuCategory));
    }

    public boolean isContains(String menuName){
        return menus.containsKey(menuName);
    }
    public Menu getMenu(String name){
        return menus.get(name);
    }



    @Override
    public String toString() {
        return menus.toString();
    }
}
