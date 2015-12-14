package Actions.General;

import Entities.LoginObject;
import GeneralHelpers.DBUtill;
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
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;
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

        }catch (Exception e){
            System.out.println("For some reasons driver can`t open activation link, we gonna try it one more time after timeout");
            Thread.sleep(5000);
            driver.get(activLink);
        }
        return myOrdersPage;
    }


    public static Boolean isEvenID(int id){
        return id%2 == 1;
    }


    private static String createTestUserName(){
        return "AutoBot-" + randomNumeric(4) + randomAlphabetic(3);
    }

    @SuppressWarnings("ConstantConditions")
    public static void checkForExitingUserAndDeleteIt(DBUtill dbUtill, String query, String column, String email) throws SQLException, InterruptedException, IOException {
        String result = dbUtill.getColumn(query, column);
        if (result != null ) {
            if (result.equalsIgnoreCase(email)){
                deleteCreatedUserFromDB(dbUtill, email);
            }
        } else System.out.println("No such user in DB");
    }

    public static String deleteCreatedUserFromDB(DBUtill dbUtill, String whereEmail) throws SQLException, IOException {

        String mail = randomNumeric(4) + randomAlphabetic(3) + randomNumeric(3) + "@testmail.com' ";
        dbUtill.executeUpdate(
                "UPDATE users " +
                        "SET email = '" + mail +
                        "WHERE email = '" + whereEmail + "'"
        );
        return mail;
    }

    public  static String getActivationLinkFromTargetMessage(Message message) throws MessagingException, IOException {

        Multipart multipart = (Multipart)message.getContent();
        for (int i=0; i<multipart.getCount(); i++){

            BodyPart bodyPart = multipart.getBodyPart(i);
            String s = (String)bodyPart.getContent();

            Pattern p = Pattern.compile("<a[^>]*href=\"(http[s]?:[^\"]*)\".*Activate<\\/a>");
            Matcher m = p.matcher(s);

            if(m.find()){
                String url = m.group(1);
                System.out.println("Regexp : " + url);
                return url;
            }
        }
        return null;
    }
}
