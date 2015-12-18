package Actions.General;

import Entities.LoginObject;
import PageObjects.General.*;
import PageObjects.Landings.ForClientsPage;
import PageObjects.Landings.ForWritersPage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
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


    public static LoginPage logOut(WebDriver driver) throws InterruptedException {

        TopMenuGeneralPage topMenuGeneralPage = new TopMenuGeneralPage(driver);
        LoginPage loginPage = topMenuGeneralPage.selectLogoutFromMenu();

        return loginPage;
    }


    public static MyOrdersPage switchUser(WebDriver driver, LoginObject userRole) throws InterruptedException {

        logOut(driver);
        loginAs(driver, userRole);

        return new MyOrdersPage(driver);
    }


    public static String registerAsClientFromMainPageAndGetPageTitle(WebDriver driver, String nickname, String email, String password) throws Exception{

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage(driver);
        loginPage.registrationLinkClick();
        RegistrationFormPage registrationFormPage = loginPage.clickOnRegisterAsClient();

        registrationFormPage.register(nickname, email, password);
        $(registrationFormPage.successMessageAfterSubmitRegistration).shouldBe(Condition.visible);

        return driver.getTitle();
    }


    public static String registerAsWriterFromMainPageAndGetPageTitle(WebDriver driver, String nickname, String email, String password) throws Exception {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage(driver);
        loginPage.registrationLinkClick();

        RegistrationFormPage registrationFormPage = loginPage.clickOnRegisterAsWriter();
        registrationFormPage.register(nickname, email, password);
        $(registrationFormPage.successMessageAfterSubmitRegistration).shouldBe(Condition.visible);

        return driver.getTitle();
    }


    public static String registerFromLandingAsWriterAndGetPageTitle(WebDriver driver, String URL, String nickname, String email, String password) throws InterruptedException {

        ForWritersPage forWritersPage = new ForWritersPage(driver);
        forWritersPage.goToForWritersLanding(URL);
        RegistrationFormPage registrationFormPage = forWritersPage.clickOnRegisterNowButton();

        registrationFormPage.register(nickname, email, password);
        $(registrationFormPage.successMessageAfterSubmitRegistration).shouldBe(Condition.visible);

        return driver.getTitle();
    }


    public static String registerFromLandingAsClientAndGetPageTitle(WebDriver driver, String URL, String nickname, String email, String password) throws InterruptedException {

        ForClientsPage forClientsPage = new ForClientsPage(driver);
        forClientsPage.goToForClientsLanding(URL);
        RegistrationFormPage registrationFormPage = forClientsPage.fillRegistrationFormFromLanding(nickname, email, password);
        $(registrationFormPage.successMessageAfterSubmitRegistration).shouldBe(Condition.visible);

        return driver.getTitle();
    }

    public static MyOrdersPage activateUserAccount(WebDriver driver, String activLink, String title) throws InterruptedException {

        driver.get(activLink);
        MyOrdersPage myOrdersPage = new MyOrdersPage(driver);
        try{
            driver.getTitle().equalsIgnoreCase(title);

        }catch (Exception e){ //sometimes, something goes wrong and driver for some reasons can`t get activLink from first time, so I deside to give a second chance to get it
            System.out.println("For some reasons driver can`t open activation link, we gonna try it one more time after timeout");
            Thread.sleep(5000);
            driver.get(activLink);
        }
        return myOrdersPage;
    }

    public static Boolean isUserHaveEvenID(int id){
        return id%2 == 1;
    }

}
