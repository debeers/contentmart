package Tests.OrdersActions;

import Actions.Writer.WriterGoToAllOrders;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.RegistrationAndLogin.logOut;
import static org.testng.Assert.assertEquals;

/**
 * Created by ilya on 28.08.2015.
 */
public class BidOnOrder extends BaseTest {





    @Test(groups={"regress 1.0"}, dataProvider= "dataproviderForBidOnOrder", dataProviderClass = WriterMakeAddBidForTheOrderDataProvider.class)
    public void WriterBidOnOrder(Object clientLoginObject, Object orderObject, Object writerLoginObj, Object msg) throws InterruptedException {


        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;

        Order order = new Order();
        OrderInfoAndActions orderInfoAndActions = WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, orderObj, writerLogin, order);

        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Proposal sent", "ERROR: wrong status!");
        assertEquals(orderInfoAndActions.getTextFromOrderName(), order.getEntityOrderName(), "ERROR: There are different order name");
        assertEquals(orderInfoAndActions.getTextFromSuccessMessageTextAfterBid(), msg, "Error: no success message");
        logOut(driver);

    }



}
