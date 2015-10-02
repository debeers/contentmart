package Actions.Client;

import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.GeneralHelpers;
import GeneralHelpers.GeneralWaits;
import PageObjects.General.MyOrdersPage;
import org.openqa.selenium.WebDriver;

import static Actions.RegistrationAndLogin.logOut;
import static Actions.RegistrationAndLogin.loginAs;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 09.09.2015.
 */
public class ClientGoToMyOrders {


    public static MyOrdersPage clientGoToMyOrders(WebDriver driver, LoginObject clientLogin) {

        if (driver.getTitle() != "My Orders | ContentMart") {
            logOut(driver);
            loginAs(driver, clientLogin);
        }

        MyOrdersPage myOrders = new MyOrdersPage(driver);
        GeneralWaits.waitForPageLoad(driver);

        $(myOrders.draftLinkMyOrderClient).shouldBe(visible);       // метод ждёт и готовит мне страницу для следующего метода, ожидания нужны, часто не всё в дом заходит
        $(myOrders.inProgressLinkMyOrdersClient).shouldBe(visible);
        $(myOrders.finishedLinkMyOrdersClient).shouldBe(visible);
        $(myOrders.publishedLinkMyOrdersClient).shouldBe(visible);

        return myOrders;
    }


    public static MyOrdersPage clientGoToCreatedOrder(WebDriver driver, LoginObject clientLogin, OrderObject order) throws InterruptedException {

        if (driver.getTitle() == order.getEntityOrderName() + " | ContentMart") {

            logOut(driver);
        }

        MyOrdersPage myOrders = ClientGoToMyOrders.clientGoToMyOrders(driver, clientLogin);

        myOrders.clickOnPublishedLinkMyOrdersClient();
        GeneralHelpers.findCreatedClientOrderAndClickOnIt(driver, order);
        System.out.println("Balance successfully found!");
        return myOrders;
    }


}
