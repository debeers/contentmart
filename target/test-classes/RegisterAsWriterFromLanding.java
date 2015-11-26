package Tests;

import GeneralHelpers.GmailListener;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.PartnersPage;
import PageObjects.Writer.WriterEditProfilePage;
import PageObjects.Writer.WriterProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import javax.mail.Message;

import static Actions.General.RegistrationAndLogin.*;
import static GeneralHelpers.DBWorker.checkForExitingUser;
import static GeneralHelpers.DBWorker.deleteCreatedUserFromDB;
import static GeneralHelpers.DBWorker.setUserNickName;
import static GeneralHelpers.GmailListener.getActivationLinkFromTargetMessage;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;


public class RegisterAsWriterFromLanding extends BaseTest{


    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public static void RegisterAsWriterFromLanding() throws Exception {

        String URL       = "https://dev.contentmart.in/forwriters";
        String subject   = "Welcome to ContentMart.in";
        String fromEmail = "info@contentmart.in";
        Boolean isSeen   = false;
        int timeToWait   = 600;

        String userType          = "writer";
        String userNickName      =  setUserNickName("writer");
        String userEmail         = "contentmartmai@gmail.com";
        String userPassword      = "7777777";
        String registrationTitle = "Register successful | ContentMart";
        String checkUser         = "SELECT * FROM users\n" +
                "WHERE email = \"contentmartmai@gmail.com\"";

        checkForExitingUser(checkUser, "email", userEmail);

        String title = registerFromLandingAs(driver, userType, userNickName, userEmail, userPassword, URL);
        assertEquals(title, registrationTitle);

        Message targetMessage = new GmailListener()
                .startListening(gmailCredentials, subject, fromEmail, isSeen, timeToWait);

        String activLink = getActivationLinkFromTargetMessage(targetMessage);
        driver.get(activLink);

        MyOrdersPage myOrdersPage = new MyOrdersPage(driver);
        Assert.assertTrue($(myOrdersPage.takeTheTestNowButton).isDisplayed());

        assertEquals(driver.getTitle(), "My Orders | ContentMart");
        assertEquals(myOrdersPage.getUserNickNameFromProfileDropMenu().trim(), userNickName);

        AccountDetailsPage accountDetailsPage = myOrdersPage.selectAccountSettingsFromMenu();

        Assert.assertEquals(accountDetailsPage.nickNameField.getAttribute("value").trim(), userNickName);
        Assert.assertEquals(accountDetailsPage.countryField.getAttribute("value"), "1");
        Assert.assertEquals(accountDetailsPage.getUserTimeZone().trim(), "321");

        WriterProfilePage writerProfilePage = accountDetailsPage.selectWriterProfileFromMenu();
        Assert.assertEquals(writerProfilePage.userName.getText().trim(), userNickName);
        Assert.assertTrue(writerProfilePage.takeTheTestNowButtonInHeader.isDisplayed());

        switchUser(driver, clientLogin);

        PartnersPage partnersPage = myOrdersPage.clickOnWritersFromTopMenu();
        partnersPage.search(userNickName).click();

        Assert.assertEquals(writerProfilePage.userName.getText(), userNickName);
        Assert.assertEquals(writerProfilePage.languageDefText.getText(), "User does not know any language.");
        Assert.assertEquals(writerProfilePage.expertisesDefText.getText(), "User does not have any expertises.");
        Assert.assertEquals(writerProfilePage.categoriesDefText.getText(), "User does not selected any writing categories.");
        Assert.assertEquals(writerProfilePage.writerDoesNotHavePortfolioText.getText(), userNickName + " does not have portfolio yet :(");

        deleteCreatedUserFromDB(userEmail);

    }

}
