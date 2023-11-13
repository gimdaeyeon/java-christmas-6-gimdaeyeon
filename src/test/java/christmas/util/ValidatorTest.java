package christmas.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorTest {
    @ValueSource(strings = {"41", "dsf", "-123"})
    @ParameterizedTest
    void 날짜_입력_검증_테스트_예외상황(String date) {
        assertThatThrownBy(() -> {
            Validator.validateDate(date);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("유효하지 않은 날짜입니다");
    }

    @Test
    void 날짜_입력_검증_테스트_정상입력값() {
        String date = "15";
        assertThatCode(() -> {
            Validator.validateDate(date);
        }).doesNotThrowAnyException();
    }

    @ValueSource(strings = {"소고기파스타-2,레드와인-1,초코케이크-1",
            "제로콜라-2,레드와인-1",
    "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-21",
    "티본스테이크-5,바비큐립-5,초코케이크-5,제로콜라-6",
    "티본스테이크-1,티본스테이크-1,초코케이크-2,제로콜라-1"})
    @ParameterizedTest
    void 주문_입력_검증_테스트_예외상황(String order) {
        assertThatThrownBy(() -> {
            Validator.validateOrder(order);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("유효하지 않은 주문입니다");
    }

    @Test
    void 메뉴주문_입력_검증_테스트_정상입력값() {
        String order = "티본스테이크-2,양송이수프-4,바비큐립-1,초코케이크-2,샴페인-3";
        assertThatCode(() -> {
            Validator.validateOrder(order);
        }).doesNotThrowAnyException();
    }

}