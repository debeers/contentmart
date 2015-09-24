package Tests.UploadingFiles;

import Actions.Client.ClientAvatarAndSignUpload;
import DataProviders.LoginDataProvider;
import Entities.LoginObject;
import PageObjects.General.EditProfilePage;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class ClientAvatarUploading extends BaseTest {




    @Test(groups={"regress 1.0"}, dataProvider= "ClientLoginData", dataProviderClass = LoginDataProvider.class)
    public static void ClientAvatarUploading(Object clientLoginObject) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        String path = System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\DMX.jpg";

        EditProfilePage editProfilePage = ClientAvatarAndSignUpload.uploadUserPhoto(driver, clientLogin, path);
        editProfilePage.setNewProfilePhoto(path);
        Thread.sleep(1000);

        editProfilePage.clickOnSaveCropedAvatarButton();
        editProfilePage.waitForAvatarProgressBar();
        String progressBarStatus = editProfilePage.getAvatarProgressBarStatus();
        assertEquals(progressBarStatus, "100%");
        editProfilePage.clickOnSaveProfileButton();
        editProfilePage.waitForSettingsSuccessfullysavedAppear();

    }



}
