package Tests.UploadingFiles;

import Actions.Client.ClientGoToEditProfile;
import DataProviders.LoginDataProvider;
import Entities.LoginObject;
import PageObjects.General.EditProfilePage;
import Tests.BaseTest;
import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class ClientSignUpload extends BaseTest {


    @Test(groups={"regress 1.0"}, dataProvider= "ClientLoginData", dataProviderClass = LoginDataProvider.class)
    public static void ClientAvatarUploading(Object clientLoginObject) throws InterruptedException {


        LoginObject clientLogin = (LoginObject) clientLoginObject;


        String path = System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\DMX.jpg";

        EditProfilePage goToEditProfilePage = ClientGoToEditProfile.goToEditProfileUni(driver, clientLogin);

        goToEditProfilePage.uploadSign(path);
        Thread.sleep(1500);


        goToEditProfilePage.waitForSignProgressBarAppear();
        $(goToEditProfilePage.saveCropedSignButton).should(Condition.disappear);
        goToEditProfilePage.clickOnSaveProfileButton();
        goToEditProfilePage.waitForSettingsSuccessfullysavedAppear();




    }
}
