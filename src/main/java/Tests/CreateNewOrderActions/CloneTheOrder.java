package Tests.CreateNewOrderActions;

import Actions.Client.ClientGoToCreateNewOrder;
import DataProviders.CreateNewOrderActionsDataProvider;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

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


        OrderInfoAndActions orderInfoAndActions = ClientGoToCreateNewOrder.andPublish(driver, clientLogin, orderObj);
        ClientNewOrderPage clientNewOrderPage = orderInfoAndActions.clickOnCloneOrderButton();
        clientNewOrderPage.andClickOnPublishNewOrderButton(driver);
        

        assertEquals(orderInfoAndActions.getorderName(), orderObj.getEntityOrderName());
        assertThat(orderInfoAndActions.getsystemOrderID(), not(equalToIgnoringCase(orderObj.getEntityOrderSystemID())));
        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Published", "ERROR: wrong status!");

    }
}
