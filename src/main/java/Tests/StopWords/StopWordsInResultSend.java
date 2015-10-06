package Tests.StopWords;

import Actions.Writer.WriterGoToStartToWorking;
import Entities.LoginObject;
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

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");
        String msg = "why not to work outside ContentMart";
        String writerText = "Skype, icq, telephone number, give me a call, cuz I want to communicate directly, without site. Thank you very much and have a nice day!";

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, orderObj, writerLogin);
        orderInfoWriter.sendTextToTheClientTextArea(driver, writerText);
        Assert.assertEquals(orderInfoWriter.waitForStopWordsAllert(), msg);

    }







}
