package Actions.Client;

import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.GeneralHelpers;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.LeftMenuGeneralPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Actions.General.GoToBalanceGeneralActions.getCurrentBallanceFromMenuButton;
import static Actions.General.RegistrationAndLogin.loginAs;
import static GeneralHelpers.GeneralHelpers.getFileName;
import static GeneralHelpers.CustomWaits.createNewOrderWaits;
import static GeneralHelpers.GeneralHelpers.uploadFileToHidenInput;
import static Tests.BaseTest.driver;

/**
 * Created by CMG_TEST on 17.09.2015.
 */
public class ClientGoToCreateNewOrder {


    public static OrderObject setOrderObject(OrderObject order) {

        OrderInfoAndActions orderInfoPage = new OrderInfoAndActions(driver);

        order.setEntityOrderName(orderInfoPage.getorderName());
        order.setEntityOrderDeadLine(orderInfoPage.getorderDeadline());
        order.setEntityOrderDescription(orderInfoPage.getorderDescription());
        order.setEntityOrderPublicDate(orderInfoPage.getorderPublicationDate());
        order.setEntityOrderStatus(orderInfoPage.getorderStatus());
        order.setEntityOrderSystemID(orderInfoPage.getsystemOrderID());
        order.setEntityOrderVisibility(orderInfoPage.gettypeOfSharing());
        order.setWordsReq(orderInfoPage.getWordsRequire());

        System.out.println("\n" +

                "Order name: " + orderInfoPage.getorderName() + "\n" +
                "Order System ID: " + orderInfoPage.getsystemOrderID() + "\n" +
                "Order description: " + orderInfoPage.getorderDescription() + "\n" +
                "Public date: " + orderInfoPage.getorderPublicationDate() + "\n" +
                "Deadline: " + orderInfoPage.getorderDeadline() + "\n" +
                "Words Require: " + orderInfoPage.getWordsRequire() + "\n" +
                "Visible for: " + orderInfoPage.gettypeOfSharing() + "\n" +
                "Status: " + orderInfoPage.getorderStatus() + "\n");

        return order;
    }

    public static OrderInfoAndActions andPublish(WebDriver driver, LoginObject clientLogin,
                                                 OrderObject order) throws InterruptedException {

        ClientNewOrderPage newOrder = andCreateTheNewOrder(driver, clientLogin, order);
        OrderInfoAndActions orderInfoAndActions = newOrder.andClickOnPublishNewOrderButton(driver);
        setOrderObject(order);

        return orderInfoAndActions;
    }


    public static OrderInfoAndActions andSaveAsDraft(WebDriver driver, LoginObject clientLogin,
                                                     OrderObject order) throws InterruptedException {

        ClientNewOrderPage newOrder = andCreateTheNewOrder(driver, clientLogin, order);
        OrderInfoAndActions orderInfoAndActions = newOrder.andClickOnSaveAsDraftButton(driver);

        return orderInfoAndActions;
    }


    public static OrderInfoAndActions andUploadFilesToIt(WebDriver driver, LoginObject clientLogin,
                                                         OrderObject order, String filepath) throws InterruptedException {

        ClientNewOrderPage newOrder = andCreateTheNewOrder(driver, clientLogin, order);
        uploadFileToHidenInput(driver, filepath);
        newOrder.waitForProgressBarWhenUploadingFilesToNewOrder();

        OrderInfoAndActions orderInfoAndActions = newOrder.andClickOnPublishNewOrderButton(driver);

        return orderInfoAndActions;
    }


    public static Boolean checkForFileUploadInNewOrder(String filePath) {
        String fileName = getFileName(filePath);
        ClientNewOrderPage newOrderPage = new ClientNewOrderPage(driver);

        List<WebElement> attachedFiles = newOrderPage.attachedFiles;

        for (WebElement el : attachedFiles) {

            if (el.getText().contains(fileName)) {

                System.out.println("File successfully found! " + el);
                return true;

            } else {

                System.out.println("File not found!!!");
                return false;
            }
        }
        return true;
    }


    public static Boolean checkForFileExsistInNewOrder() {

        ClientNewOrderPage newOrderPage = new ClientNewOrderPage(driver);
        List<WebElement> attachedFiles = newOrderPage.attachedFiles;

        for (WebElement fileItem : attachedFiles) {

            if (GeneralHelpers.isFileExists(fileItem.getAttribute("href"))) {
                System.out.println("File successfully found! " + fileItem.getText());
                return true;
            } else {
                System.out.println("File not found!!!");
                return false;
            }
        }

        return true;
    }


    public static ClientNewOrderPage andCreateTheNewOrder(WebDriver driver, LoginObject clientLogin,
                                                          OrderObject order) throws InterruptedException {

        loginAs(driver, clientLogin);
        LeftMenuGeneralPage leftMenuGeneralPage = new LeftMenuGeneralPage(driver);

        ClientNewOrderPage clientNewOrderPage = leftMenuGeneralPage.clickOnNewOrderFromLeftMenu();
        createNewOrderWaits(clientNewOrderPage);

        clientNewOrderPage.setOrder(driver, clientNewOrderPage, order);
        order.setEntityOrderValue(order.getEntityOrderValue());
        order.setTotalBalanceBefore(getCurrentBallanceFromMenuButton(driver));

        System.out.println("Order value: " + order.getEntityOrderValue());
        System.out.println("Total balance before: " + order.getTotalBalanceBefore());

        return clientNewOrderPage;
    }


    public static ClientNewOrderPage publishAndGoToEditOrder(WebDriver driver, LoginObject clientLogin,
                                                             OrderObject order) throws InterruptedException {

        OrderInfoAndActions fillTheOrderFields = ClientGoToCreateNewOrder.andPublish(driver, clientLogin, order);
        ClientNewOrderPage clientNewOrderPage = fillTheOrderFields.andClickOnEditOrderButton();

        return clientNewOrderPage;
    }

}
