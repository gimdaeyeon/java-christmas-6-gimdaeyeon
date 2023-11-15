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
        order.createEventManager(order);
    }

    public void getBenefitLists(){
        outputView.printResultBefitList(getResultMessage());
    }

    private String getResultMessage(){
        return order.formatOrderMenuSummary() +"\n"+
                order.formatTotalOrderAmountSummary() +"\n"+
                order.getGiftMessage() +"\n"+
                order.getBenefitMessage() +"\n"+
                order.getDiscountMessage() +"\n"+
                order.formatAmountAfterDiscount()+"\n"+
                order.getEventBadgeMessage()+"\n";
    }




}
