package christmas.domain;

import christmas.dto.event.Event;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private List<Event> events;

    public EventManager(Order order) {
        this.events = initializeEvent(order);
    }
    private List<Event> initializeEvent(Order order){
        if(order.isEventSubject()){
            return List.of();
        }
        List<Event> list = new ArrayList<>();
        checkAppliedEvents(order,list);
        return list;
    }
    private void checkAppliedEvents(Order order,List<Event> events){
        registerDdayDiscount(order.getVisitDate(),events);
        registerWeekdayDiscount(order,events);
        registerWeekendDiscount(order,events);
        registerSpecialDiscount(order,events);
        registerPresentEvent(order,events);
    }

    private void registerDdayDiscount(int visitDate,List<Event> events){

    }
    private void registerWeekdayDiscount(Order order,List<Event> events){

    }
    private void registerWeekendDiscount(Order order,List<Event> events){

    }
    private void registerSpecialDiscount(Order order,List<Event> events){

    }
    private void registerPresentEvent(Order order,List<Event> events){

    }


}
