package christmas.ui;

public class OutputView {
    public static final String BENEFIT_GUIDE_MESSAGE ="12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public void printResultBefitList(String result){
        System.out.println(BENEFIT_GUIDE_MESSAGE+"\n");
        System.out.println(result);
    }


}
