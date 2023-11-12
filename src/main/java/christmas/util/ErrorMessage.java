package christmas.util;

public enum ErrorMessage {
    INVALID_DATE_RANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER_FORMAT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_CATEGORY_DESCRIPTION("유효하지 않은 카테코리 이름입니다");

    private final String message;

    ErrorMessage(String message) {
        String ERROR_MARK = "[ERROR] ";
        this.message = ERROR_MARK + message;
    }

    public String getMessage() {
        return message;
    }
}
