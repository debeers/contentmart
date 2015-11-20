package Tests;

import GeneralHelpers.GmailListener;
import PageObjects.General.MyOrdersPage;
import org.testng.annotations.Test;

import javax.mail.Message;

import static Actions.General.RegistrationAndLogin.registerAs;
import static GeneralHelpers.DBWorker.deleteCreatedUserFromDB;
import static GeneralHelpers.DBWorker.setUserNickName;
import static GeneralHelpers.GmailListener.getActivationLinkFromTargetMessage;
import static org.testng.Assert.assertEquals;


/**
 * Created by DeBeers on 02.11.2015.
 */
public class CreateNewUserTest extends BaseTest {

    @Test(groups={"regress 1.0"})
    public static void CreateNewUserTest() throws Exception {

        String subject   = "Welcome to ContentMart.in";
        String fromEmail = "info@contentmart.in";
        Boolean isSeen   = false;
        int timeToWait   = 600;

        String userType          = "client";
        String userNickName      =  setUserNickName("writer");
        String userEmail         = "contentmartmai@gmail.com";
        String userPassword      = "7777777";
        String registrationTitle = "Register as a Client | ContentMart";

        String title = registerAs(driver, userType, userNickName, userEmail, userPassword);
        assertEquals(title, registrationTitle);
        Message targetMessage = new GmailListener()
                .startListening(gmailCredentials, subject, fromEmail, isSeen, timeToWait);

        String activLink = getActivationLinkFromTargetMessage(targetMessage);
        driver.get(activLink);

        MyOrdersPage myOrdersPage = new MyOrdersPage(driver);

        assertEquals(driver.getTitle(), "My Orders | ContentMart");
        assertEquals(myOrdersPage.getUserNickNameFromProfileDropMenu().trim(), userNickName);
        deleteCreatedUserFromDB(userEmail);

    }
}
