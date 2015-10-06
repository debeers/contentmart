package Tests.RegistrationAndLogin;

import Actions.General.RegistrationAndLogin;
import PageObjects.General.LoginPage;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;


public class ClientAndWriterLoginLogOutTest extends BaseTest {


    @Test(groups = {"regress2.2"})
    public void positive_Client_LoginLogOut() throws InterruptedException {

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


    @Test(groups = {"regress2.2"})
    public void positive_Writer_LoginLogOut() throws InterruptedException {

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
