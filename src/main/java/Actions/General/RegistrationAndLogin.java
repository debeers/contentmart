package Actions.General;

import Entities.LoginObject;
import GeneralHelpers.JDBCutil;
import PageObjects.General.LoginPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.RegistrationFormPage;
import PageObjects.General.TopMenuGeneralPage;
import PageObjects.Landings.ForClientsPage;
import PageObjects.Landings.ForWritersPage;
import com.codeborne.selenide.Condition;
import junit.framework.Assert;
import net.sf.qualitycheck.Throws;
import org.openqa.selenium.WebDriver;

import static GeneralHelpers.JDBCutil.*;
import static SQLRepo.General.checkUserID;
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
        registrationFormPage.register(nickname, email, password);
        return result;
    }


    public static String registerFromLandingAsWriter(WebDriver driver, String URL, String nickname, String email, String password) throws InterruptedException {

        ForWritersPage forWritersPage = new ForWritersPage(driver);
        forWritersPage.goToForWritersLanding(URL);
        RegistrationFormPage registrationFormPage = forWritersPage.clickOnRegisterNowButton();

        try {
            registrationFormPage.getHeader().trim().equals("Register as a Writer");

        }catch (Exception e) {
            System.out.println("Wrong header, probably we are not at required page");
        }

        registrationFormPage.register(nickname, email, password);
        $(registrationFormPage.successMessageAfterSubmitRegistration).shouldBe(Condition.visible);
        return driver.getTitle();
    }


    public static String registerFromLandingAsClient(WebDriver driver, String URL, String nickname, String email, String password) throws InterruptedException {

        ForClientsPage forClientsPage = new ForClientsPage(driver);
        forClientsPage.goToForClientsLanding(URL);
        RegistrationFormPage registrationFormPage = forClientsPage.fillRegistrationFormFromLanding(nickname, email, password);
        $(registrationFormPage.successMessageAfterSubmitRegistration).shouldBe(Condition.visible);
        return driver.getTitle();
    }


    public static Boolean isEvenID(String email){

        int res = Integer.parseInt(getUserID(email));

        if(res%2 == 1){
            return true;
        }else
            return false;
    }


    public static String getUserID(String email){

        String res = executeQuery(checkUserID(email), "id");

        if(res != null){
            return res;
        }
        return null;
    }

}
