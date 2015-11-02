package Tests.MyProfile;

import Actions.General.GoToAccountSettings;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.EmailNotificationsPage;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.General.GoToAccountSettings.isTriggersHaveNeededConditions;
import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static org.testng.Assert.assertTrue;

/**
 * Created by DeBeers on 03.10.2015.
 */
public class EmailNotificationsSaving extends BaseTest{


    @Test(groups = {"Fast_And_Furious_Smoke_1.0"})
    public void Notifications() throws InterruptedException {

        AccountDetailsPage accountDetailsPage = GoToAccountSettings.goToEditProfile(driver, clientLogin);
        EmailNotificationsPage emailNotificationsPage = accountDetailsPage.clickOnEmailNotificationsLink(driver);

        emailNotificationsPage.switchTriggersOFF();
        Thread.sleep(4000);
        driver.navigate().refresh();
        waitForPageLoad(driver);
        assertTrue(isTriggersHaveNeededConditions("OFF"));

        emailNotificationsPage.switchTriggersON();
        Thread.sleep(4000); // need to save changes, waits for server side...
        driver.navigate().refresh();
        waitForPageLoad(driver);

        assertTrue(isTriggersHaveNeededConditions("ON"));
    }
}
