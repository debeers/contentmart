package Tests;

import Entities.UserEmailAccount;
import Utilities.EmailListener;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.PartnersPage;
import PageObjects.Writer.WriterProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import javax.mail.Message;
import java.util.Properties;
import static Actions.General.RegistrationAndLogin.*;
import static Utilities.CreateEmailAccountUtill.createNewUserEmail;
import static Utilities.Randomizers.setRandomUserNickName;
import static Utilities.EmailWorker.getActivationLinkFromRegistrationLetter;
import static Utilities.PropertiesLoader.*;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class WriterRegistrationViaLandingTest extends BaseTest{

    @Test
    public void WriterRegistrationViaLanding_Test() throws Exception {

        Properties props = propertyXMLoader(System.getProperty("user.dir") +
                "\\src\\main\\java\\tests\\TestDataXML\\Registration\\WriterRegistrationViaLandingTest.xml");

        UserEmailAccount userEmailAccount = createNewUserEmail(props.getProperty("userRole"));
        String email = userEmailAccount.getEmail();
        String userNickName  = setRandomUserNickName(props.getProperty("userRole"));

        String title = registerFromLandingAsWriterAndGetPageTitle(
                driver,
                props.getProperty("URL"),
                userNickName,
                email,
                props.getProperty("userPassword")
        );

        assertEquals(title, props.getProperty("registrationTitle"));

        Message targetMessage = new EmailListener()
                .getTargetEmail(
                        props.getProperty("subject"),
                        props.getProperty("mail"),
                        userEmailAccount
                );

        MyOrdersPage myOrdersPage = activateUserAccount(
                driver, getActivationLinkFromRegistrationLetter(targetMessage),
                props.getProperty("MyOrdersTitle"));

        assertTrue($(myOrdersPage.takeTheTestNowButton).isDisplayed());
        assertEquals(driver.getTitle(), "My Orders | ContentMart"                           );
        assertEquals(myOrdersPage.getUserNickNameFromProfileDropMenu().trim(), userNickName);

        AccountDetailsPage accountDetailsPage = myOrdersPage.selectAccountSettingsFromMenu();

        Assert.assertEquals(accountDetailsPage.getUserCountry(), props.getProperty("UserCountry")        );
        Assert.assertEquals(accountDetailsPage.getUserTimeZoneValue(), props.getProperty("UserTimeZone") );

        WriterProfilePage writerProfilePage = myOrdersPage.selectWriterProfileFromMenu();

        Assert.assertEquals(writerProfilePage.userName.getText().trim(), userNickName );
        Assert.assertTrue(writerProfilePage.takeTheTestNowButtonInHeader.isDisplayed());

        PartnersPage partnersPage =
                switchUser(driver, clientLogin)
                        .clickOnWritersFromTopMenu();

        partnersPage.analogSearch(userNickName).click();

        Assert.assertEquals(writerProfilePage.getUserName(), userNickName                                                                );
        Assert.assertEquals(writerProfilePage.getLanguageDefText(), props.getProperty("User does not know any language")                 );
        Assert.assertEquals(writerProfilePage.getExpertisesDefText(), props.getProperty("User does not have any expertises")             );
        Assert.assertEquals(writerProfilePage.getCategoriesDefText(), props.getProperty("User does not selected any writing categories") );
        Assert.assertEquals(writerProfilePage.getWriterDoesNotHavePortfolioText(), userNickName + " does not have portfolio yet :("      );

        logOut(driver);
    }
}
