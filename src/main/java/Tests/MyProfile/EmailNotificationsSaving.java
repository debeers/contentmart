package Tests.MyProfile;

import Actions.General.GoToEditProfile;
import PageObjects.General.EditProfilePage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.General.GoToEditProfile.checkForTriggersStatus;
import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static org.testng.Assert.assertTrue;

/**
 * Created by DeBeers on 03.10.2015.
 */
public class EmailNotificationsSaving extends BaseTest{


    @Test(groups = {"regress2.2"})
    public void Notifications() throws InterruptedException {

        EditProfilePage editProfilePage = GoToEditProfile.goToEditProfile(driver, clientLogin);
        editProfilePage.clickOnEmailNotificationsLink();

        editProfilePage.switchTriggersOFF();
        Thread.sleep(4000);
        driver.navigate().refresh();
        waitForPageLoad(driver);
        assertTrue(checkForTriggersStatus("OFF"));

        editProfilePage.switchTriggersON();
        Thread.sleep(4000); // need to save changes, waits for server side...
        driver.navigate().refresh();
        waitForPageLoad(driver);

        assertTrue(checkForTriggersStatus("ON"));
    }
}
