package Tests.OrdersActions;

import Actions.Writer.WriterGoToEndResultToClient;
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

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";
        String successMessage = "Your result has been delivered to the order owner successfully! You will be notified as and when your result is accepted/rejected.";
        String filepath = System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\DMX.jpg";

        OrderInfoAndActions orderInfoWriter = WriterGoToEndResultToClient.andSendResultToTheClient(driver, clientLogin, order, writerLogin, writerText, filepath);

        Assert.assertEquals(orderInfoWriter.getTextFromSuccessMessageAfterSendResult(), successMessage);
        assertEquals(orderInfoWriter.getTextFromOrderStatus(), "Result sent");
    }
}
