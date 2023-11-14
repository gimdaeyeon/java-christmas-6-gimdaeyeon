package christmas.util;

import java.time.LocalDate;
import java.time.YearMonth;

public class EventDate {
        public static final int EVENT_YEAR = 2023;
        public static final int EVENT_MONTH = 12;

        public static int getEventStartDate(){
            return  YearMonth.of(EVENT_YEAR, EVENT_MONTH).atDay(1).getDayOfMonth();
        }

        public static int getEventEndDate(){
            return YearMonth.of(EVENT_YEAR, EVENT_MONTH).atEndOfMonth().getDayOfMonth();
        }




}
