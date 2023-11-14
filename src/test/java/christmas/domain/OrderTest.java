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

        assertThat(new Order(order,12).formatOrderMenuSummary())
                .isEqualTo(result);
    }

    @Test
    void 할인_전_총주문_금액_테스트(){
        String orderInput = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        Order order = new Order(orderInput,3);
        int result = 142000;

        assertThat(order.calculateTotalOrderAmount()).isEqualTo(result);
    }


}