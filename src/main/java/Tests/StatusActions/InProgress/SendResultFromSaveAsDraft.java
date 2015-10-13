package Tests.StatusActions.InProgress;

import Actions.Writer.WriterGoToStartToWorking;
import Entities.OrderObject;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static GeneralHelpers.GeneralHelpers.uploadFileToHidenInput;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by CMG_TEST on 07.10.2015.
 */
public class SendResultFromSaveAsDraft extends BaseTest {

    @Test(groups={"regress 1.0"})
    public static void SaveAsDraft() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";
        String successMessageAfterResultSent = "Your result has been delivered to the order owner successfully! You will be notified as and when your result is accepted/rejected.";


        String filepath = System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\DMX.jpg";

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, order, writerLogin);
        assertEquals(orderInfoWriter.getTextFromOrderStatus(), "In Progress");

        orderInfoWriter.sendTextToTheClientTextArea(driver, writerText);
        orderInfoWriter.clickOnSaveAsDraftButton();
        assertTrue(orderInfoWriter.waitForPlagiatorCheckAppear());

        assertEquals(orderInfoWriter.getTextFromSavedAsDraftTextArea(), writerText);
        assertEquals(orderInfoWriter.getTextFromOrderStatus(), "In Progress");

        orderInfoWriter.sendTextToTheClientTextArea(driver, writerText);
        uploadFileToHidenInput(driver, filepath);
        ClientNewOrderPage clientNewOrderPage = new ClientNewOrderPage(driver);
        clientNewOrderPage.waitForProgressBarWhenUploadingFilesToNewOrder();
        orderInfoWriter.clickOnTheSendCompletedOrderButton(driver);

        assertEquals(orderInfoWriter.waitForSuccessMessageAfterSendResult(), successMessageAfterResultSent);
        assertEquals(orderInfoWriter.getSendedWriterText(), writerText+writerText);
        assertEquals(orderInfoWriter.getTextFromOrderStatus(), "Result sent");
    }
}
