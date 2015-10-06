package Actions.Client;

import Actions.Writer.WriterGoToStartToWorking;
import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.GeneralWaits;
import PageObjects.General.BalanceGeneralPage;
import PageObjects.General.LoginPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

import static Actions.General.RegistrationAndLogin.logOut;
import static Actions.General.RegistrationAndLogin.loginAs;
import static GeneralHelpers.GeneralHelpers.findCreatedOrderAndClickOnIt;
import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 18.09.2015.
 */
public class CreateOrderAddBidSetWinnerGoToDecisionPage {


    public static OrderInfoAndActions andMakeAChoice(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin, String text) throws InterruptedException {

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, orderObject, writerLogin);
        orderInfoWriter.sendTextToTheClientTextArea(driver, text);
        orderInfoWriter.clickOnTheSendCompletedOrderButton(driver);

        logOut(driver);
        MyOrdersPage myOrders = loginAs(driver, clientLogin);
        myOrders.clickOnInProgressLinkMyOrdersClient();
        findCreatedOrderAndClickOnIt(driver, orderObject);

        OrderInfoAndActions orderInfoClient = new OrderInfoAndActions(driver);
        $(orderInfoClient.acceptButtonDecision).shouldBe(present);  //нужные ожидания, без них не все успевают в дом
        $(orderInfoClient.reassignButtonDecision).shouldBe(present);
        $(orderInfoClient.declineButtonDecision).shouldBe(present);

        return orderInfoClient;
    }


    public static OrderInfoAndActions andReassignOrder(WebDriver driver, LoginObject clientLogin, OrderObject orderObject,
                                                       LoginObject writerLogin, String text, String reason) throws InterruptedException {

        OrderInfoAndActions orderInfoAndActions = andMakeAChoice(driver, clientLogin, orderObject, writerLogin, text);
        orderInfoAndActions.clickOnReassingButtonDecision();
        orderInfoAndActions.sendReasonOfRefusalTextAreaDecision(driver, reason);
        orderInfoAndActions.setReassignDate();
        orderInfoAndActions.clickOnReassignSendButton();

        return orderInfoAndActions;
    }



    public static LoginPage declineWriterTextAction(WebDriver driver, OrderInfoAndActions decisionPage, String declineReason){

        decisionPage.clickOndeclineButtonOnDecisionPage();
        decisionPage.sendReasonOfRefusalTextAreaDecision(driver, declineReason);
        decisionPage.clickOnDeclineButtonAfterRefusalDecision(driver);
        logOut(driver);

        LoginPage loginPage = new LoginPage(driver);
        return loginPage;
    }




    public static void acceptWriterText(OrderInfoAndActions decisionPage){

        decisionPage.clickOnAcceptButtonOnDecisionPage();

    }



}
