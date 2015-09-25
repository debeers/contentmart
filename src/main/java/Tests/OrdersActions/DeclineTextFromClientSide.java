package Tests.OrdersActions;

import Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage;
import DataProviders.ActionsWithOrdersDataProvider;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class DeclineTextFromClientSide extends BaseTest {



    @Test(groups={"regress 1.0"}, dataProvider= "dataproviderObjectsForDeclineText", dataProviderClass = ActionsWithOrdersDataProvider.class)
    public static void DeclineTextFromClientSide(Object clientLoginObject, Object orderObject, Object writerLoginObj, Object text, Object declineReasonObj) throws InterruptedException {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;
        String writerText = text.toString();
        String declineReason = declineReasonObj.toString();
        System.out.println(declineReason);


        Order order = new Order();
        OrderInfoAndActions decisionPage = CreateOrderAddBidSetWinnerGoToDecisionPage.andMakeAChoice(driver, clientLogin, orderObj, writerLogin, order, writerText);

        decisionPage.clickOndeclineButtonOnDecisionPage();
        assertEquals(decisionPage.getTextFromLable(), "CONTENT REJECTED");

        decisionPage.sendReasonOfRefusalTextAreaDecision(driver, declineReason);
        decisionPage.clickOnDeclineButtonAfterRefusalDecision(driver);

        assertEquals(decisionPage.getTextFromDeclineReasonOnDecisionPage(), declineReason, "Wrong decline reason!");
        assertEquals(decisionPage.getTextFromOrderStatus(), "Declined", "ERROR: wrong status!");

    }

}






