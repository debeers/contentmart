package Tests.OrdersActions;

import Actions.Client.CreateOrderAddBidAndSetAsWinner;
import DataProviders.ActionsWithOrdersDataProvider;
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



    @Test(groups={"regress 1.0"}, dataProvider= "SetAsWinnerDataProvider", dataProviderClass = ActionsWithOrdersDataProvider.class)
    public static void SetAsWinner(Object clientLoginObject, Object orderObject, Object writerLoginObj) throws InterruptedException {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;

        OrderInfoAndActions orderInfoClientPage = CreateOrderAddBidAndSetAsWinner.andAwardOrderToWriter(driver, clientLogin, orderObj, writerLogin);

        assertEquals(orderInfoClientPage.getTextFromOrderStatus(), "Awarded", "ERROR: wrong status!");

    }

}
