package Actions.General;

import Entities.LoginObject;
import PageObjects.General.LeftMenuGeneralPage;
import PageObjects.General.LoginPage;
import PageObjects.General.MyOrdersPage;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 27.08.2015.
 */
public class RegistrationAndLogin {


    public static MyOrdersPage loginAs(WebDriver driver, LoginObject clientLogin) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage(driver);
        loginPage.loginLinkClick();
        String header = loginPage.welcomeLoginHeader();
        assertEquals(header, "Welcome to ContentMart.in");

        loginPage.setLoginField(clientLogin.getLogin());
        loginPage.setPasswordField(clientLogin.getPassword());
        MyOrdersPage my_ordersPage = loginPage.submit();
        assertEquals(driver.getTitle(), "My Orders | ContentMart");

        return my_ordersPage;
    }


    public static LoginPage logOut(WebDriver driver) {

        LeftMenuGeneralPage leftMenuGeneralPage = new LeftMenuGeneralPage(driver);
        leftMenuGeneralPage.clickOnProfileLeftMenu();
        LoginPage loginPage = leftMenuGeneralPage.clickOnLoOutLeftMenu(driver);

        return loginPage;
    }
}
