package Tests.StatusActions.Published;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

/**
 * Created by DeBeers on 17.09.2015.
 */
public class EditAndThanPublishOrder extends BaseTest{

    @Test(groups={"regress 1.0"})
    public  void EditAndThanPublishOrder() throws Exception {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        ClientNewOrderPage clientNewOrderPage = ClientGoToCreateNewOrder.publishAndGoToEditOrder(driver, clientLogin, order);
        OrderInfoAndActions orderInfoAndActions = clientNewOrderPage.andClickOnPublishNewOrderButton(driver);

//        Assert.assertEquals(orderInfoAndActions.getorderName(), order.getEntityOrderName());
//        Assert.assertEquals(orderInfoAndActions.getorderStatus(), order.getEntityOrderStatus());  // incorrect redirection! Need to fix, task in jira CMI-1110
    }
}
