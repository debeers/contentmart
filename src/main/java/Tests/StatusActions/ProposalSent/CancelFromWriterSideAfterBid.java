package Tests.StatusActions.ProposalSent;

import Actions.Writer.WriterGoToAllOrders;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by DeBeers on 06.10.2015.
 */
public class CancelFromWriterSideAfterBid extends BaseTest{

    @Test(groups={"regress 1.0"})
    public void WriterBidOnOrder() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        OrderInfoAndActions orderInfoAndActions = WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, order, writerLogin);

        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Proposal sent");
        orderInfoAndActions.clickOnCancelYourOfferAfterBid();

        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Published");
        assertTrue(orderInfoAndActions.leaveAnOfferDetailsField.isDisplayed());
    }
}
