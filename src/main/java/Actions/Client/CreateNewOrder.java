package Actions.Client;

import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.Client.NewOrderPage;
import PageObjects.General.OrderWorkFlow;
import PageObjects.General.TopMenuGeneralPage;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static Actions.General.RegistrationAndLogin.loginAs;


/**
 * Created by DeBeers on 20.11.2015.
 */
public class CreateNewOrder {

    public static OrderWorkFlow createNewOrder(WebDriver driver, LoginObject clientLogin, OrderObject order, String filepath,
                                               String perUnit, String visibility, String articles) throws InterruptedException {

        loginAs(driver, clientLogin);
        TopMenuGeneralPage topMenuGeneralPage = new TopMenuGeneralPage(driver);

        NewOrderPage clientNewOrderPage = topMenuGeneralPage.clickOnNewOrderFromTopMenu();
        clientNewOrderPage.setOrder(order, filepath, perUnit, visibility, articles);

        return new OrderWorkFlow();
    }
}
