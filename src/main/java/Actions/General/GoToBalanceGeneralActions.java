package Actions.General;

import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.GeneralWaits;
import PageObjects.General.BalanceGeneralPage;
import org.openqa.selenium.WebDriver;

import static Actions.General.RegistrationAndLogin.loginAs;


/**
 * Created by CMG_TEST on 07.09.2015.
 */
public class GoToBalanceGeneralActions {


    public static String getCurrentBallanceFromMenuButton(WebDriver driver) {

        BalanceGeneralPage balanceGeneral = new BalanceGeneralPage(driver);
        String scan = balanceGeneral.scanBalanceFromMenu();
        System.out.println("Scan ballance from button = " + scan);

        return scan;
    }


    public static Boolean checkForCorrectBlockingMoneyOperation(OrderObject order) {

        double balanceBefore = Double.parseDouble(order.getTotalBalanceBefore());
        double orderPrice = Double.parseDouble(order.getEntityOrderValue());
        double balanceAfterBlocking = Double.parseDouble(order.getTotalBalanceAfterBlocking());

        double res = balanceBefore - balanceAfterBlocking;

        if (res == orderPrice) {
            System.out.println("Balance before: " + order.getTotalBalanceBefore() + "\n" +
                    "Balance price: " + order.getEntityOrderValue() + "\n");
            System.out.println("Balance operation correct! ");

            return true;

        } else {
            System.out.println("!!!!!!! Wrong balance man!!!!!!!!!");
            return false;
        }
    }


    public static Boolean checkForCorrectUnblockingMoneyOperation(OrderObject order) {

        double orderPrice = Double.parseDouble(order.getEntityOrderValue());
        double balanceAfterBlocking = Double.parseDouble(order.getTotalBalanceAfterBlocking());
        double balanceAfterUnblocking = Double.parseDouble(order.getTotalBalanceAfterUnBlocking());

        double res = balanceAfterUnblocking - balanceAfterBlocking;

        if (res == orderPrice) {
            System.out.println("Balance before: " + order.getTotalBalanceBefore() + "\n" +
                    "Balance price: " + order.getEntityOrderValue() + "\n");
            System.out.println("Balance operation correct! ");

            return true;

        } else {
            System.out.println("!!!!!!! Wrong balance man!!!!!!!!!");
            return false;
        }

    }


    public static Boolean checkForCorrectBalanceTransferOperation(OrderObject order) {

        double balanceBefore = Double.parseDouble(order.getTotalBalanceBefore());
        double orderPrice = Double.parseDouble(order.getEntityOrderValue());
        double balanceAfterBlocking = Double.parseDouble(order.getTotalBalanceAfterBlocking());

        double res = balanceBefore - balanceAfterBlocking;

        if (res == orderPrice) {
            System.out.println("Balance before: " + order.getTotalBalanceBefore() + "\n" +
                    "Balance price: " + order.getEntityOrderValue() + "\n");
            System.out.println("Balance operation correct! ");

            return true;

        } else {
            System.out.println("!!!!!!! Wrong balance man!!!!!!!!!");
            return false;
        }

    }


    public static BalanceGeneralPage clientGoToCheckForBlockingBalance(WebDriver driver, OrderObject orderObj) {

        orderObj.setTotalBalanceAfterUnBlocking(getCurrentBallanceFromMenuButton(driver));
        BalanceGeneralPage balanceGeneral = new BalanceGeneralPage(driver);
        balanceGeneral.clickOnbalanceLeftMenu();
        GeneralWaits.waitForPageLoad(driver);

        return balanceGeneral;

    }


    public static BalanceGeneralPage clientGoToCheckForUnBlockingBalance(WebDriver driver, OrderObject orderObj, LoginObject clientLogin) {

        loginAs(driver, clientLogin);
        orderObj.setTotalBalanceAfterBlocking(getCurrentBallanceFromMenuButton(driver));
        BalanceGeneralPage balanceGeneral = new BalanceGeneralPage(driver);
        balanceGeneral.clickOnbalanceLeftMenu();
        GeneralWaits.waitForPageLoad(driver);

        return balanceGeneral;

    }
}
