package Actions.Client;

import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import GeneralHelpers.GeneralHelpers;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.LeftMenuGeneralPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Actions.RegistrationAndLogin.loginAs;
import static GeneralHelpers.GeneralHelpers.getFileName;
import static GeneralHelpers.CustomWaits.createNewOrderWaits;
import static GeneralHelpers.GeneralHelpers.uploadFileToHidenInput;
import static Tests.BaseTest.driver;

/**
 * Created by CMG_TEST on 17.09.2015.
 */
public class ClientGoToCreateNewOrder {


    public static Order setOrderObject(Order order){

        OrderInfoAndActions orderInfoPage = new OrderInfoAndActions(driver);

        order.setEntityOrderName(orderInfoPage.getorderName());
        order.setEntityOrderDeadLine(orderInfoPage.getorderDeadline());
        order.setEntityOrderDescription(orderInfoPage.getorderDescription());
        order.setEntityOrderPublicDate(orderInfoPage.getorderPublicationDate());
        order.setEntityOrderStatus(orderInfoPage.getorderStatus());
        order.setEntityOrderSystemID(orderInfoPage.getsystemOrderID());
        order.setEntityOrderVisibility(orderInfoPage.gettypeOfSharing());




        System.out.println("\n" +
                "Balance name: " + orderInfoPage.getorderName() + "\n" +
                "Balance System ID: " + orderInfoPage.getsystemOrderID() + "\n" +
                "Balance description: " + orderInfoPage.getorderDescription() + "\n" +
                "Public date: " + orderInfoPage.getorderPublicationDate() + "\n" +
                "Deadline: " + orderInfoPage.getorderDeadline() + "\n" +
                "Visible for: " + orderInfoPage.gettypeOfSharing() + "\n" +
                "Status: " + orderInfoPage.getorderStatus() + "\n");

        return order;
    }

    public static OrderInfoAndActions andPublish(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, Order order) throws InterruptedException {

        ClientNewOrderPage newOrder = andCreateTheNewOrder(driver, clientLogin, orderObject, order);
        OrderInfoAndActions orderInfoAndActions = newOrder.andClickOnPublishNewOrderButton(driver);
        setOrderObject(order);

        return orderInfoAndActions;
    }

    public static OrderInfoAndActions andSaveAsDraft(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, Order order) throws InterruptedException {

        ClientNewOrderPage newOrder = andCreateTheNewOrder(driver, clientLogin, orderObject, order);
        OrderInfoAndActions orderInfoAndActions = newOrder.andClickOnSaveAsDraftButton(driver);

        return orderInfoAndActions;
    }


    public static OrderInfoAndActions andUploadFilesToIt(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, Order order, String filepath) throws InterruptedException {

        ClientNewOrderPage newOrder = andCreateTheNewOrder(driver, clientLogin, orderObject, order);
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

        for (WebElement el : attachedFiles) {

            if (GeneralHelpers.isFileExists(el.getAttribute("href"))) {
                System.out.println("File successfully found! " + el.getText());
                return true;
            } else {
                System.out.println("File not found!!!");
                return false;
            }

        }

        return true;

    }



    public static ClientNewOrderPage andCreateTheNewOrder(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, Order order) throws InterruptedException {

        loginAs(driver, clientLogin);
        LeftMenuGeneralPage leftMenuGeneralPage = new LeftMenuGeneralPage(driver);

        ClientNewOrderPage clientNewOrderPage = leftMenuGeneralPage.clickOnNewOrderFromLeftMenu();
        createNewOrderWaits(clientNewOrderPage);

        clientNewOrderPage.setOrder(driver, clientNewOrderPage, orderObject, order);
        System.out.println(order.getEntityOrderValue());


        return clientNewOrderPage;
    }



    public static ClientNewOrderPage publishAndGoToEditOrder(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, Order order ) throws InterruptedException {


        OrderInfoAndActions fillTheOrderFields = ClientGoToCreateNewOrder.andPublish(driver, clientLogin, orderObject, order);
        ClientNewOrderPage clientNewOrderPage = fillTheOrderFields.andClickOnEditOrderButton();

        return clientNewOrderPage;
    }







}
