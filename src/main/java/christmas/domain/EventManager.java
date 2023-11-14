package christmas.domain;

import christmas.dto.event.Event;
import christmas.dto.event.EventCategory;
import christmas.util.EventDate;

import java.util.ArrayList;
import java.util.List;

import static christmas.util.EventDate.EVENT_YEAR;

public class EventManager {

    private List<Event> events;
    private static final int D_DAY_DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final int D_DAY_ADDITIONAL_DISCOUNT_AMOUNT = 100;
    private static final int D_DAY_LAST_DATE = 25;
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;
    private static final int GIFT_EVENT_THRESHOLD = 120000;
    private static final int CHAMPAGNE_PRICES = 25000;

    public EventManager(Order order) {
        this.events = initializeEvent(order);
    }

    private List<Event> initializeEvent(Order order) {
        if (order.isEventSubject()) {
            return List.of();
        }
        List<Event> list = new ArrayList<>();
        checkAppliedEvents(order, list);
        return list;
    }

    private void checkAppliedEvents(Order order, List<Event> events) {
        registerDdayDiscount(order.getVisitDate(), events);
        checkDayOfWeekAndDiscount(order, events);
        registerSpecialDiscount(order, events);
        registerPresentEvent(order, events);
    }

    private void registerDdayDiscount(int visitDate, List<Event> events) {
        if (visitDate <= D_DAY_LAST_DATE) {
            events.add(new Event(EventCategory.D_DAY, calculateDdayDiscountAmount(visitDate)));
        }
    }

    private int calculateDdayDiscountAmount(int visitDate) {
        return ((visitDate - 1) * D_DAY_ADDITIONAL_DISCOUNT_AMOUNT) + D_DAY_DEFAULT_DISCOUNT_AMOUNT;
    }

    private void checkDayOfWeekAndDiscount(Order order, List<Event> events) {
        if (EventDate.isWeekend(order.getVisitDate())) {
            registerWeekendDiscount(order, events);
            return;
        }
        registerWeekdayDiscount(order, events);
    }

    private void registerWeekdayDiscount(Order order, List<Event> events) {
        events.add(new Event(EventCategory.WEEKDAY, order.getDessertCount() * EVENT_YEAR));
    }

    private void registerWeekendDiscount(Order order, List<Event> events) {
        events.add(new Event(EventCategory.WEEKEND, order.getMainCount() * EVENT_YEAR));
    }

    private void registerSpecialDiscount(Order order, List<Event> events) {
        if (EventDate.isSpecialDiscountDate(order.getVisitDate())) {
            events.add(new Event(EventCategory.SPACIAL, SPECIAL_DISCOUNT_AMOUNT));
        }
    }

    private void registerPresentEvent(Order order, List<Event> events) {
        if(order.calculateTotalOrderAmount()>=GIFT_EVENT_THRESHOLD){
            events.add(new Event(EventCategory.GIFT,CHAMPAGNE_PRICES));
        }
    }

    private int getTotalDiscountAmount(List<Event> events){
        return events.stream().mapToInt(Event::discountAmount).sum();
    }


}
