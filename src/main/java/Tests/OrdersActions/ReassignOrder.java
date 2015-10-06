package Tests.OrdersActions;

import Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage;
import Entities.LoginObject;
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

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";
        String declineReason = "Nothing pearsonal, just test!";

        OrderInfoAndActions decisionPage = CreateOrderAddBidSetWinnerGoToDecisionPage.andReassignOrder(driver, clientLogin, orderObj, writerLogin, writerText, declineReason);

        Assert.assertEquals(decisionPage.getorderStatus(), "Result sent");
        Assert.assertEquals(declineReason, decisionPage.getTextFromDeclineReasonOnDecisionPage());

    }
}
