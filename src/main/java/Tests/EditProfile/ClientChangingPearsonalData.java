package Tests.EditProfile;

import Actions.Client.ClientGoToProfilePage;
import PageObjects.Client.ClientEditProfilePage;
import PageObjects.Client.ClientProfilePage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.GregorianCalendar;
import static GeneralHelpers.ProfileHelper.userCheckForOld;
import static GeneralHelpers.ProfileHelper.userSelectDateOfBirth;

/**
 * Created by DeBeers on 18.10.2015.
 */
public class ClientChangingPearsonalData extends BaseTest {


    @Test(groups = {"regress2.2"})
    public void ClientChangingPearsonalData() throws InterruptedException {

        ClientProfilePage clientProfilePage = ClientGoToProfilePage.goToMyProfile(driver, clientLogin);
        clientProfilePage.clickOnEditProfileButton();

        ClientEditProfilePage clientEditProfilePage = new ClientEditProfilePage(driver);
        GregorianCalendar birthdaySet = userSelectDateOfBirth(clientEditProfilePage);

        clientEditProfilePage.clickOnSaveProfileChangesButton();
        Assert.assertTrue(userCheckForOld(birthdaySet, clientProfilePage));

    }

}
