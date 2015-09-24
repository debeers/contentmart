package Actions.Writer;

import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import GeneralHelpers.GeneralHelpers;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

/**
 * Created by DeBeers on 18.09.2015.
 */
public class WriterGoToOrderWorkflow {



    public static OrderInfoAndActions andSendResultToTheClient(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin, Order order, String text) throws InterruptedException {

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, orderObject, writerLogin, order);
        orderInfoWriter.sendTextToTheClientTextArea(driver, text);
        orderInfoWriter.clickOnTheSendCompletedOrderButton(driver);

        return orderInfoWriter;
    }


    public static OrderInfoAndActions uploadFilesAndSendResultToTheClient(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin, Order order, String text, String filepath) throws InterruptedException {

        OrderInfoAndActions orderInfoAndActions = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, orderObject, writerLogin, order);
        orderInfoAndActions.sendTextToTheClientTextArea(driver, text);
        GeneralHelpers.uploadFileToOrder(driver, filepath);
        ClientNewOrderPage clientNewOrderPage = new ClientNewOrderPage(driver);
        clientNewOrderPage.waitForProgressBarWhenUploadingFilesToNewOrder();

        return orderInfoAndActions;
    }



}
