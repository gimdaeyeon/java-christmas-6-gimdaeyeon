package christmas.domain;

import christmas.dto.event.Event;
import christmas.dto.event.EventCategory;
import christmas.util.EventDate;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static christmas.data.MenuData.MENU_DELIMITER;
import static christmas.dto.event.EventBadge.getBadgeToBenefitAmount;
import static christmas.util.EventDate.EVENT_YEAR;

public class EventManager {

    private final List<Event> events;

    private static final int D_DAY_DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final int D_DAY_ADDITIONAL_DISCOUNT_AMOUNT = 100;
    private static final int D_DAY_LAST_DATE = 25;
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;
    private static final int GIFT_EVENT_THRESHOLD = 120000;
    private static final int CHAMPAGNE_PRICES = 25000;

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String BENEFIT_HEADER = "<혜택 내역>"+LINE_SEPARATOR;
    private static final String BENEFIT_AMOUNT_HEADER = "<총혜택 금액>"+LINE_SEPARATOR;
    private static final String GIFT_HEADER = "<증정 메뉴>"+LINE_SEPARATOR;
    private static final String EVENT_BADGE_HEADER = "<12월 이벤트 배지>"+LINE_SEPARATOR;
    private static final String GIFT_PRODUCT = "샴페인 1개"+LINE_SEPARATOR;
    public static final String NOTTING = "없음"+LINE_SEPARATOR;


    public EventManager(Order order) {
        this.events = initializeEvent(order);
    }

    private List<Event> initializeEvent(Order order) {
        if (!order.isEventSubject()) {
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
        System.out.println(order.getDessertCount());
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
    public int getTotalBenefitAmount(){
        return events.stream()
                .mapToInt(Event::discountAmount).sum();
    }
    public int getTotalDiscountAmount(){
        return events.stream()
                .filter(i->i.eventCategory()!=EventCategory.GIFT)
                .mapToInt(Event::discountAmount).sum();
    }
    public String formatTotalBenefitAmountSummary(){
        int totalBenefitAmount = -getTotalBenefitAmount();
        return String.format("%s%s원",BENEFIT_AMOUNT_HEADER, NumberFormat.getNumberInstance()
                .format(totalBenefitAmount));
    }

    public String formatBenefitSummary(){
        if(events.isEmpty()){
            return BENEFIT_HEADER + NOTTING;
        }
        return BENEFIT_HEADER +
                events.stream().map(Event::toString)
                        .collect(Collectors.joining(MENU_DELIMITER))
                +MENU_DELIMITER;
    }

    public String formatGiftSummary(){
        if (isContainGiftEvent()){
            return GIFT_HEADER+GIFT_PRODUCT;
        }
        return GIFT_HEADER+NOTTING;
    }
    public boolean isContainGiftEvent(){
        return events.stream().map(Event::eventCategory)
                .anyMatch(e -> e == EventCategory.GIFT);
    }
    public String formatEventBadgeSummary(){
        int benefitAmount = getTotalBenefitAmount();
        return EVENT_BADGE_HEADER+getBadgeToBenefitAmount(benefitAmount);
    }

}
