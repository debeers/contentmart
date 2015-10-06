package Actions.Client;


import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.Search;
import PageObjects.General.MyMessagesPage;
import PageObjects.General.MyOrdersPage;
import org.openqa.selenium.WebDriver;

import static Actions.General.RegistrationAndLogin.logOut;
import static Actions.General.RegistrationAndLogin.loginAs;
import static GeneralHelpers.GeneralHelpers.findCreatedOrderAndClickOnIt;

/**
 * Created by ilya on 01.09.2015.
 */
public class ClientGoToMessages {


    public static String checkDeliveryMessageFromWriter(WebDriver driver, LoginObject clientLogin,
                                                        OrderObject order, String textMessage) {
        logOut(driver);
        MyMessagesPage clientMessages = ClientGoToMessages.goToClientMessages(driver, clientLogin);

        findCreatedOrderAndClickOnIt(driver, order);
        String msg = Search.findMessage(driver, textMessage);
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
