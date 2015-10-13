package Actions.General;

import Entities.LoginObject;
import Entities.UserObject;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.EmailNotificationsPage;
import PageObjects.General.MyOrdersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static Tests.BaseTest.driver;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class GoToAccountSettings {


    public static AccountDetailsPage goToEditProfile(WebDriver driver, LoginObject clientLogin) throws InterruptedException {

        MyOrdersPage defaultClientPage = RegistrationAndLogin.loginAs(driver, clientLogin);
        defaultClientPage.clickOnProfileFromLeftMenu();
        AccountDetailsPage accountDetailsPage = defaultClientPage.clickOnAccountSettingsFromLeftMenu();

        $WaitFor(
                accountDetailsPage.saveChangesButton,
                accountDetailsPage.biographyField
        );
        return accountDetailsPage;
    }


    public static Boolean checkForTriggersStatus(String status) {

        EmailNotificationsPage ep = new EmailNotificationsPage(driver);

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


    public static void setUserData(UserObject user, AccountDetailsPage accountDetailsPage){


        user.setNickname(accountDetailsPage.getUserNickName());
        accountDetailsPage.setFirstNameField(user.getFirstname());
        accountDetailsPage.setLastNameField(user.getLastname());
        accountDetailsPage.setPhoneField(user.getPhone());
        accountDetailsPage.setPanField(user.getPan());
        accountDetailsPage.setStateField(user.getState());
        accountDetailsPage.setCityField(user.getCity());
        accountDetailsPage.setAddressField(user.getAddress());
        accountDetailsPage.setZipField(user.getZip());
        accountDetailsPage.setBiographyField(user.getBio());

    }


}
