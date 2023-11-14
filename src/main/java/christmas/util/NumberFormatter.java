package christmas.util;

import java.text.NumberFormat;

public class NumberFormatter {
    public static String format(int number) {
        return NumberFormat.getNumberInstance().format(number);
    }
}
