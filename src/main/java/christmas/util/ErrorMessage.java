package christmas.util;

public enum ErrorMessage {
    INVALID_DATE_RANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_NUMBER_FORMAT("숫자만 입력해 주세요"),
    INVALID_PURCHASE_AMOUNT("금액은 1000단위로 입력해 주세요"),
    INVALID_NUMBER_COUNT("당첨 번호는 6자리로 입력해 주세요"),
    INVALID_DUPLICATE_VALUE("당첨 번호에 중복이 허용되지 않습니다");

    private final String message;

    ErrorMessage(String message) {
        String ERROR_MARK = "[ERROR] ";
        this.message = ERROR_MARK + message;
    }

    public String getMessage() {
        return message;
    }
}
