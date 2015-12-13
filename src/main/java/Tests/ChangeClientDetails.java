package Tests;

import Entities.UserObject;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.General.GoToAccountSettings.setClientAccountSettings;
import static Actions.General.GoToAccountSettings.setWriterAccountSettings;
import static Actions.General.RegistrationAndLogin.loginAs;

/**
 * Created by DeBeers on 18.10.2015.
 */
public class ChangeClientDetails extends BaseTest {


    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public void ChangeClientDetails() throws Exception {

        UserObject user = new UserObject();
        String country = "India";

        MyOrdersPage myOrdersPage = loginAs(driver, clientLogin);
        myOrdersPage.clickOnProfileFromTopMenu();
        AccountDetailsPage accountDetailsPage = myOrdersPage.clickOnAccountSettingsDropMenu();
        setClientAccountSettings(user, accountDetailsPage, country);
        accountDetailsPage.clickOnSaveChangesButton();
        driver.navigate().refresh();
        Thread.sleep(3000); //server side wait

        Assert.assertEquals(accountDetailsPage.getUserFirstName(), user.getFirstname());
        Assert.assertEquals(accountDetailsPage.getUserLastName(), user.getLastname());
        Assert.assertEquals(accountDetailsPage.getUserPhone(), user.getPhone());
        Assert.assertEquals(accountDetailsPage.getUserPan(), user.getPan());
        Assert.assertEquals(accountDetailsPage.getUserRegion(), user.getState());
        Assert.assertEquals(accountDetailsPage.getUserCity(), user.getCity());
        Assert.assertEquals(accountDetailsPage.getUserAdress(), user.getAddress());
        Assert.assertEquals(accountDetailsPage.getUserZip(), user.getZip());
    }
}
