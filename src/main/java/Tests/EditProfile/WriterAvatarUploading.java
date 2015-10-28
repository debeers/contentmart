package Tests.EditProfile;

import Actions.Writer.WriterGoToProfilePage;
import PageObjects.Writer.WriterEditProfilePage;
import PageObjects.Writer.WriterProfilePage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import static GeneralHelpers.ProfileHelper.verifyUploadedImages;

/**
 * Created by CMG_TEST on 16.10.2015.
 */
public class WriterAvatarUploading extends BaseTest {


    @Test(groups = {"regress2.2"})
    public void WriterAvatarUploading() throws InterruptedException, IOException, NoSuchFieldException, NoSuchMethodException {

        String filepath = System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\avatar.jpg";

        WriterProfilePage writerProfilePage = WriterGoToProfilePage.goToMyProfile(driver, writerLogin);
        WriterEditProfilePage editProfilePage = writerProfilePage.writerClickOnEditProfileButton();
        editProfilePage.uploadNewAvatarPhoto(filepath);

        Assert.assertTrue(verifyUploadedImages(editProfilePage, filepath));
        editProfilePage.saveAvatarPhotoButtonClick();

        Assert.assertTrue(editProfilePage.avatarProgressCount());
        editProfilePage.clickOnSaveChangesButton();
    }

}