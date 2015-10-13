package Tests.MyProfile;

import Actions.General.GoToAccountSettings;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.EmailNotificationsPage;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.General.GoToAccountSettings.checkForTriggersStatus;
import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static org.testng.Assert.assertTrue;

/**
 * Created by DeBeers on 03.10.2015.
 */
public class EmailNotificationsSaving extends BaseTest{


    @Test(groups = {"regress2.2"})
    public void Notifications() throws InterruptedException {

        AccountDetailsPage accountDetailsPage = GoToAccountSettings.goToEditProfile(driver, clientLogin);
        EmailNotificationsPage emailNotificationsPage = accountDetailsPage.clickOnEmailNotificationsLink(driver);

        emailNotificationsPage.switchTriggersOFF();
        Thread.sleep(4000);
        driver.navigate().refresh();
        waitForPageLoad(driver);
        assertTrue(checkForTriggersStatus("OFF"));

        emailNotificationsPage.switchTriggersON();
        Thread.sleep(4000); // need to save changes, waits for server side...
        driver.navigate().refresh();
        waitForPageLoad(driver);

        assertTrue(checkForTriggersStatus("ON"));
    }
}
