package Tests;

import Entities.Mail;
import GeneralHelpers.DBUtill;
import GeneralHelpers.GmailListener;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.PartnersPage;
import PageObjects.Writer.WriterProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.Message;
import java.util.Properties;

import static Actions.General.RegistrationAndLogin.logOut;
import static Actions.General.RegistrationAndLogin.registerAsWriterFromMainPage;
import static Actions.General.RegistrationAndLogin.switchUser;
import static GeneralHelpers.DBWorker.checkForExitingUserAndDeleteIt;
import static GeneralHelpers.DBWorker.deleteCreatedUserFromDB;
import static GeneralHelpers.GeneralHelpers.setRandomUserNickName;
import static GeneralHelpers.GmailListener.getActivationLinkFromTargetMessage;
import static GeneralHelpers.MailCreator.createNewUserMail;
import static GeneralHelpers.PropertiesLoader.propertyXMLoader;
import static SQLRepo.General.checkUserExsistanceByMail;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 30.11.2015.
 */
public class WriterRegistrationViaMainPage extends BaseTest{

    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public void WriterRegistrationViaMainPage() throws Exception {

        Properties props     = propertyXMLoader(System.getProperty("user.dir") +
                "\\src\\main\\java\\tests\\TestDataXML\\Registration\\WriterRegistrationViaMainPage.xml");

        String userDBconn = "\\src\\main\\java\\GeneralHelpers\\SettingsXML\\DB_CONNECTION.xml";

        Mail mail = new Mail();

        DBUtill dbUtill_User = new DBUtill();
        dbUtill_User = dbUtill_User.initDB(userDBconn);

        Boolean isSeen       = false;
        String userNickName  =  setRandomUserNickName(props.getProperty("userRole"));

        checkForExitingUserAndDeleteIt(
                dbUtill_User,
                checkUserExsistanceByMail(mail.getId()),
                "email", mail.getId()
        );


        String email = createNewUserMail(
                mail.initMail(
                        props.getProperty("userRole")), dbUtill_User
        );


        String title = registerAsWriterFromMainPage(
                driver,
                userNickName,
                email,
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

        try{
            myOrdersPage.takeTheTestNowButton.isDisplayed();

        }catch (Exception e){
            System.out.println("For some reasons driver can`t open activation link, we gonna try it one more time after timeout");
            Thread.sleep(5000);
            driver.get(activLink);
        }

        myOrdersPage = new MyOrdersPage(driver);
        Assert.assertTrue($(myOrdersPage.takeTheTestNowButton).isDisplayed());

        assertEquals(driver.getTitle(), "My Orders | ContentMart");
        assertEquals(myOrdersPage.getUserNickNameFromProfileDropMenu().trim(), userNickName);

        AccountDetailsPage accountDetailsPage = myOrdersPage.selectAccountSettingsFromMenu();

        Assert.assertEquals(accountDetailsPage.getUserNickNameFromProfileDropMenu(), userNickName   );
        Assert.assertEquals(accountDetailsPage.getUserCountry(),  props.getProperty("UserCountry")  );
        Assert.assertEquals(accountDetailsPage.getUserTimeZone(), props.getProperty("UserTimeZone") );

        WriterProfilePage writerProfilePage = accountDetailsPage.selectWriterProfileFromMenu();
        Assert.assertEquals(writerProfilePage.userName.getText().trim(), userNickName);
        Assert.assertTrue(writerProfilePage.takeTheTestNowButtonInHeader.isDisplayed());

        switchUser(driver, clientLogin);

        PartnersPage partnersPage = myOrdersPage.clickOnWritersFromTopMenu();
        partnersPage.search(userNickName).click();

        Assert.assertEquals(writerProfilePage.getUserName(), userNickName);
        Assert.assertEquals(writerProfilePage.getLanguageDefText(),   props.getProperty("User does not know any language")               );
        Assert.assertEquals(writerProfilePage.getExpertisesDefText(), props.getProperty("User does not have any expertises")             );
        Assert.assertEquals(writerProfilePage.getCategoriesDefText(), props.getProperty("User does not selected any writing categories") );
        Assert.assertEquals(writerProfilePage.getWriterDoesNotHavePortfolioText(), userNickName + "User does not have portfolio yet"     );

        logOut(driver);
    }

}
