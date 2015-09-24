package Actions.Client;


import Entities.LoginObject;
import Entities.Order;
import GeneralHelpers.Messages;
import PageObjects.General.MyMessagesPage;
import PageObjects.General.MyOrdersPage;
import org.openqa.selenium.WebDriver;

import static Actions.RegistrationAndLogin.logOut;
import static Actions.RegistrationAndLogin.loginAs;
import static GeneralHelpers.GeneralHelpers.findCreatedClientOrderAndClickOnIt;

/**
 * Created by ilya on 01.09.2015.
 */
public class ClientGoToMessages {


    public static String checkDeliveryMessageFromWriter(WebDriver driver, LoginObject clientLogin,
                                                        Order order, String textMessage) {
        if(driver.getTitle() != "ContentMart") {
            logOut(driver);
        }

        MyMessagesPage clientMessages = ClientGoToMessages.goToClientMessages(driver, clientLogin);

        findCreatedClientOrderAndClickOnIt(driver, order);
        String msg = Messages.findMessage(driver, textMessage);
        clientMessages.closeMessageWindowClick();
        return msg;
    }


    public static MyMessagesPage goToClientMessages(WebDriver driver, LoginObject clientLogin) {

        MyOrdersPage myOrdersPage = loginAs(driver, clientLogin);
        myOrdersPage.clickOnProfileLeftMenu();
        MyMessagesPage myMessages = myOrdersPage.clickOnMyMessagesLeftMenu(driver);

        return myMessages;

    }

}
