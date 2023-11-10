package christmas.util;

import static christmas.util.ErrorMessage.INVALID_DATE_RANGE;

public class Validator {

    public static void validateDate(String dateInput) {
        if (!(isNumericInput(dateInput) && isValidDateRange(dateInput))) {
            throw new IllegalArgumentException(INVALID_DATE_RANGE.getMessage());
        }
    }

    private static boolean isNumericInput(String input) {
        return input.matches("^[0-9]+$");
    }

    private static boolean isValidDateRange(String input) {
        int day = Integer.parseInt(input);
        return day>=1&&day<=31;
    }


}
