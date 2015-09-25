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
 * Created by CMG_TEST on 04.09.2015.
 */
public class AcceptWriterText extends BaseTest {




    @Test(groups={"regress 1.0"}, dataProvider= "AcceptWriterText", dataProviderClass = ActionsWithOrdersDataProvider.class)
    public static void AcceptWriterText(Object clientLoginObject, Object orderObject, Object writerLoginObj, Object text) throws InterruptedException {


        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;
        String writerText = text.toString();

        Order order = new Order();
        OrderInfoAndActions decisionPage = CreateOrderAddBidSetWinnerGoToDecisionPage.andMakeAChoice(driver, clientLogin, orderObj, writerLogin, order, writerText);
        decisionPage.clickOnAcceptButtonOnDecisionPage();

        assertEquals(decisionPage.acceptTextLableOnDecisionPage(), "TEXT ACCEPTED");

    }





}
