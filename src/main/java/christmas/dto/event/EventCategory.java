package christmas.dto.event;

public enum EventCategory {
    D_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPACIAL("특별 할인"),
    PRESENT("증정 이벤트");

    private final String displayName;
    EventCategory(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}