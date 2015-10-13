package Tests.AccountSettings;

import Entities.UserObject;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.General.GoToAccountSettings.setUserData;
import static Actions.General.RegistrationAndLogin.loginAs;

/**
 * Created by CMG_TEST on 13.10.2015.
 */
public class ChangeUserDetails extends BaseTest {


    @Test(groups={"regress 1.0"})
    public void ChangeUserDetails() throws Exception {


        UserObject user = new UserObject("", "Julius", "Avrora", "7777777777", "TYTGH4444T", "Goa", "Goa", "Ceasar ave 777", "888888", "New writer biography");


        MyOrdersPage myOrdersPage = loginAs(driver, writerLogin);
        myOrdersPage.clickOnProfileFromLeftMenu();
        AccountDetailsPage accountDetailsPage = myOrdersPage.clickOnAccountSettingsFromLeftMenu();
        setUserData(user, accountDetailsPage);
        accountDetailsPage.clickOnSaveChangesButton();
        // test in progress, compare`s & asserts left

    }
}
