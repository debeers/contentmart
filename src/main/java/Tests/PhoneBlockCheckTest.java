package Tests;

import Entities.LoginObject;
import PageObjects.General.MyOrdersPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

import static Actions.General.RegistrationAndLogin.isEvenID;
import static Actions.General.RegistrationAndLogin.logOut;
import static Actions.General.RegistrationAndLogin.loginAs;
import static GeneralHelpers.PropertiesLoader.propertyXMLoader;

/**
 * Created by DeBeers on 30.11.2015.
 */
public class PhoneBlockCheckTest extends BaseTest{

    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public void PhoneBlockCheckTest() throws Exception {

        Properties props = propertyXMLoader(System.getProperty("user.dir") +
                "\\src\\main\\java\\tests\\TestDataXML\\Registration\\PhoneBlockCheck.xml");

        LoginObject evenUser = new LoginObject(props.getProperty("evenUserLogin"), props.getProperty("evenUserPassword"));
        MyOrdersPage myOrdersPage = loginAs(driver, evenUser);

        if (isEvenID(props.getProperty("evenUserLogin"))) {

            Assert.assertEquals(myOrdersPage.getNumberFromTheHelpBlock(), props.getProperty("helpBlock"));
        }

        logOut(driver);
    }

}
