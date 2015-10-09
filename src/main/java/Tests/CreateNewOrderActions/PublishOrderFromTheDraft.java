package Tests.CreateNewOrderActions;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 17.09.2015.
 */
public class PublishOrderFromTheDraft extends BaseTest{

    @Test(groups={"regress 1.0"})
    public  void PublishOrderFromTheDraft() throws Exception {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        OrderInfoAndActions orderInfoAndActions = ClientGoToCreateNewOrder.andSaveAsDraft(driver, clientLogin, order);
        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Drafted");
        orderInfoAndActions.andClickOnPublishOrderButtonTop();
        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Published");
    }
}
