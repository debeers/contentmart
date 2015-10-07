package Tests.StatusActions.InProgress;

import Actions.Writer.WriterGoToStartToWorking;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by CMG_TEST on 07.10.2015.
 */
public class SaveAsDraft extends BaseTest {




    @Test(groups={"regress 1.0"})
    public static void SaveAsDraft() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, order, writerLogin);
        assertEquals(orderInfoWriter.getTextFromOrderStatus(), "In Progress");

        orderInfoWriter.sendTextToTheClientTextArea(driver, writerText);
        orderInfoWriter.clickOnSaveAsDraftButton();
        assertTrue(orderInfoWriter.waitForPlagiatorCheckAppear());

        assertEquals(orderInfoWriter.getTextFromSavedAsDraftTextArea(), writerText);
        assertEquals(orderInfoWriter.getTextFromOrderStatus(), "In Progress");

    }


}
