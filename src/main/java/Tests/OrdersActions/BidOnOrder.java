package Tests.OrdersActions;

import Actions.Writer.WriterGoToAllOrders;
import DataProviders.ActionsWithOrdersDataProvider;
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





    @Test(groups={"regress 1.0"}, dataProvider= "dataproviderForBidOnOrder", dataProviderClass = ActionsWithOrdersDataProvider.class)
    public void WriterBidOnOrder(Object clientLoginObject, Object orderObject, Object writerLoginObj, Object msg) throws InterruptedException {


        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;

        OrderInfoAndActions orderInfoAndActions = WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, orderObj, writerLogin);

        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Proposal sent", "ERROR: wrong status!");
        assertEquals(orderInfoAndActions.getTextFromOrderName(), orderObj.getEntityOrderName(), "ERROR: There are different order name");
        assertEquals(orderInfoAndActions.getTextFromSuccessMessageTextAfterBid(), msg, "Error: no success message");


    }



}
