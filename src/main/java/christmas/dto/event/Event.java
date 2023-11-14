package christmas.dto.event;

import java.text.NumberFormat;

public record Event(EventCategory eventCategory, int discountAmount) {
    @Override
    public String toString() {
        return String.format("%s: -%s원",eventCategory.getDisplayName(),discountAmountFormat());
    }

    public String discountAmountFormat() {
        return NumberFormat.getNumberInstance().format(discountAmount);
    }
}
