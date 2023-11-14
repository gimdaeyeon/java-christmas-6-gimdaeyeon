package christmas;

import christmas.controller.ChristmasController;
import christmas.ui.InputView;
import christmas.ui.OutputView;

public class Application {
    public static void main(String[] args) {
        new ChristmasController(new InputView(),new OutputView());
    }
}
