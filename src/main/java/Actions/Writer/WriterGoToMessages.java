package Actions.Writer;

import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import GeneralHelpers.GeneralHelpers;
import PageObjects.General.MyMessagesPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by CMG_TEST on 09.09.2015.
 */
public class WriterGoToMessages {


    public static OrderInfoAndActions sendMessageToClient(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin,
                                           Order order, String textMessage) throws InterruptedException {

        OrderInfoAndActions orderInfoWriter = WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, orderObject, writerLogin, order);
        orderInfoWriter.clickOnTheDropTheCustomerMessageButton(driver);

        MyMessagesPage message = new MyMessagesPage(driver);
        message.sendTextMessage(textMessage);

        return orderInfoWriter;

    }



    public static OrderInfoAndActions sendMessageWithFileToClient(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin,
                                                          Order order,  String path) throws InterruptedException {

        OrderInfoAndActions orderInfoWriter = WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, orderObject, writerLogin, order);
        orderInfoWriter.clickOnTheDropTheCustomerMessageButton(driver);

        MyMessagesPage message = new MyMessagesPage(driver);
        message.inputFileToTheMessage(path);

        return orderInfoWriter;

    }


    public static Boolean waitForFileAppearInDialogBox(WebDriver driver, String path) {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        String file = GeneralHelpers.getFileName(path);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+file+"')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fileDownloadIcon")));
        return true;
    }



}
