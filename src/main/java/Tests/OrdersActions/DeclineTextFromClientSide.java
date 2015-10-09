package Tests.OrdersActions;

import Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class DeclineTextFromClientSide extends BaseTest {

    @Test(groups={"regress 1.0"})
    public static void DeclineTextFromClientSide() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";
        String declineReason = "Nothing pearsonal, just test!";

        OrderInfoAndActions decisionPage = CreateOrderAddBidSetWinnerGoToDecisionPage.andMakeAChoice(driver, clientLogin, order, writerLogin, writerText);
        decisionPage.clickOndeclineButtonOnDecisionPage();
        assertEquals(decisionPage.getTextFromLable(), "CONTENT REJECTED");

        decisionPage.sendReasonOfRefusalTextAreaDecision(driver, declineReason);
        decisionPage.clickOnDeclineButtonAfterRefusalDecision(driver);

        assertEquals(decisionPage.getTextFromDeclineReasonOnDecisionPage(), declineReason);
        assertEquals(decisionPage.getTextFromOrderStatus(), "Declined");
    }
}






