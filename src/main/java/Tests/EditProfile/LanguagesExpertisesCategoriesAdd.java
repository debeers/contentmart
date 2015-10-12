package Tests.EditProfile;

import Actions.Writer.GoToWriterProfile;
import PageObjects.Writer.WriterEditProfilePage;
import PageObjects.Writer.WriterProfilePage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static Actions.Writer.GoToWriterProfile.checkForMadeCategoriesChanges;
import static Actions.Writer.WriterGoToEditProfile.setupSkills;

/**
 * Created by DeBeers on 04.10.2015.
 */
public class LanguagesExpertisesCategoriesAdd extends BaseTest {


    @Test(groups = {"regress2.2"})
    public void LanguagesExpertisesCategoriesAdd() throws InterruptedException {

        WriterProfilePage writerProfilePage = GoToWriterProfile.goToMyProfile(driver, writerLogin);
        writerProfilePage.clickOnEditProfileButton();

        WriterEditProfilePage writerEditProfilePage = new WriterEditProfilePage(driver);
        writerEditProfilePage.clear();

        List<String> madeChangesFor = setupSkills(driver, "Languages", "add");
        writerEditProfilePage.clickOnSaveChangesButton();

        Assert.assertTrue(checkForMadeCategoriesChanges(writerProfilePage, madeChangesFor, "Languages"));

    }
}
