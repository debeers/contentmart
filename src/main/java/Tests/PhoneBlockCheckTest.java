package Tests;

import Entities.LoginObject;
import Entities.UserModel;
import PageObjects.General.MyOrdersPage;
import Repository.UserModelRepo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

import static Actions.General.RegistrationAndLogin.isUserHaveEvenID;
import static Actions.General.RegistrationAndLogin.logOut;
import static Actions.General.RegistrationAndLogin.loginAs;
import static Utilities.DateTimeUtils.getSystemTime_AM_PM;
import static Utilities.PropertiesLoader.propertyXMLoader;

/**
 * Created by DeBeers on 30.11.2015.
 */
public class PhoneBlockCheckTest extends BaseTest{

    @Test
    public void PhoneBlockCheckTest_Test() throws Exception {

        Properties props = propertyXMLoader(System.getProperty("user.dir") +
                "\\src\\main\\java\\tests\\TestDataXML\\Registration\\PhoneBlockCheck.xml");

        UserModel user = UserModelRepo.getUserDATAFromDB(props.getProperty("evenUserLogin"));

        LoginObject evenUser = new LoginObject(props.getProperty("evenUserLogin"), props.getProperty("evenUserPassword"));
        MyOrdersPage myOrdersPage = loginAs(driver, evenUser);
        System.out.println("user id is : " + user.getUserId());

        if (isUserHaveEvenID(user.getUserId())) {

            int hours = Integer.parseInt(getSystemTime_AM_PM().substring(0, 2));
            if( (hours >= 10 && getSystemTime_AM_PM().substring(6, 8).equalsIgnoreCase("AM")) ||
                    (hours < 7 && getSystemTime_AM_PM().substring(6, 8).equalsIgnoreCase("PM"))){

                Assert.assertEquals(myOrdersPage.getNumberFromTheHelpBlock(), props.getProperty("helpBlock_10_19"));

            }else Assert.assertEquals(myOrdersPage.getNumberFromTheHelpBlock(), props.getProperty("helpBlock_19_10"));
        }
        logOut(driver);
    }
}
