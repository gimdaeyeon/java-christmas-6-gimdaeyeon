package christmas.data;

import java.util.List;

public class SpecialDiscountDate {
    private List<Integer> dates;
    public SpecialDiscountDate() {
        this.dates = List.of(3,10,17,24,25,31);
    }
    public boolean isContainsDate(int date){
        return dates.contains(date);
    }
}
