package christmas.controller;

import christmas.domain.Order;
import christmas.ui.InputView;
import christmas.ui.OutputView;

public class ChristmasController {
    InputView inputView;
    OutputView outputView;
    Order order;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        int visitDate = inputView.inputDate();
        String orderStr = inputView.inputOrder();

        order = new Order(orderStr,visitDate);
    }
}
