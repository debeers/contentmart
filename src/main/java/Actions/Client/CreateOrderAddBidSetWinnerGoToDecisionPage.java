package Actions.Client;

import Actions.Writer.WriterGoToStartToWorking;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

import static Actions.RegistrationAndLogin.logOut;
import static Actions.RegistrationAndLogin.loginAs;
import static GeneralHelpers.GeneralHelpers.findCreatedClientOrderAndClickOnIt;
import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 18.09.2015.
 */
public class CreateOrderAddBidSetWinnerGoToDecisionPage {


    public static OrderInfoAndActions andMakeAChoice(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin, Order order, String text) throws InterruptedException {

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, orderObject, writerLogin, order);
        orderInfoWriter.sendTextToTheClientTextArea(driver, text);
        orderInfoWriter.clickOnTheSendCompletedOrderButton(driver);

        logOut(driver);
        MyOrdersPage myOrders = loginAs(driver, clientLogin);
        myOrders.clickOnInProgressLinkMyOrdersClient();
        findCreatedClientOrderAndClickOnIt(driver, order);

        OrderInfoAndActions orderInfoClient = new OrderInfoAndActions(driver);
        $(orderInfoClient.acceptButtonDecision).shouldBe(present);  //нужные ожидания, без них не все успевают в дом
        $(orderInfoClient.reassignButtonDecision).shouldBe(present);
        $(orderInfoClient.declineButtonDecision).shouldBe(present);

        return orderInfoClient;
    }


    public static OrderInfoAndActions andReassignOrder(WebDriver driver, LoginObject clientLogin, OrderObject orderObject,
                                                       LoginObject writerLogin, Order order, String text, String reason) throws InterruptedException {

        OrderInfoAndActions orderInfoAndActions = andMakeAChoice(driver, clientLogin, orderObject, writerLogin, order, text);
        orderInfoAndActions.clickOnReassingButtonDecision();
        orderInfoAndActions.sendReasonOfRefusalTextAreaDecision(driver, reason);
        orderInfoAndActions.setReassignDate();
        orderInfoAndActions.clickOnReassignSendButton();

        return orderInfoAndActions;
    }






}
