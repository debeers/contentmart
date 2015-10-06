package Tests.OrdersActions;

import Actions.Client.CreateOrderAddBidAndSetAsWinner;
import Entities.LoginObject;
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

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");

        OrderInfoAndActions orderInfoClientPage = CreateOrderAddBidAndSetAsWinner.andAwardOrderToWriter(driver, clientLogin, orderObj, writerLogin);

        assertEquals(orderInfoClientPage.getTextFromOrderStatus(), "Awarded", "ERROR: wrong status!");

    }

}
