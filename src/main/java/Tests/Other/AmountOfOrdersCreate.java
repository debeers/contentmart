package Tests.Other;

import Actions.Client.ClientGoToCreateNewOrder;
import Actions.RegistrationAndLogin;
import DataProviders.CreateNewOrderActionsDataProvider;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

/**
 * Created by CMG_TEST on 23.09.2015.
 */
public class AmountOfOrdersCreate extends BaseTest{


    @Test(groups={"regress 1.0"}, dataProvider= "CreateNewOrderActions", dataProviderClass = CreateNewOrderActionsDataProvider.class)
    public  void AmountOfOrdersCreate(Object clientLoginObject, Object orderObject) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;

        for (int i = 0; i<=20; i++) {
            OrderInfoAndActions orderInfoAndActions = ClientGoToCreateNewOrder.andPublish(driver, clientLogin, orderObj);
            ClientNewOrderPage clientNewOrderPage = orderInfoAndActions.clickOnCloneOrderButton();
            clientNewOrderPage.andClickOnPublishNewOrderButton(driver);
            RegistrationAndLogin.logOut(driver);

        }


    }



}
