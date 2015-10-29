package Tests.AccountSettings;

import Entities.UserObject;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.General.GoToAccountSettings.setUserData;
import static Actions.General.RegistrationAndLogin.loginAs;

/**
 * Created by CMG_TEST on 13.10.2015.
 */
public class ChangeWriterDetails extends BaseTest {


    @Test(groups={"regress 1.0"})
    public void ChangeUserDetails() throws Exception {

        UserObject user = new UserObject();

        MyOrdersPage myOrdersPage = loginAs(driver, writerLogin);
        myOrdersPage.clickOnProfileFromLeftMenu();
        AccountDetailsPage accountDetailsPage = myOrdersPage.clickOnAccountSettingsFromLeftMenu();
        setUserData(user, accountDetailsPage);
        accountDetailsPage.clickOnSaveChangesButton();
        driver.navigate().refresh();
        Thread.sleep(3000); //server side wait

        Assert.assertEquals(accountDetailsPage.getUserFirstName(), user.getFirstname());
        Assert.assertEquals(accountDetailsPage.getUserLastName(), user.getLastname());
        Assert.assertEquals(accountDetailsPage.getUserPhone(), "+91-"+user.getPhone());
        Assert.assertEquals(accountDetailsPage.getUserPan(), user.getPan());
        Assert.assertEquals(accountDetailsPage.getUserState(), user.getState());
        Assert.assertEquals(accountDetailsPage.getUserCity(), user.getCity());
        Assert.assertEquals(accountDetailsPage.getUserAdress(), user.getAddress());
        Assert.assertEquals(accountDetailsPage.getUserZip(), user.getZip());

    }
}
