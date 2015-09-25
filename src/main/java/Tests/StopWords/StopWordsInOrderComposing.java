package Tests.StopWords;

import Actions.Client.ClientGoToCreateNewOrder;
import DataProviders.StopWordsDataProvider;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by CMG_TEST on 23.09.2015.
 */
public class StopWordsInOrderComposing extends BaseTest{





    @Test(groups={"regress 1.0"}, dataProvider= "StopWordsInNewOrderComposing", dataProviderClass = StopWordsDataProvider.class)
    public  void StopWordsInOrderComposing(Object clientLoginObject, Object orderObject) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;

        Order order = new Order();
        ClientNewOrderPage clientNewOrderPage = ClientGoToCreateNewOrder.andCreateTheNewOrder(driver, clientLogin, orderObj, order);

        Assert.assertTrue(clientNewOrderPage.waitForStopWordsAllertAppear());
        clientNewOrderPage.andClickOnPublishNewOrderButton(driver);

    }












}
