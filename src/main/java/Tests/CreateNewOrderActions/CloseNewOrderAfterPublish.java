package Tests.CreateNewOrderActions;

import Actions.Client.ClientGoToCreateNewOrder;
import Actions.RegistrationAndLogin;
import DataProviders.CreateNewOrderActionsDataProvider;
import Entities.Balance;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.RegistrationAndLogin.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 20.09.2015.
 */
public class CloseNewOrderAfterPublish extends BaseTest{


    @Test(groups={"regress 1.0"}, dataProvider= "CreateNewOrderActions", dataProviderClass = CreateNewOrderActionsDataProvider.class)
    public  void CloseNewOrderAfterPublish(Object clientLoginObject, Object orderObject) throws Exception {


        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;


        Order order = new Order();
        OrderInfoAndActions orderInfoAndActions = ClientGoToCreateNewOrder.andPublish(driver, clientLogin, orderObj, order);
        orderInfoAndActions.clickOnCloseOrderButtonTopAndAcceptSweet(driver);

        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Drafted");
        assertEquals(orderInfoAndActions.getorderName(), order.getEntityOrderName());
        logOut(driver);
    }
}
