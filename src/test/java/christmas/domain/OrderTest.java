package christmas.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderTest {
    @Test
    void 주문내역_출력_테스트(){
        String order = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        String result = "<주문 메뉴>\n" +
                "티본스테이크 1개\n" +
                "바비큐립 1개\n" +
                "초코케이크 2개\n" +
                "제로콜라 1개\n";

        assertThat(new Order(order,12).formatOrderSummary())
                .isEqualTo(result);
    }


}