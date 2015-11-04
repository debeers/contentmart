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


    @Test(groups = {"Fast_And_Furious_Smoke_1.0"})
    public void WriterAvatarUploading() throws InterruptedException, IOException, NoSuchFieldException, NoSuchMethodException {

        String filepath = System.getProperty("user.dir") + "\\src\\main\\test\\resources\\avatar.jpg";

        WriterProfilePage writerProfilePage = WriterGoToProfilePage.goToMyProfile(driver, writerLogin);
        WriterEditProfilePage editProfilePage = writerProfilePage.writerClickOnEditProfileButton();
        editProfilePage.uploadNewAvatarPhoto(filepath);

        Assert.assertTrue(verifyUploadedImages(editProfilePage, filepath));
        editProfilePage.saveAvatarPhotoButtonClick();

        Assert.assertTrue(editProfilePage.avatarProgressCount());
        editProfilePage.clickOnSaveChangesButton();
    }

}