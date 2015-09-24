package Actions.Client;

import Entities.LoginObject;
import PageObjects.General.EditProfilePage;
import org.openqa.selenium.WebDriver;

/**
 * Created by CMG_TEST on 22.09.2015.
 */
public class ClientAvatarAndSignUpload {


    public static EditProfilePage uploadUserPhoto(WebDriver driver, LoginObject clientLogin, String path) throws InterruptedException {

        EditProfilePage editProfilePage = ClientGoToEditProfile.goToEditProfileUni(driver, clientLogin);
        editProfilePage.setNewProfilePhoto(path);
        Thread.sleep(1000);

        editProfilePage.clickOnSaveCropedAvatarButton();
        editProfilePage.waitForAvatarProgressBar();


        return editProfilePage;
    }










}
