package Actions.General;

import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.GeneralWaits;
import PageObjects.General.BalanceGeneralPage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;

import static Actions.General.RegistrationAndLogin.loginAs;
import static com.codeborne.selenide.Selenide.$;


/**
 * Created by CMG_TEST on 07.09.2015.
 */
public class GoToBalanceGeneralActions {


    public static String getCurrentBallanceFromMenuButton(WebDriver driver) {

        BalanceGeneralPage balanceGeneral = new BalanceGeneralPage(driver);
        String scan = $(balanceGeneral.clientBallanceFromLeftMenu).shouldBe(Condition.visible).getText();
        String balance = scan.substring(scan.indexOf(' ') + 1);
        System.out.println("Scan ballance from button = " + balance);

        return balance;

    }


    public static Boolean blockingBallanceDifference(OrderObject order) {

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


    public static Boolean unBlockingBallanceDifference(OrderObject order) {

        double orderPrice = Double.parseDouble(order.getEntityOrderValue());
        double balanceAfterBlocking = Double.parseDouble(order.getTotalBalanceAfterBlocking());
        double balanceAfterUnblocking = Double.parseDouble(order.getTotalBalanceAfterUnBlocking());

        double res = balanceAfterUnblocking - balanceAfterBlocking ;

        if (res == orderPrice ) {
            System.out.println("Balance before: " + order.getTotalBalanceBefore() + "\n" +
                    "Balance price: " + order.getEntityOrderValue() + "\n" );
            System.out.println("Balance operation correct! ");

            return true;

        } else {
            System.out.println("!!!!!!! Wrong balance man!!!!!!!!!");
            return false;
        }

    }



    public static Boolean transferBallanceDifference( OrderObject order) {

        double balanceBefore = Double.parseDouble(order.getTotalBalanceBefore());
        double orderPrice = Double.parseDouble(order.getEntityOrderValue());
        double balanceAfterBlocking = Double.parseDouble(order.getTotalBalanceAfterBlocking());


        double res = balanceBefore - balanceAfterBlocking;

        if (res == orderPrice ) {
            System.out.println("Balance before: " + order.getTotalBalanceBefore() + "\n" +
                    "Balance price: " + order.getEntityOrderValue() + "\n");
            System.out.println("Balance operation correct! ");

            return true;

        } else {
            System.out.println("!!!!!!! Wrong balance man!!!!!!!!!");
            return false;
        }

    }



    public static BalanceGeneralPage clientGoToCheckForBalance(WebDriver driver, LoginObject clientLogin, OrderObject orderObj){
        if(driver.getTitle() != "ContentMart" ) {
            loginAs(driver, clientLogin);
        }else
        orderObj.setTotalBalanceAfterUnBlocking(getCurrentBallanceFromMenuButton(driver));
        BalanceGeneralPage balanceGeneral = new BalanceGeneralPage(driver);
        balanceGeneral.clickOnbalanceLeftMenu();
        GeneralWaits.waitForPageLoad(driver);

        return balanceGeneral;

    }









}
