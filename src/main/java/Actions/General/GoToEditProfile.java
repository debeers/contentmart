package Actions.General;

import Entities.LoginObject;
import PageObjects.General.EditProfilePage;
import PageObjects.General.MyOrdersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static Tests.BaseTest.driver;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class GoToEditProfile {


    public static EditProfilePage goToEditProfile(WebDriver driver, LoginObject clientLogin) throws InterruptedException {

        MyOrdersPage defaultClientPage = RegistrationAndLogin.loginAs(driver, clientLogin);
        defaultClientPage.clickOnProfileLeftMenu();
        EditProfilePage editProfilePage = defaultClientPage.clickOnEditProfileLeftMenu();

        $WaitFor(

                editProfilePage.nickNameField,
                editProfilePage.zipField,
                editProfilePage.saveChangesButton,
                editProfilePage.adressTextArea,
                editProfilePage.panField,
                editProfilePage.phoneField,
                editProfilePage.stateSelect,
                editProfilePage.citySelect

        );
        return editProfilePage;

    }


    public static Boolean checkForTriggersStatus(String status) {

        EditProfilePage ep = new EditProfilePage(driver);

        for (WebElement trigger : ep.triggers) {

            if (status == "OFF" && $(trigger).getAttribute("class").contains("switch toggle-on")) {

                System.out.println("Some elements did not save changes: " + trigger.getAttribute("class"));
                return false;

            }else if(status == "ON" && $(trigger).getAttribute("class").contains("switch toggle-off")) {
                System.out.println("Some elements did not save changes: " + trigger.getAttribute("class"));
                return false;

            }

        }
        return true;
    }
}
