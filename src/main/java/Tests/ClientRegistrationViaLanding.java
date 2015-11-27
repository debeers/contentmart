package Tests;

import GeneralHelpers.GmailListener;
import PageObjects.Client.ClientProfilePage;
import PageObjects.Client.NewOrderPage;
import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.PartnersPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.Message;
import java.util.Properties;

import static Actions.General.RegistrationAndLogin.*;
import static GeneralHelpers.DBWorker.checkForExitingUser;
import static GeneralHelpers.DBWorker.setUserNickName;
import static GeneralHelpers.GmailListener.getActivationLinkFromTargetMessage;
import static GeneralHelpers.PropertiesLoader.propertyXMLoader;
import static SQLRepo.General.checkUserExsistanceByMail;
import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 27.11.2015.
 */
public class ClientRegistrationViaLanding extends BaseTest{

    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public void ClientRegistrationViaLanding() throws Exception {

        Properties props = propertyXMLoader(System.getProperty("user.dir") +
                "\\src\\main\\java\\tests\\TestDataXML\\Registration\\ClientRegistrationViaLanding.xml");

        Boolean isSeen = false;
        String phoneBlockNumber = "1-800-313-0717";
        String userNickName = setUserNickName(props.getProperty("userRole"));

        checkForExitingUser(
                checkUserExsistanceByMail(
                        props.getProperty("userEmail")), "email", props.getProperty("userEmail")
        );

        String title = registerFromLandingAsClient(
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

        Assert.assertEquals(myOrdersPage.getMyOrdersH1(),                      props.getProperty("MyOrdersTitle")    );
        Assert.assertEquals(myOrdersPage.getChooseAwriterElement(),            props.getProperty("Choose a writer")  );
        Assert.assertEquals(myOrdersPage.getPostAnOrderElementText(),          props.getProperty("Post an order")    );
        Assert.assertEquals(myOrdersPage.getReviewContentElementElement(),     props.getProperty("Review Content")   );
        Assert.assertEquals(myOrdersPage.getProjectCompleteElementElement(),   props.getProperty("Project complete") );
        Assert.assertEquals(myOrdersPage.getUserNickNameFromProfileDropMenu(), userNickName                          );

        NewOrderPage newOrderPage = myOrdersPage.clickOnNewOrderButton();

        Assert.assertTrue(newOrderPage.orderNameField.isDisplayed()    );
        Assert.assertTrue(newOrderPage.priceInUSDField.isDisplayed()   );
        Assert.assertTrue(newOrderPage.publishButton.isDisplayed()     );
        Assert.assertEquals(newOrderPage.getOrderTotalPriceValue(), "0");

        AccountDetailsPage accountDetailsPage = newOrderPage.selectAccountSettingsFromMenu();

        Assert.assertEquals(accountDetailsPage.getUserNickNameFromProfileDropMenu(), userNickName   );
        Assert.assertEquals(accountDetailsPage.getUserCountry(),  props.getProperty("UserCountry")  );
        Assert.assertEquals(accountDetailsPage.getUserState(),    props.getProperty("UserState")    );
        Assert.assertEquals(accountDetailsPage.getUserCity(),     props.getProperty("UserCity")     );
        Assert.assertEquals(accountDetailsPage.getUserTimeZone(), props.getProperty("UserTimeZone") );

        Assert.assertEquals(accountDetailsPage.selectEditClientProfileFromMenu().getUserName(), userNickName);

        MyOrdersPage writerOrdersPage = switchUser(driver, writerLogin);

        PartnersPage partnersPage = writerOrdersPage.clickOnClientsFromTopMenu();
        partnersPage.search(userNickName).click();

        ClientProfilePage clientProfilePage = new ClientProfilePage(driver);
        Assert.assertEquals(clientProfilePage.getClientName(), userNickName);

        if(isEvenID(props.getProperty("userEmail"))){

            Assert.assertEquals(clientProfilePage.getNumberFromThePhoneBlock(), phoneBlockNumber);
        }
        logOut(driver);
    }
}
