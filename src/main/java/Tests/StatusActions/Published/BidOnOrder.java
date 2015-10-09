package Tests.StatusActions.Published;

import Actions.Writer.WriterGoToAllOrders;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by ilya on 28.08.2015.
 */
public class BidOnOrder extends BaseTest {

    @Test(groups={"regress 1.0"})
    public void WriterBidOnOrder() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String msg = "Thanks! Your proposal has been sent to the order owner.";

        OrderInfoAndActions orderInfoAndActions = WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, order, writerLogin);

        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Proposal sent");
        assertEquals(orderInfoAndActions.getTextFromOrderName(), order.getEntityOrderName());
        assertEquals(orderInfoAndActions.getTextFromSuccessMessageTextAfterBid(), msg);
    }
}
