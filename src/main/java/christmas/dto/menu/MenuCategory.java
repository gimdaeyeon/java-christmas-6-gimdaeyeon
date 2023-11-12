package christmas.dto.menu;

import static christmas.util.ErrorMessage.INVALID_CATEGORY_DESCRIPTION;

public enum MenuCategory {
    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료");

    private final String displayName;

    MenuCategory(String displayName) {
        this.displayName = displayName;
    }

        public static MenuCategory fromDescription(String description){
        if(description.equals(APPETIZER.displayName)){
            return APPETIZER;
        }
        if(description.equals(MAIN.displayName)){
            return MAIN;
        }
        if(description.equals(DESSERT.displayName)){
            return DESSERT;
        }
        if(description.equals(BEVERAGE.displayName)){
            return BEVERAGE;
        }
        throw new IllegalArgumentException(INVALID_CATEGORY_DESCRIPTION.getMessage());
    }

    public String getDisplayName() {
        return displayName;
    }
}
