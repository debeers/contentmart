package Tests;

import Entities.Mail;
import GeneralHelpers.DBUtill;
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
import static GeneralHelpers.DBWorker.checkForExitingUserAndDeleteIt;
import static GeneralHelpers.GeneralHelpers.setRandomUserNickName;
import static GeneralHelpers.GmailListener.getActivationLinkFromTargetMessage;
import static GeneralHelpers.MailCreator.createNewUserMail;
import static GeneralHelpers.PropertiesLoader.propertyXMLoader;
import static SQLRepo.General.checkUserExsistanceByMail;
import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 30.11.2015.
 */
public class ClientRegistrationViaMainPage extends BaseTest{

    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public void ClientRegistrationViaMainPage() throws Exception {

        Properties props = propertyXMLoader(System.getProperty("user.dir") +
                "\\src\\main\\java\\tests\\TestDataXML\\Registration\\ClientRegistrationViaMainPage.xml");

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

        String title = registerAsClientFromMainPage(
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
        System.out.println(activLink);
        driver.get(activLink);

        MyOrdersPage myOrdersPage = new MyOrdersPage(driver);

        try{
            myOrdersPage.newOrderButton.isDisplayed();

        }catch (Exception e){
            System.out.println("For some reasons driver can`t open activation link, we gonna try it one more time after timeout");
            Thread.sleep(5000);
            driver.get(activLink);
        }

        Assert.assertEquals(myOrdersPage.getMyOrdersH1(), props.getProperty("MyOrdersTitle"));
        Assert.assertEquals(myOrdersPage.getChooseAwriterElement(),            props.getProperty("Choose a writer")  );
        Assert.assertEquals(myOrdersPage.getPostAnOrderElementText(), props.getProperty("Post an order"));
        Assert.assertEquals(myOrdersPage.getReviewContentElementElement(), props.getProperty("Review Content"));
        Assert.assertEquals(myOrdersPage.getProjectCompleteElementElement(), props.getProperty("Project complete"));
        Assert.assertEquals(myOrdersPage.getUserNickNameFromProfileDropMenu(), userNickName);

        NewOrderPage newOrderPage = myOrdersPage.clickOnNewOrderButton();

        Assert.assertTrue(newOrderPage.orderNameField.isDisplayed());
        Assert.assertTrue(newOrderPage.priceInUSDField.isDisplayed());
        Assert.assertTrue(newOrderPage.publishButton.isDisplayed());
        Assert.assertEquals(newOrderPage.getOrderTotalPriceValue(), "0");

        AccountDetailsPage accountDetailsPage = newOrderPage.selectAccountSettingsFromMenu();

        Assert.assertEquals(accountDetailsPage.getUserNickNameFromProfileDropMenu(), userNickName   );
        Assert.assertEquals(accountDetailsPage.getUserCountry(), props.getProperty("UserCountry"));
        Assert.assertEquals(accountDetailsPage.getUserState(), props.getProperty("UserState"));
        Assert.assertEquals(accountDetailsPage.getUserCity(), props.getProperty("UserCity"));
        Assert.assertEquals(accountDetailsPage.getUserTimeZone(), props.getProperty("UserTimeZone"));

        Assert.assertEquals(accountDetailsPage.selectEditClientProfileFromMenu().getUserName(), userNickName);

        MyOrdersPage writerOrdersPage = switchUser(driver, writerLogin);

        PartnersPage partnersPage = writerOrdersPage.clickOnClientsFromTopMenu();
        partnersPage.search(userNickName).click();

        ClientProfilePage clientProfilePage = new ClientProfilePage(driver);
        Assert.assertEquals(clientProfilePage.getClientName(), userNickName);

        logOut(driver);
    }

}
