package Tests.OrdersActions;

import Actions.Writer.WriterGoToEndResultToClient;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 03.09.2015.
 */
public class SendResultToClient extends BaseTest {


    @Test(groups={"regress 1.0"})
    public static void SendResult() throws InterruptedException {


        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";
        String successMessage = "Your result has been delivered to the order owner successfully! You will be notified as and when your result is accepted/rejected.";

        OrderInfoAndActions orderInfoWriter = WriterGoToEndResultToClient.andSendResultToTheClient(driver, clientLogin, orderObj, writerLogin, writerText);

        Assert.assertEquals(orderInfoWriter.getTextFromSuccessMessageAfterSendResult(), successMessage);
        assertEquals(orderInfoWriter.getTextFromOrderStatus(), "Result sent", "ERROR: wrong status!");
    }


}
