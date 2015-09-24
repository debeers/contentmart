package Actions.Client;

import Actions.RegistrationAndLogin;
import Entities.LoginObject;
import PageObjects.General.EditProfilePage;
import PageObjects.General.MyOrdersPage;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class ClientGoToEditProfile {


    public static EditProfilePage goToEditProfileUni(WebDriver driver, LoginObject clientLogin) throws InterruptedException {

        MyOrdersPage defaultClientPage = RegistrationAndLogin.loginAs(driver, clientLogin);
        defaultClientPage.clickOnProfileLeftMenu();
        EditProfilePage editProfilePage = defaultClientPage.clickOnEditProfileLeftMenu();

        $(editProfilePage.firstNameField).isDisplayed();
        $(editProfilePage.zipField).isDisplayed();
        $(editProfilePage.saveButton).isDisplayed();

        return editProfilePage;


    }




}
