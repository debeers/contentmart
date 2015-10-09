package Tests.StopWords;

import Actions.Writer.WriterGoToAllOrders;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 24.09.2015.
 */
public class StopWordsInLeaveAnOffer extends BaseTest{

    @Test(groups={"regress 1.0"})
    public void StopWordsInLeaveAnOffer() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String offerDetailsText = "Hello master! I`m your Jinni! Now you`re have 3 wishes! But I`m very bussy for now, please call me for a 1000 year or write me on my email.";
        String stopWordsAllert = "You may not communicate with users directly or provide your email, Skype or phone number. Learn why not to work outside ContentMart.";

        OrderInfoAndActions orderInfoAndActions = WriterGoToAllOrders.createNewOrderAndBidOnIt(driver, clientLogin, order, writerLogin);
        orderInfoAndActions.typeDetailsOfYourOfferField(offerDetailsText);

        assertEquals(orderInfoAndActions.stopwordsAllertMsg().trim(), stopWordsAllert);
        orderInfoAndActions.clickOnLeaveAnOfferButtonFromBidOnOrder(driver);
    }
}
