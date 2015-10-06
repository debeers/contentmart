package Tests.CreateNewOrderActions;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

/**
 * Created by DeBeers on 17.09.2015.
 */
public class EditAndThanPublishOrder extends BaseTest{




    @Test(groups={"regress 1.0"})
    public  void EditAndThanPublishOrder() throws Exception {

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        ClientNewOrderPage clientNewOrderPage = ClientGoToCreateNewOrder.publishAndGoToEditOrder(driver, clientLogin, orderObj);
        OrderInfoAndActions orderInfoAndActions = clientNewOrderPage.andClickOnPublishNewOrderButton(driver);

//        Assert.assertEquals(orderInfoAndActions.getorderName(), order.getEntityOrderName());
//        Assert.assertEquals(orderInfoAndActions.getorderStatus(), order.getEntityOrderStatus());

    }








}
