package Tests;

import Entities.UserObject;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.General.GoToAccountSettings.setWriterAccountSettings;
import static Actions.General.RegistrationAndLogin.loginAs;

/**
 * Created by CMG_TEST on 13.10.2015.
 */
public class ChangeWriterDetailsTest extends BaseTest {

    @Test
    public void ChangeWriterAcoountSettings_Test() throws Exception {

        UserObject user = new UserObject();

        MyOrdersPage myOrdersPage = loginAs(driver, writerLogin);
        myOrdersPage.clickOnProfileFromTopMenu();
        AccountDetailsPage accountDetailsPage = myOrdersPage.clickOnAccountSettingsDropMenu();
        setWriterAccountSettings(user, accountDetailsPage);
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
