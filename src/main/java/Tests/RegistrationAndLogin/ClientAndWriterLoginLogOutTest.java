package Tests.RegistrationAndLogin;

import Actions.RegistrationAndLogin;
import DataProviders.LoginDataProvider;
import Entities.LoginObject;
import PageObjects.General.LoginPage;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;


public class ClientAndWriterLoginLogOutTest extends BaseTest {


    @Test(groups = {"regress2.2"}, dataProvider = "ClientLoginData", dataProviderClass = LoginDataProvider.class)
    public void positive_Client_LoginLogOut(Object clientLoginObject) throws InterruptedException {

        LoginObject clientLogin = (LoginObject) clientLoginObject;

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


    @Test(groups = {"regress2.2"}, dataProvider = "WriterLoginData", dataProviderClass = LoginDataProvider.class)
    public void positive_Writer_LoginLogOut(Object writerLoginObj) throws InterruptedException {

        LoginObject writerLogin = (LoginObject) writerLoginObj;

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
