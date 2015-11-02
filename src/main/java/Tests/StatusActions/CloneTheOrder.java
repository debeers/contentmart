package Tests.StatusActions;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 17.09.2015.
 */
public class CloneTheOrder extends BaseTest{

    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public  void EditAndThanPublishOrder() throws Exception {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        OrderInfoAndActions orderInfoAndActions = ClientGoToCreateNewOrder.andPublish(driver, clientLogin, order);
        ClientNewOrderPage clientNewOrderPage = orderInfoAndActions.clickOnCloneOrderButton();
        clientNewOrderPage.andClickOnPublishNewOrderButton(driver);

        assertEquals(orderInfoAndActions.getorderName(), order.getEntityOrderName());
        assertThat(orderInfoAndActions.getsystemOrderID(), not(equalToIgnoringCase(order.getEntityOrderSystemID())));
        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Published");
    }
}
