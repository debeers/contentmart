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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 17.09.2015.
 */
public class CloneTheOrder extends BaseTest{

    @Test(groups={"regress 1.0"}, dataProvider= "CreateNewOrderActions", dataProviderClass = CreateNewOrderActionsDataProvider.class)
    public  void EditAndThanPublishOrder(Object clientLoginObject, Object orderObject) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;


        Order order = new Order();
        OrderInfoAndActions orderInfoAndActions = ClientGoToCreateNewOrder.andPublish(driver, clientLogin, orderObj, order);
        ClientNewOrderPage clientNewOrderPage = orderInfoAndActions.clickOnCloneOrderButton();
        clientNewOrderPage.andClickOnPublishNewOrderButton(driver);
        

        assertEquals(orderInfoAndActions.getorderName(), order.getEntityOrderName());
        assertThat(orderInfoAndActions.getsystemOrderID(), not(equalToIgnoringCase(order.getEntityOrderSystemID())));
        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Published", "ERROR: wrong status!");
        logOut(driver);
    }
}
