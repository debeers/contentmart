package Actions.General;

import Entities.LoginObject;
import PageObjects.General.LoginPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.RegistrationFormPage;
import PageObjects.General.TopMenuGeneralPage;
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

        TopMenuGeneralPage topMenuGeneralPage = new TopMenuGeneralPage(driver);
        topMenuGeneralPage.clickOnProfileFromTopMenu();
        LoginPage loginPage = topMenuGeneralPage.clickOnLogOutFromDropMenu();

        return loginPage;
    }


    public static String registerAs(WebDriver driver, String userType, String nickname, String email, String password) throws InterruptedException {

        RegistrationFormPage registrationFormPage = null;
        String result = "";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage(driver);
        loginPage.registrationLinkClick();

        if (userType.equalsIgnoreCase("writer")) {
            registrationFormPage = loginPage.clickOnRegisterAsWriter();
            result = driver.getTitle();

        } else if (userType.equalsIgnoreCase("client")) {
            registrationFormPage = loginPage.clickOnRegisterAsClient();
            result = driver.getTitle();
        }

        assert registrationFormPage != null;
        registrationFormPage.setUserNickName(nickname);
        registrationFormPage.setUserEmail(email);
        registrationFormPage.setUserPassword(password);
        registrationFormPage.clickOnHeaderToDropWarnings();

        registrationFormPage.clickOnRegisterButton();

        return result;
    }

}
