package Tests.OrdersActions;

import Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 04.09.2015.
 */
public class AcceptWriterText extends BaseTest {

    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public static void AcceptWriterText() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";

        OrderInfoAndActions decisionPage = CreateOrderAddBidSetWinnerGoToDecisionPage.andMakeAChoice(driver, clientLogin, order, writerLogin, writerText);
        decisionPage.clickOnAcceptButtonOnDecisionPage();

        assertEquals(decisionPage.acceptTextLableOnDecisionPage(), "TEXT ACCEPTED");
    }
}
