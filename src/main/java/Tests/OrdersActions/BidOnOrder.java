package Tests.OrdersActions;

import Actions.Writer.WriterGoToAllOrders;
import Entities.LoginObject;
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


        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");
        String msg = "Thanks! Your proposal has been sent to the order owner.";


        OrderInfoAndActions orderInfoAndActions = WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, orderObj, writerLogin);

        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Proposal sent", "ERROR: wrong status!");
        assertEquals(orderInfoAndActions.getTextFromOrderName(), orderObj.getEntityOrderName());
        assertEquals(orderInfoAndActions.getTextFromSuccessMessageTextAfterBid(), msg, "Error: no success message");


    }



}
