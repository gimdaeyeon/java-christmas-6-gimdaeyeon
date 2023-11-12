package christmas.dto.menu;

import christmas.util.Validator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class MenuCategoryTest {

    @ParameterizedTest
    @MethodSource("provideEnumCases")
    void 카테고리_반환_테스트(String input, MenuCategory expected){
        assertThat(MenuCategory.fromDescription(input))
                .isEqualTo(expected);
    }

    static Stream<Arguments> provideEnumCases() {
        return Stream.of(
                Arguments.of("에피타이저", MenuCategory.APPETIZER),
                Arguments.of("메인", MenuCategory.MAIN),
                Arguments.of("디저트", MenuCategory.DESSERT),
                Arguments.of("음료", MenuCategory.BEVERAGE)
        );
    }


}