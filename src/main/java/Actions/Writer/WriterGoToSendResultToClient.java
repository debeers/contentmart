package Actions.Writer;

import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.GeneralHelpers;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

/**
 * Created by DeBeers on 18.09.2015.
 */
public class WriterGoToSendResultToClient {


    public static OrderInfoAndActions andSendResultToTheClient(WebDriver driver, LoginObject clientLogin, OrderObject orderObject,
                                                               LoginObject writerLogin, String text, String filepath) throws InterruptedException {

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, orderObject, writerLogin);
        orderInfoWriter.sendTextToTheClientTextArea(driver, text);
        orderInfoWriter.clickOnTheSendCompletedOrderButton(driver);
        GeneralHelpers.uploadFileToHidenInput(driver, filepath);

        return orderInfoWriter;
    }


    public static OrderInfoAndActions uploadFilesAndSendResultToTheClient(WebDriver driver, LoginObject clientLogin, OrderObject orderObject,
                                                                          LoginObject writerLogin, String text, String filepath) throws InterruptedException {

        OrderInfoAndActions orderInfoAndActions = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, orderObject, writerLogin);
        orderInfoAndActions.sendTextToTheClientTextArea(driver, text);
        GeneralHelpers.uploadFileToHidenInput(driver, filepath);
        ClientNewOrderPage clientNewOrderPage = new ClientNewOrderPage(driver);
        clientNewOrderPage.waitForProgressBarWhenUploadingFilesToNewOrder();

        return orderInfoAndActions;
    }

}
