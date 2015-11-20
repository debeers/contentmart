package Tests;

import Actions.General.RegistrationAndLogin;
import PageObjects.General.LoginPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 20.11.2015.
 */
public class Login_Logout_Test extends BaseTest{

    @Test(groups = {"Fast_And_Furious_Smoke_1.0"})
    public void positive_Client_Login_LogOut() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage(driver);
        loginPage.loginLinkClick();
        assertEquals($(loginPage.welcomeLoginHeader).getText(), "Welcome to ContentMart.in");

        loginPage.setLoginField(clientLogin.getLogin());
        loginPage.setPasswordField(clientLogin.getPassword());
        loginPage.submit();

        assertEquals(driver.getTitle(), "My Orders | ContentMart");
        assertEquals(driver.getCurrentUrl(), baseUrl + "my_orders");

        RegistrationAndLogin.logOut(driver);
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }


    @Test(groups = {"Fast_And_Furious_Smoke_1.0"})
    public void positive_Writer_Login_LogOut() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage(driver);
        loginPage.loginLinkClick();
        assertEquals($(loginPage.welcomeLoginHeader).getText(), "Welcome to ContentMart.in");

        loginPage.setLoginField(writerLogin.getLogin());
        loginPage.setPasswordField(writerLogin.getPassword());
        loginPage.submit();

        assertEquals(driver.getTitle(), "My Orders | ContentMart");
        assertEquals(driver.getCurrentUrl(), baseUrl + "my_orders");

        RegistrationAndLogin.logOut(driver);
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }

}
