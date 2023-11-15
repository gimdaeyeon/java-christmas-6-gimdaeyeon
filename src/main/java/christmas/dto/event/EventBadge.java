package christmas.dto.event;

import static christmas.domain.EventManager.NOTTING;

public enum EventBadge {
    STAR("별",5000),
    TREE("트리",10000),
    SANTA("산타",20000);

    private final String displayName;
    private final int minimumAmountMet;
    EventBadge(String displayName, int minimumAmountMet) {
        this.displayName = displayName;
        this.minimumAmountMet = minimumAmountMet;
    }

    public int getMinimumAmountMet() {
        return minimumAmountMet;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String getBadgeToBenefitAmount(int benefitAmount){
        if(benefitAmount>= SANTA.minimumAmountMet){
            return SANTA.displayName;
        }
        if(benefitAmount>= TREE.minimumAmountMet){
            return TREE.displayName;
        }
        if(benefitAmount>= STAR.minimumAmountMet){
            return STAR.displayName;
        }
        return NOTTING;
    }


}
