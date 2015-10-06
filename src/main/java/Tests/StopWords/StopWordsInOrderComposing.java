package Tests.StopWords;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by CMG_TEST on 23.09.2015.
 */
public class StopWordsInOrderComposing extends BaseTest{





    @Test(groups={"regress 1.0"})
    public  void StopWordsInOrderComposing() throws Exception {

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        ClientNewOrderPage clientNewOrderPage = ClientGoToCreateNewOrder.andCreateTheNewOrder(driver, clientLogin, orderObj);

        Assert.assertTrue(clientNewOrderPage.waitForStopWordsAllertAppear());
        clientNewOrderPage.andClickOnPublishNewOrderButton(driver);

    }












}
