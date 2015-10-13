package Actions.General;

import Entities.LoginObject;
import PageObjects.General.MyOrdersPage;
import org.openqa.selenium.WebDriver;

import static Actions.General.RegistrationAndLogin.loginAs;

/**
 * Created by CMG_TEST on 13.10.2015.
 */
public class GoToAccountDetail {


    public static void goToAccountSettings(WebDriver driver, LoginObject login){


        MyOrdersPage myOrdersPage = loginAs(driver, login);
        myOrdersPage.clickOnProfileFromLeftMenu();
        myOrdersPage.clickOnAccountSettingsFromLeftMenu();






    }


}
