package Tests;

import Actions.General.GoToAccountSettings;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.EmailNotificationsPage;
import org.testng.annotations.Test;

import static Actions.General.GoToAccountSettings.isTriggersHaveNeededConditions;
import static org.testng.Assert.assertTrue;

/**
 * Created by DeBeers on 03.10.2015.
 */
public class EmailNotificationsSavingTest extends BaseTest {

    @Test
    public void Notifications_Test() throws InterruptedException {

        AccountDetailsPage accountDetailsPage = GoToAccountSettings.goToEditProfile(driver, clientLogin);
        EmailNotificationsPage emailNotificationsPage = accountDetailsPage.clickOnEmailNotificationsLink(driver);

        emailNotificationsPage.switchTriggersOFF();
        Thread.sleep(4000);
        driver.navigate().refresh();
        assertTrue(isTriggersHaveNeededConditions("OFF"));

        emailNotificationsPage.switchTriggersON();
        Thread.sleep(4000); // need to save changes, waits for server side...
        driver.navigate().refresh();

        assertTrue(isTriggersHaveNeededConditions("ON"));
    }
}
