package Tests;

import Entities.UserEmailAccount;
import GeneralHelpers.DBUtill;
import GeneralHelpers.EmailListener;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.PartnersPage;
import PageObjects.Writer.WriterProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import javax.mail.Message;
import java.util.Properties;
import static Actions.General.RegistrationAndLogin.*;
import static GeneralHelpers.CreateEmailAccountUtill.createNewUserEmail;
import static GeneralHelpers.DBWorker.checkForExitingUserAndDeleteIt;
import static GeneralHelpers.GeneralHelpers.setRandomUserNickName;
import static GeneralHelpers.EmailListener.getActivationLinkFromTargetMessage;
import static GeneralHelpers.PropertiesLoader.propertyXMLoader;
import static SQLRepo.General.checkUserExsistanceByMail;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by DeBeers on 30.11.2015.
 */
public class WriterRegistrationViaMainPage extends BaseTest{

    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public void WriterRegistrationViaMainPage() throws Exception {

        Properties props = propertyXMLoader(System.getProperty("user.dir") +
                "\\src\\main\\java\\tests\\TestDataXML\\Registration\\WriterRegistrationViaMainPage.xml");

        UserEmailAccount userEmailAccount = createNewUserEmail(props.getProperty("userRole"));
        String email = userEmailAccount.getEmail();
        String userNickName  = setRandomUserNickName(props.getProperty("userRole"));

        String title = registerAsWriterFromMainPage(
                driver,
                userNickName,
                email,
                props.getProperty("userPassword")
        );

        assertEquals(title, props.getProperty("registrationTitle"));

        Message targetMessage = new EmailListener().getTargetEmail(
                props.getProperty("subject"),
                props.getProperty("mail"),
                userEmailAccount
        );

        MyOrdersPage myOrdersPage = activateUserAccount(
                driver, getActivationLinkFromTargetMessage(targetMessage),
                props.getProperty("MyOrdersTitle"));

        assertTrue($(myOrdersPage.takeTheTestNowButton).isDisplayed()                       );
        assertEquals(myOrdersPage.getUserNickNameFromProfileDropMenu().trim(), userNickName);
        new DBUtill().initDB().getColumn(checkUserExsistanceByMail(email), "*");
        AccountDetailsPage accountDetailsPage = myOrdersPage.selectAccountSettingsFromMenu();

        Assert.assertEquals(accountDetailsPage.getUserCountry(), props.getProperty("UserCountry")        );
        Assert.assertEquals(accountDetailsPage.getUserTimeZoneValue(), props.getProperty("UserTimeZone"));

        WriterProfilePage writerProfilePage = myOrdersPage.selectWriterProfileFromMenu();

        Assert.assertEquals(writerProfilePage.userName.getText().trim(), userNickName );
        Assert.assertTrue(writerProfilePage.takeTheTestNowButtonInHeader.isDisplayed());

        PartnersPage partnersPage =
                switchUser(driver, clientLogin)
                        .clickOnWritersFromTopMenu();

        partnersPage.analogSearch(userNickName).click();

        Assert.assertEquals(writerProfilePage.getUserName(), userNickName);
        Assert.assertEquals(writerProfilePage.getWriterDoesNotHavePortfolioText(), userNickName + " does not have portfolio yet :(");

        logOut(driver);
    }
}
