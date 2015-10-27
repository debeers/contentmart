package Tests.EditProfile;

import Actions.Client.ClientGoToProfilePage;
import PageObjects.Client.ClientEditProfilePage;
import PageObjects.Client.ClientProfilePage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import static GeneralHelpers.ProfileHelper.getFileHash;
import static GeneralHelpers.ProfileHelper.verifyUploadedImages;

/**
 * Created by DeBeers on 17.10.2015.
 */
public class ClientAvatarUploading extends BaseTest{


    @Test(groups = {"regress2.2"})
    public void ClientAvatarUploading() throws InterruptedException, IOException, NoSuchFieldException, NoSuchMethodException {

        String filepath = System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\avatar.jpg";

        ClientProfilePage clientEditProfilePage = ClientGoToProfilePage.goToMyProfile(driver, clientLogin);
        ClientEditProfilePage editProfilePage = clientEditProfilePage.clickOnEditProfileButton();
        editProfilePage.uploadNewAvatarPhoto(filepath);

        Assert.assertTrue(verifyUploadedImages(editProfilePage, filepath));
        editProfilePage.saveAvatarPhotoButtonClick();

        Assert.assertTrue(editProfilePage.avatarProgressCount());
        editProfilePage.clickOnSaveProfileChangesButton();
    }


}
