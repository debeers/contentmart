package Tests.EditProfile;

import Actions.Writer.WriterGoToProfilePage;
import PageObjects.Writer.WriterEditProfilePage;
import PageObjects.Writer.WriterProfilePage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.GregorianCalendar;
import static GeneralHelpers.ProfileHelper.userCheckForOld;
import static GeneralHelpers.ProfileHelper.userSelectDateOfBirth;

/**
 * Created by CMG_TEST on 12.10.2015.
 */
public class WriterChangingPearsonalData extends BaseTest{


    @Test(groups = {"Fast_And_Furious_Smoke_1.0"})
    public void WriterChangingPearsonalData() throws InterruptedException {

        WriterProfilePage writerProfilePage = WriterGoToProfilePage.goToMyProfile(driver, writerLogin);
        writerProfilePage.writerClickOnEditProfileButton();

        WriterEditProfilePage writerEditProfilePage = new WriterEditProfilePage(driver);
        GregorianCalendar birthdaySet = userSelectDateOfBirth(writerEditProfilePage);
        writerEditProfilePage.clickOnSaveChangesButton();

        Assert.assertTrue(userCheckForOld(birthdaySet, writerProfilePage));
    }

}
