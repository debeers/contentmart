package Tests.CreateNewOrderActions;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by DeBeers on 17.09.2015.
 */
public class CancelOrderFromCreationPage extends BaseTest{


    @Test(groups={"regress 1.0"})
    public void EditAndThanPublishOrder() throws Exception {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        ClientNewOrderPage clientNewOrderPage = ClientGoToCreateNewOrder.andCreateTheNewOrder(driver, clientLogin, order);
        clientNewOrderPage.clickOnCancelOrderButton();
        Assert.assertEquals(driver.getTitle(), "My Orders | ContentMart");
    }
}
