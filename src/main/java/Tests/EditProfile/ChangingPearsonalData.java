package Tests.EditProfile;

import Actions.Writer.GoToWriterProfile;
import PageObjects.Writer.WriterEditProfilePage;
import PageObjects.Writer.WriterProfilePage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.Writer.GoToWriterProfile.checkForOld;
import static Actions.Writer.GoToWriterProfile.randomSelectDateOfBirth;

/**
 * Created by CMG_TEST on 12.10.2015.
 */
public class ChangingPearsonalData extends BaseTest{


    @Test(groups = {"regress2.2"})
    public void ChangingPearsonalData() throws InterruptedException {

        WriterProfilePage writerProfilePage = GoToWriterProfile.goToMyProfile(driver, writerLogin);
        writerProfilePage.clickOnEditProfileButton();

        WriterEditProfilePage writerEditProfilePage = new WriterEditProfilePage(driver);
        String year = randomSelectDateOfBirth(writerEditProfilePage);
        writerEditProfilePage.clickOnSaveChangesButton();

        Assert.assertTrue(checkForOld(year, writerProfilePage.getYearsOld()));

    }
}
