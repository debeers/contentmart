package Tests.OrdersActions;

import Actions.Client.CreateOrderAddBidAndSetAsWinner;
import DataProviders.ClientSetAsWinnerDataProvider;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.RegistrationAndLogin.logOut;
import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 03.09.2015.
 */
public class SetAsWinner extends BaseTest {



    @Test(groups={"regress 1.0"}, dataProvider= "SetAsWinnerDataProvider", dataProviderClass = ClientSetAsWinnerDataProvider.class)
    public static void SetAsWinner(Object clientLoginObject, Object orderObject, Object writerLoginObj) throws InterruptedException {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;

        Order order = new Order();
        OrderInfoAndActions orderInfoClientPage = CreateOrderAddBidAndSetAsWinner.andAwardOrderToWriter(driver, clientLogin, orderObj, writerLogin, order);

        assertEquals(orderInfoClientPage.getTextFromOrderStatus(), "Awarded", "ERROR: wrong status!");
        logOut(driver);

    }

}
