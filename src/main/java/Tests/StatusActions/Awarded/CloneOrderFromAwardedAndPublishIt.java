package Tests.StatusActions.Awarded;

import Actions.Client.CreateOrderAddBidAndSetAsWinner;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * Created by DeBeers on 06.10.2015.
 */
public class CloneOrderFromAwardedAndPublishIt extends BaseTest{

    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public static void CloneOrderFromAwarded() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        OrderInfoAndActions orderInfoClientPage = CreateOrderAddBidAndSetAsWinner.andAwardOrderToWriter(driver, clientLogin, order, writerLogin);
        assertEquals(orderInfoClientPage.getTextFromOrderStatus(), "Awarded");
        ClientNewOrderPage clientNewOrderPage = orderInfoClientPage.clickOnCloneOrderButton();
        clientNewOrderPage.setOrder(clientNewOrderPage, order);
        OrderInfoAndActions orderInfoAndActions = clientNewOrderPage.andClickOnPublishNewOrderButton(driver);

        assertEquals(orderInfoAndActions.getorderStatus(), "Published");
        assertNotEquals(orderInfoAndActions.getsystemOrderID(), order.getEntityOrderSystemID());
    }
}
