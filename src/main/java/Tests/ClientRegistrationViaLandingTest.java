package Tests;

import Entities.UserEmailAccount;
import Utilities.EmailListener;
import PageObjects.Client.ClientProfilePage;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.PartnersPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.Message;
import java.util.Properties;

import static Actions.General.RegistrationAndLogin.*;
import static Utilities.CreateEmailAccountUtill.createNewUserEmail;
import static Utilities.EmailWorker.getActivationLinkFromRegistrationLetter;
import static Utilities.PropertiesLoader.propertyXMLoader;
import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 27.11.2015.
 */
public class ClientRegistrationViaLandingTest extends BaseTest{

    @Test
    public void ClientRegistrationViaLanding_Test() throws Exception {

        Properties props  = propertyXMLoader(System.getProperty("user.dir") +
                "\\src\\main\\java\\tests\\TestDataXML\\Registration\\ClientRegistrationViaLandingTest.xml");

        UserEmailAccount userEmailAccount = createNewUserEmail(props.getProperty("userRole"));
        String userNickName  = userEmailAccount.getName();
        String email = userEmailAccount.getEmail();

        String title = registerFromLandingAsClientAndGetPageTitle(
                driver,
                props.getProperty("URL"),
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
                driver, getActivationLinkFromRegistrationLetter(targetMessage),
                props.getProperty("MyOrdersTitle"));

        Assert.assertEquals(myOrdersPage.getPostAnOrderElementText(), props.getProperty("Post an order"));
        Assert.assertEquals(myOrdersPage.getUserNickNameFromProfileDropMenu(), userNickName                     );

        AccountDetailsPage accountDetailsPage = myOrdersPage.selectAccountSettingsFromMenu();

        Assert.assertEquals(accountDetailsPage.getUserNickNameFromProfileDropMenu(), userNickName               );
        Assert.assertEquals(accountDetailsPage.getUserCountry(), props.getProperty("UserCountry")               );
        Assert.assertEquals(accountDetailsPage.getUserRegion(), props.getProperty("UserState")                  );
        Assert.assertEquals(accountDetailsPage.getUserCity(), props.getProperty("UserCity")                     );
        Assert.assertEquals(accountDetailsPage.getUserTimeZoneValue(), props.getProperty("UserTimeZone")        );
        Assert.assertEquals(accountDetailsPage.selectEditClientProfileFromMenu().getUserName(), userNickName    );

        MyOrdersPage writerOrdersPage = switchUser(driver, writerLogin);

        PartnersPage partnersPage = writerOrdersPage.clickOnClientsFromTopMenu();
        partnersPage.search(userNickName).click();

        ClientProfilePage clientProfilePage = new ClientProfilePage(driver);
        Assert.assertEquals(clientProfilePage.getClientName(), userNickName);

        logOut(driver);
    }
}
