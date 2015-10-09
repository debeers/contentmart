package Tests.CreateNewOrderActions;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 17.09.2015.
 */
public class CreateNewOrderAndSaveAsDraft extends BaseTest{


    @Test(groups={"regress 1.0"})
    public  void CreateNewOrderAndSaveAsDraft() throws Exception {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        ClientNewOrderPage clientNewOrderPage = ClientGoToCreateNewOrder.andCreateTheNewOrder(driver, clientLogin, order);
        OrderInfoAndActions orderInfoAndActions = clientNewOrderPage.andClickOnSaveAsDraftButton(driver);

        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Drafted");
    }
}
