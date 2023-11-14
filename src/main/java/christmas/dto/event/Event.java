package christmas.dto.event;

public class Event {
    private EventCategory eventCategory;
    private int discountAmount;

    public Event(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }
    public Event(EventCategory eventCategory, int discountAmount) {
        this.eventCategory = eventCategory;
        this.discountAmount = discountAmount;
    }

}
