package christmas.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ValidatorTest {
    @ValueSource(strings = {"41", "dsf", "-123"})
    @ParameterizedTest
    void 날짜_입력_검증_테스트_예외상황(String date){
        assertThatThrownBy(()->{
            Validator.validateDate(date);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("유효하지 않은 날짜입니다");
    }
    @Test
    void 날짜_입력_검증_테스트_정상입력값(){
        String date = "15";
        assertThatCode(()->{
            Validator.validateDate(date);
        }).doesNotThrowAnyException();
    }

}