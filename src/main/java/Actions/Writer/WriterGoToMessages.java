package Actions.Writer;

import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.MyMessagesPage;
import PageObjects.General.OrderInfoAndActions;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static GeneralHelpers.GeneralHelpers.entityAppear;
import static GeneralHelpers.GeneralHelpers.getFileName;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 09.09.2015.
 */
public class WriterGoToMessages {


    public static OrderInfoAndActions sendMessageToClient(WebDriver driver, LoginObject clientLogin, OrderObject order,
                                                          LoginObject writerLogin, String textMessage) throws InterruptedException {

        OrderInfoAndActions orderInfoWriter = WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, order, writerLogin);
        orderInfoWriter.clickOnTheDropTheCustomerMessageButton(driver);

        MyMessagesPage message = new MyMessagesPage(driver);
        message.sendTextMessage(textMessage);

        return orderInfoWriter;
    }


    public static String CreateOrderAddBidSendMessageWithFileToClient(WebDriver driver, LoginObject clientLogin, OrderObject order,
                                                                      LoginObject writerLogin, String path) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        OrderInfoAndActions orderInfoWriter = WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, order, writerLogin);
        orderInfoWriter.clickOnTheDropTheCustomerMessageButton(driver);
        MyMessagesPage message = new MyMessagesPage(driver);

        String filename = getFileName(path);
        message.inputFileToTheMessage(path);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fileDownloadIcon")));
        String href = message.getFileHref(filename);


        return href;
    }


    public static Boolean waitForFileAppearInDialogBox(String path) {

        String file = getFileName(path);
        entityAppear(file);
        $(By.className("fileDownloadIcon")).should(Condition.appear);

        return true;
    }

}
