package Tests.EditProfile;

import Actions.Writer.GoToWriterProfile;
import PageObjects.Writer.WriterEditProfilePage;
import PageObjects.Writer.WriterProfilePage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static Actions.Writer.GoToWriterProfile.verifyImageInFancyBox;

/**
 * Created by CMG_TEST on 16.10.2015.
 */
public class WriterAvatarUploading extends BaseTest {


    @Test(groups = {"regress2.2"})
    public void WriterAvatarUploading() throws InterruptedException, IOException {

        String filepath = System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\avatar.jpg";

        WriterProfilePage writerProfilePage = GoToWriterProfile.goToMyProfile(driver, writerLogin);
        WriterEditProfilePage editProfilePage = writerProfilePage.clickOnEditProfileButton();
        editProfilePage.uploadNewAvatarPhoto(filepath);
        verifyImageInFancyBox(driver, editProfilePage, filepath);
        editProfilePage.saveAvatarPhotoButtonClick();

        Assert.assertTrue(editProfilePage.avatarProgressCount());
        editProfilePage.clickOnSaveChangesButton();
    }
}