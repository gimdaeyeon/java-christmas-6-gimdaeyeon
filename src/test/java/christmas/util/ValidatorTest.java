package christmas.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ValidatorTest {
    @ValueSource(strings = {"41", "dsf", "-123"})
    @ParameterizedTest
    void 날짜_입력_검증_테스트(String date){
        assertThatThrownBy(()->{
            Validator.validateDate(date);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("유효하지 않은 날짜입니다");
    }

}