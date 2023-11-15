package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EventManagerTest {

    Order order;

    @BeforeEach
    void setUp(){
        String orderInput = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        order = new Order(orderInput,3);
    }

    @Test
    void 총혜택_금액_테스트(){
        EventManager eventManager = new EventManager(order);
        int result = 31246;

        assertThat(eventManager.getTotalBenefitAmount())
                .isEqualTo(result);
    }

    @Test
    void 총할인_금액_테스트(){
        EventManager eventManager = new EventManager(order);
        int result = 6246;
        assertThat(eventManager.getTotalDiscountAmount())
                .isEqualTo(result);
    }


}