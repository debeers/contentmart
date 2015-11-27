package Tests;

import GeneralHelpers.GmailListener;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.PartnersPage;
import PageObjects.Writer.WriterProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import javax.mail.Message;
import java.util.Properties;

import static Actions.General.RegistrationAndLogin.*;
import static GeneralHelpers.DBWorker.checkForExitingUser;
import static GeneralHelpers.DBWorker.deleteCreatedUserFromDB;
import static GeneralHelpers.DBWorker.setUserNickName;
import static GeneralHelpers.GmailListener.getActivationLinkFromTargetMessage;
import static GeneralHelpers.PropertiesLoader.*;
import static SQLRepo.General.checkUserExsistanceByMail;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;


public class WriterRegistrationViaLanding extends BaseTest{

    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public void RegisterAsWriterFromLanding() throws Exception {

        Properties props     = propertyXMLoader(System.getProperty("user.dir")+
                "\\src\\main\\java\\tests\\TestDataXML\\Registration\\WriterRegistrationViaLanding.xml");
        
        Boolean isSeen       = false;
        String userNickName  =  setUserNickName(props.getProperty("userRole"));

        checkForExitingUser(
                checkUserExsistanceByMail(
                        props.getProperty("userEmail")), "email", props.getProperty("userEmail")
        );

        String title = registerFromLandingAsWriter(
                driver,
                props.getProperty("URL"),
                userNickName,
                props.getProperty("userEmail"),
                props.getProperty("userPassword")
        );

        assertEquals(title, props.getProperty("registrationTitle"));

        Message targetMessage = new GmailListener()
                .startListening(
                        gmailCredentials,
                        props.getProperty("subject"),
                        props.getProperty("mail"),
                        isSeen,
                        Integer.valueOf(props.getProperty("timeout"))
                );

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
        Assert.assertEquals(writerProfilePage.languageDefText.getText(),   "User does not know any language.");
        Assert.assertEquals(writerProfilePage.expertisesDefText.getText(), "User does not have any expertises.");
        Assert.assertEquals(writerProfilePage.categoriesDefText.getText(), "User does not selected any writing categories.");
        Assert.assertEquals(writerProfilePage.writerDoesNotHavePortfolioText.getText(), userNickName + " does not have portfolio yet :(");

        deleteCreatedUserFromDB(props.getProperty("userEmail"));
    }

}
