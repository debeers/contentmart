package Tests.CreateNewOrderActions;

import Actions.Client.ClientGoToCreateNewOrder;
import DataProviders.CreateNewOrderActionsDataProvider;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.RegistrationAndLogin.logOut;

/**
 * Created by DeBeers on 17.09.2015.
 */
public class EditAndThanPublishOrder extends BaseTest{




    @Test(groups={"regress 1.0"}, dataProvider= "CreateNewOrderActions", dataProviderClass = CreateNewOrderActionsDataProvider.class)
    public  void EditAndThanPublishOrder(Object clientLoginObject, Object orderObject) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;

        Order order = new Order();
        ClientNewOrderPage clientNewOrderPage = ClientGoToCreateNewOrder.publishAndGoToEditOrder(driver, clientLogin, orderObj, order);
        OrderInfoAndActions orderInfoAndActions = clientNewOrderPage.andClickOnPublishNewOrderButton(driver);

//        Assert.assertEquals(orderInfoAndActions.getorderName(), order.getEntityOrderName());
//        Assert.assertEquals(orderInfoAndActions.getorderStatus(), order.getEntityOrderStatus());
        logOut(driver);
    }








}
