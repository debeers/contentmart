package Tests.StopWords;

import Actions.Writer.WriterGoToStartToWorking;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by CMG_TEST on 28.09.2015.
 */
public class StopWordsInResultSend extends BaseTest {


    @Test(groups={"regress 1.0"})
    public static void StopWordsInResultSend() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String msg = "why not to work outside ContentMart";
        String writerText = "Skype, icq, telephone number, give me a call, cuz I want to communicate directly, without site. Thank you very much and have a nice day!";

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, order, writerLogin);
        orderInfoWriter.sendTextToTheClientTextArea(driver, writerText);
        Assert.assertEquals(orderInfoWriter.waitForStopWordsAllert(), msg);
    }
}
