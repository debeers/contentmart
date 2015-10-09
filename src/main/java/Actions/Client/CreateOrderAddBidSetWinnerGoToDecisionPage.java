package Actions.Client;

import Actions.Writer.WriterGoToStartToWorking;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.LoginPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

import static Actions.General.RegistrationAndLogin.logOut;
import static Actions.General.RegistrationAndLogin.loginAs;
import static GeneralHelpers.CustomWaits.$WaitFor;
import static GeneralHelpers.GeneralHelpers.findCreatedOrderAndClickOnIt;

/**
 * Created by DeBeers on 18.09.2015.
 */
public class CreateOrderAddBidSetWinnerGoToDecisionPage {


    public static OrderInfoAndActions andMakeAChoice(WebDriver driver, LoginObject clientLogin, OrderObject order, LoginObject writerLogin, String text) throws InterruptedException {

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, order, writerLogin);
        orderInfoWriter.sendTextToTheClientTextArea(driver, text);
        orderInfoWriter.clickOnTheSendCompletedOrderButton(driver);

        logOut(driver);
        MyOrdersPage myOrders = loginAs(driver, clientLogin);
        myOrders.clickOnInProgressLinkMyOrdersClient();
        findCreatedOrderAndClickOnIt(driver, order);

        OrderInfoAndActions orderInfoClient = new OrderInfoAndActions(driver);
        $WaitFor(

                orderInfoClient.acceptButtonDecision,
                orderInfoClient.reassignButtonDecision,
                orderInfoClient.declineButtonDecision
        );

        return orderInfoClient;
    }


    public static OrderInfoAndActions andReassignOrder(WebDriver driver, LoginObject clientLogin, OrderObject order,
                                                       LoginObject writerLogin, String text, String reason) throws InterruptedException {

        OrderInfoAndActions orderInfoAndActions = andMakeAChoice(driver, clientLogin, order, writerLogin, text);
        orderInfoAndActions.clickOnReassingButtonDecision();
        orderInfoAndActions.sendReasonOfRefusalTextAreaDecision(driver, reason);
        orderInfoAndActions.setReassignDate();
        orderInfoAndActions.clickOnReassignSendButton();

        return orderInfoAndActions;
    }


    public static LoginPage declineWriterTextAction(WebDriver driver, OrderInfoAndActions decisionPage, String declineReason) {

        decisionPage.clickOndeclineButtonOnDecisionPage();
        decisionPage.sendReasonOfRefusalTextAreaDecision(driver, declineReason);
        decisionPage.clickOnDeclineButtonAfterRefusalDecision(driver);
        logOut(driver);

        LoginPage loginPage = new LoginPage(driver);
        return loginPage;
    }


    public static void acceptWriterText(OrderInfoAndActions decisionPage) {

        decisionPage.clickOnAcceptButtonOnDecisionPage();
    }
}
