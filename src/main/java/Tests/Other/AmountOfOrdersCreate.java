package Tests.Other;

import Actions.Client.ClientGoToCreateNewOrder;
import Actions.General.RegistrationAndLogin;
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


    @Test(groups={"regress 1.0"})
    public  void AmountOfOrdersCreate() throws Exception {

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");


        for (int i = 0; i<=20; i++) {
            OrderInfoAndActions orderInfoAndActions = ClientGoToCreateNewOrder.andPublish(driver, clientLogin, orderObj);
            ClientNewOrderPage clientNewOrderPage = orderInfoAndActions.clickOnCloneOrderButton();
            clientNewOrderPage.andClickOnPublishNewOrderButton(driver);
            RegistrationAndLogin.logOut(driver);

        }


    }



}
