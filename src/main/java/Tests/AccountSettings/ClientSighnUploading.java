package Tests.AccountSettings;

import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.General.RegistrationAndLogin.loginAs;
import static GeneralHelpers.ProfileHelper.verifyUploadedImages;

/**
 * Created by DeBeers on 19.10.2015.
 */
public class ClientSighnUploading extends BaseTest {

    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public void ClientSighnUploading() throws Exception {

        String filepath = System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\sign.jpg";

        MyOrdersPage myOrdersPage = loginAs(driver, clientLogin);
        myOrdersPage.clickOnProfileFromLeftMenu();
        AccountDetailsPage accountDetailsPage = myOrdersPage.clickOnAccountSettingsFromLeftMenu();
        accountDetailsPage.uploadNewSignature(filepath);

        Assert.assertTrue(verifyUploadedImages(accountDetailsPage, filepath));
        accountDetailsPage.clickOnSaveSignatureButton();

    }
}
