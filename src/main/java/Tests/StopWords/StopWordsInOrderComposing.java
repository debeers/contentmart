package Tests.StopWords;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by CMG_TEST on 23.09.2015.
 */
public class StopWordsInOrderComposing extends BaseTest{

    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public  void StopWordsInOrderComposing() throws Exception {

        OrderObject order = new OrderObject("Automation test order ID:", "call me to skype", "15", "1");

        ClientNewOrderPage clientNewOrderPage = ClientGoToCreateNewOrder.andCreateTheNewOrder(driver, clientLogin, order);
        Assert.assertEquals(clientNewOrderPage.getStopWordsAllert(), "why not to work outside ContentMart");
        clientNewOrderPage.andClickOnPublishNewOrderButton(driver);
    }
}
