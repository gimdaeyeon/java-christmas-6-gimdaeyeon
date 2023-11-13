package christmas;

import christmas.data.MenuData;
import christmas.domain.MenuBoard;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {

        System.out.println(new MenuBoard(MenuData.selectAll()));
    }
}
