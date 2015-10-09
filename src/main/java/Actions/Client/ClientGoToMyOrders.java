package Actions.Client;

import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.GeneralHelpers;
import GeneralHelpers.GeneralWaits;
import PageObjects.General.MyOrdersPage;
import org.openqa.selenium.WebDriver;

import static Actions.General.RegistrationAndLogin.logOut;
import static Actions.General.RegistrationAndLogin.loginAs;
import static GeneralHelpers.CustomWaits.$WaitFor;

/**
 * Created by CMG_TEST on 09.09.2015.
 */
public class ClientGoToMyOrders {

    public static MyOrdersPage clientGoToMyOrders(WebDriver driver, LoginObject clientLogin) {

        loginAs(driver, clientLogin);
        MyOrdersPage myOrders = new MyOrdersPage(driver);
        GeneralWaits.waitForPageLoad(driver);

        $WaitFor(
                myOrders.draftLinkMyOrderClient,
                myOrders.inProgressLinkMyOrdersClient,
                myOrders.finishedLinkMyOrdersClient,
                myOrders.publishedLinkMyOrdersClient
        );

        return myOrders;
    }


    public static MyOrdersPage clientGoToCreatedOrder(WebDriver driver, LoginObject clientLogin, OrderObject order) throws InterruptedException {

        logOut(driver);
        MyOrdersPage myOrders = ClientGoToMyOrders.clientGoToMyOrders(driver, clientLogin);

        myOrders.clickOnPublishedLinkMyOrdersClient();
        GeneralHelpers.findCreatedOrderAndClickOnIt(driver, order);
        System.out.println("Balance successfully found!");
        return myOrders;
    }
}
