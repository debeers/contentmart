package Tests.StatusActions.InProgress;

import Actions.Writer.WriterGoToStartToWorking;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static GeneralHelpers.CustomAsserts.orderParametersCheck;
import static GeneralHelpers.GeneralHelpers.uploadFileToHidenInput;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by DeBeers on 07.10.2015.
 */
public class CancelWithoutSendResultFromWriterSide extends BaseTest{


    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public static void CancelWithoutSendResultFromWriterSide() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String filepath = System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\DMX.jpg";
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, order, writerLogin);
        assertEquals(orderInfoWriter.getTextFromOrderStatus(), "In Progress");

        orderInfoWriter.sendTextToTheClientTextArea(driver, writerText);
        uploadFileToHidenInput(driver, filepath);
        orderInfoWriter.waitForProgressBarWhenUploadingFiles();

        orderInfoWriter.clickOnCancelButtonInOrderComposingWS();
        assertTrue($WaitFor(orderInfoWriter.leaveAnOfferButton).isDisplayed());
        orderParametersCheck(order, orderInfoWriter, "Published");
    }
}
