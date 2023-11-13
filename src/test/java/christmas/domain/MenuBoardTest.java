package christmas.domain;

import christmas.data.MenuData;
import christmas.dto.menu.MenuCategory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class MenuBoardTest {

    private final MenuBoard menuBoard = new MenuBoard(MenuData.selectAll());

    @ValueSource(strings = {"타파스", "레드와인", "티본스테이크","양송이수프","시저샐러드","바비큐립"})
    @ParameterizedTest
    void 메뉴판_기본등록_테스트(String menu){
        assertThat(menuBoard.isContains(menu)).isEqualTo(true);

    }


}