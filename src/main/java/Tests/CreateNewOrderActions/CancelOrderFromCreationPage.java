package Tests.CreateNewOrderActions;

import Actions.Client.ClientGoToCreateNewOrder;
import Actions.RegistrationAndLogin;
import DataProviders.CreateNewOrderActionsDataProvider;
import Entities.Balance;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.RegistrationAndLogin.logOut;

/**
 * Created by DeBeers on 17.09.2015.
 */
public class CancelOrderFromCreationPage extends BaseTest{


    @Test(groups={"regress 1.0"}, dataProvider= "CreateNewOrderActions", dataProviderClass = CreateNewOrderActionsDataProvider.class)
    public void EditAndThanPublishOrder(Object clientLoginObject, Object orderObject) throws Exception {


        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;

        Order order = new Order();
        ClientNewOrderPage clientNewOrderPage = ClientGoToCreateNewOrder.andCreateTheNewOrder(driver, clientLogin, orderObj, order);
        clientNewOrderPage.clickOnCancelOrderButton();
        Assert.assertEquals(driver.getTitle(), "My Orders | ContentMart");
        logOut(driver);

    }


}
