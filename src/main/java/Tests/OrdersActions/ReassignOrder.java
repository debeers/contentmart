package Tests.OrdersActions;

import Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage;
import DataProviders.ClientDeclineWritersTextDataProvider;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.RegistrationAndLogin.logOut;

/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class ReassignOrder extends BaseTest {

    @Test(groups={"regress 1.0"}, dataProvider= "dataproviderObjectsForDeclineText", dataProviderClass = ClientDeclineWritersTextDataProvider.class)
    public static void ReassignOrder(Object clientLoginObject, Object orderObject, Object writerLoginObj, Object text, Object declineReasonObj) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;
        String writerText = text.toString();
        String declineReason = declineReasonObj.toString();

        Order order = new Order();
        OrderInfoAndActions decisionPage = CreateOrderAddBidSetWinnerGoToDecisionPage.andReassignOrder(driver, clientLogin, orderObj, writerLogin, order, writerText, declineReason);

        Assert.assertEquals(decisionPage.getorderStatus(), "Result sent");
        Assert.assertEquals(declineReason, decisionPage.getTextFromDeclineReasonOnDecisionPage());
        logOut(driver);

    }
}
