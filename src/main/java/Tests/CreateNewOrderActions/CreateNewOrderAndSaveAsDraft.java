package Tests.CreateNewOrderActions;

import Actions.Client.ClientGoToCreateNewOrder;
import Actions.RegistrationAndLogin;
import DataProviders.CreateNewOrderActionsDataProvider;
import Entities.Balance;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.RegistrationAndLogin.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 17.09.2015.
 */
public class CreateNewOrderAndSaveAsDraft extends BaseTest{


    @Test(groups={"regress 1.0"}, dataProvider= "CreateNewOrderActions", dataProviderClass = CreateNewOrderActionsDataProvider.class)
    public  void CreateNewOrderAndSaveAsDraft(Object clientLoginObject, Object orderObject) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;

        Order order = new Order();
        ClientNewOrderPage clientNewOrderPage = ClientGoToCreateNewOrder.andCreateTheNewOrder(driver, clientLogin, orderObj, order);
        OrderInfoAndActions orderInfoAndActions = clientNewOrderPage.andClickOnSaveAsDraftButton(driver);

        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Drafted");
        logOut(driver);

    }


}
