package Tests.Messages;

import Actions.Client.ClientGoToMessages;
import Actions.Writer.WriterGoToMessages;
import Entities.OrderObject;
import GeneralHelpers.Messages;
import PageObjects.General.MyMessagesPage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static GeneralHelpers.Search.findMessage;

/**
 * Created by ilya on 01.09.2015.
 */
public class SendTextMessages extends BaseTest {

    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public static void SendTextMessageToClient() throws Exception {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        String textMessage = Messages.randomTextGeneratorLength(20);
        WriterGoToMessages.sendMessageToClient(driver, clientLogin, order, writerLogin, textMessage);

        String msg = findMessage(driver, textMessage);
        Assert.assertEquals(msg, textMessage);

        MyMessagesPage myMessagesPage = new MyMessagesPage(driver);
        myMessagesPage.closeMessageWindowClick();
        Thread.sleep(3000);

        String clientMessage = ClientGoToMessages.checkDeliveryMessageFromWriter(driver, clientLogin, order, textMessage);
        Assert.assertEquals(clientMessage, textMessage);
    }
}
