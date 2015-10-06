package Tests.OrdersActions;

import Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class ReassignOrder extends BaseTest {

    @Test(groups={"regress 1.0"})
    public static void ReassignOrder() throws Exception {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";
        String declineReason = "Nothing pearsonal, just test!";

        OrderInfoAndActions decisionPage = CreateOrderAddBidSetWinnerGoToDecisionPage.andReassignOrder(driver, clientLogin, order, writerLogin, writerText, declineReason);

        Assert.assertEquals(decisionPage.getorderStatus(), "Result sent");
        Assert.assertEquals(declineReason, decisionPage.getTextFromDeclineReasonOnDecisionPage());

    }
}
