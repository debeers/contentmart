package Tests;

import Actions.General.RegistrationAndLogin;
import PageObjects.General.LoginPage;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import static GeneralHelpers.PropertiesLoader.propertyXMLoader;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 20.11.2015.
 */
public class Login_Logout_Test extends BaseTest{

    private static Properties props() throws IOException {
        Properties props =  propertyXMLoader(System.getProperty("user.dir") +
                "\\src\\main\\java\\Tests\\TestDataXML\\Login\\Login_Logout.xml");
        return props;
    }

    @Test(groups = {"Fast_And_Furious_Smoke_1.0"})
    public void positive_Client_Login_LogOut() throws InterruptedException, IOException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage(driver);
        loginPage.loginLinkClick();
        assertEquals($(loginPage.welcomeLoginHeader).getText(), props().getProperty("WelcomeHeader"));

        loginPage.setLoginField(clientLogin.getLogin());
        loginPage.setPasswordField(clientLogin.getPassword());
        loginPage.submit();

        assertEquals(driver.getTitle(), props().getProperty("EnterTitle"));
        assertEquals(driver.getCurrentUrl(), baseUrl + props().getProperty("AdditionalURL"));

        RegistrationAndLogin.logOut(driver);
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }


    @Test(groups = {"Fast_And_Furious_Smoke_1.0"})
    public void positive_Writer_Login_LogOut() throws InterruptedException, IOException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage(driver);
        loginPage.loginLinkClick();
        assertEquals($(loginPage.welcomeLoginHeader).getText(), props().getProperty("WelcomeHeader"));

        loginPage.setLoginField(writerLogin.getLogin());
        loginPage.setPasswordField(writerLogin.getPassword());
        loginPage.submit();

        assertEquals(driver.getTitle(), props().getProperty("EnterTitle"));
        assertEquals(driver.getCurrentUrl(), baseUrl + props().getProperty("AdditionalURL"));

        RegistrationAndLogin.logOut(driver);
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }

}
