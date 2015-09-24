package Tests.StopWords;

import Actions.Writer.WriterGoToAllOrders;
import DataProviders.StopWordsDataProvider;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.RegistrationAndLogin.logOut;
import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 24.09.2015.
 */
public class StopWordsInLeaveAnOffer extends BaseTest{



    @Test(groups={"regress 1.0"}, dataProvider= "StopWordsInLeaveAnOffer", dataProviderClass = StopWordsDataProvider.class)
    public void StopWordsInLeaveAnOffer(Object clientLoginObject, Object orderObject, Object writerLoginObj, Object offerDetails, Object allert) throws InterruptedException {


        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;
        String offerDetailsText = (String) offerDetails;

        Order order = new Order();
        OrderInfoAndActions orderInfoAndActions = WriterGoToAllOrders.createNewOrderAndBidOnIt(driver, clientLogin, orderObj, writerLogin, order);
        orderInfoAndActions.typeDetailsOfYourOfferField(offerDetailsText);

        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Proposal sent", "ERROR: wrong status!");
        orderInfoAndActions.clickOnLeaveAnOfferButtonFromBidOnOrder(driver);


        assertEquals(orderInfoAndActions.getTextFromOrderName(), order.getEntityOrderName(), "ERROR: There are different order name");
        assertEquals(orderInfoAndActions.getTextFromSuccessMessageTextAfterBid(), allert, "Error: no success message");
        logOut(driver);

    }






}
//*[@id="description"]