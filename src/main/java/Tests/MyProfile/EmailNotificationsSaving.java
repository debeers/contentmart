package Tests.MyProfile;

import Actions.General.GoToEditProfile;
import Entities.LoginObject;
import PageObjects.General.EditProfilePage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.General.GoToEditProfile.checkForOFFTriggersStatus;
import static Actions.General.GoToEditProfile.checkForONTriggersStatus;
import static GeneralHelpers.GeneralWaits.waitForPageLoad;

/**
 * Created by DeBeers on 03.10.2015.
 */
public class EmailNotificationsSaving extends BaseTest{






    @Test(groups = {"regress2.2"})
    public void Notifications() throws InterruptedException {

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");


        EditProfilePage editProfilePage = GoToEditProfile.goToEditProfile(driver, clientLogin);
        editProfilePage.clickOnEmailNotificationsLink();

        editProfilePage.switchTriggersOFF();
        Thread.sleep(4000);
        driver.navigate().refresh();
        waitForPageLoad(driver);
        Assert.assertTrue(checkForOFFTriggersStatus());

        editProfilePage.switchTriggersON();
        Thread.sleep(4000);
        driver.navigate().refresh();
        waitForPageLoad(driver);

        Assert.assertTrue(checkForONTriggersStatus());




    }

}
