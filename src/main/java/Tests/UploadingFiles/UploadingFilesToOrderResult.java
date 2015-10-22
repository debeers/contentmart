package Tests.UploadingFiles;

import Actions.Writer.WriterGoToSendResultToClient;
import Entities.OrderObject;
import GeneralHelpers.GeneralHelpers;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

/**
 * Created by CMG_TEST on 23.09.2015.
 */
public class UploadingFilesToOrderResult extends BaseTest{

    @Test(groups={"regress 1.0"})
    public static void UploadingFilesToOrderResult() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!))) Skype, icq, telephone number";
        String filepath = System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\BigByte.doc";

        OrderInfoAndActions orderInfoAndActions = WriterGoToSendResultToClient.uploadFilesAndSendResultToTheClient(driver, clientLogin, order, writerLogin, writerText, filepath);
        String filename = GeneralHelpers.getFileName(filepath);

        assertTrue($(orderInfoAndActions.waitForUploadingFilesToOrder(filename)).isDisplayed());
        orderInfoAndActions.clickOnTheSendCompletedOrderButton(driver);
    }
}
