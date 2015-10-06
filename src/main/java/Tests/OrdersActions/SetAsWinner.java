package Tests.OrdersActions;

import Actions.Client.CreateOrderAddBidAndSetAsWinner;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 03.09.2015.
 */
public class SetAsWinner extends BaseTest {



    @Test(groups={"regress 1.0"})
    public static void SetAsWinner() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        OrderInfoAndActions orderInfoClientPage = CreateOrderAddBidAndSetAsWinner.andAwardOrderToWriter(driver, clientLogin, order, writerLogin);
        assertEquals(orderInfoClientPage.getTextFromOrderStatus(), "Awarded");

    }

}
