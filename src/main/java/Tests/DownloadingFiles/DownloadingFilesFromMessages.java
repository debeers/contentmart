package Tests.DownloadingFiles;

import Actions.Writer.WriterGoToAllOrders;
import DataProviders.ActionsWithOrdersDataProvider;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.General.MyMessagesPage;
import Tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 02.09.2015.
 */
public class DownloadingFilesFromMessages extends BaseTest{




    @Test(groups={"regress 1.0"}, dataProvider= "ActionsWithOrderData", dataProviderClass = ActionsWithOrdersDataProvider.class)
    public static void DownloadFilesToMessage(Object clientLoginObject, Object orderObject, Object writerLoginObj) throws Exception {


        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;



        Order order = new Order();

        String path = System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\DMX.jpg";

        WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, orderObj, writerLogin, order);
        MyMessagesPage message = new MyMessagesPage(driver);

//                message.clickOnTheDropTheCustomerMessageButtonFromBidOnOrder(driver);
        $(message.attachFileInput).shouldBe(visible).sendKeys(path);
        $(message.sendMessageButton).shouldBe(visible).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'DMX')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fileDownloadIcon"))).click();


        Robot robot = new Robot();
        Thread.sleep(1000L);
        //Click Down Arrow Key to select "Save File" Radio Button
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000L);
        // Click 3 times Tab to take focus on "OK" Button
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(1000L);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(1000L);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(1000L);
        //Click "Enter" Button to download file
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(5000L);
        System.out.println("Robot work Complete");


    }
}
