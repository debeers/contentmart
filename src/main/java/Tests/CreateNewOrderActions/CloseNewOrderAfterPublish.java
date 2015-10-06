package Tests.CreateNewOrderActions;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 20.09.2015.
 */
public class CloseNewOrderAfterPublish extends BaseTest{


    @Test(groups={"regress 1.0"})
    public  void CloseNewOrderAfterPublish() throws Exception {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        OrderInfoAndActions orderInfoAndActions = ClientGoToCreateNewOrder.andPublish(driver, clientLogin, order);
        orderInfoAndActions.clickOnCloseOrderButtonTopAndAcceptSweet(driver);

        assertEquals(driver.getTitle(), "My Orders | ContentMart");

    }
}
