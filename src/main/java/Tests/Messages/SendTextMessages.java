package Tests.Messages;

import Actions.Client.ClientGoToMessages;
import Actions.Writer.WriterGoToMessages;
import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.Messages;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static GeneralHelpers.Search.findMessage;

/**
 * Created by ilya on 01.09.2015.
 */
public class SendTextMessages extends BaseTest {





    @Test(groups={"regress 1.0"})
    public static void SendTextMessageToClient() throws Exception {

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");


        String textMessage = Messages.randomMessageGeneratorLength(20);
        WriterGoToMessages.sendMessageToClient(driver, clientLogin, orderObj, writerLogin, textMessage);

        String msg = findMessage(driver, textMessage);
        Assert.assertEquals(msg, textMessage);

        String clientMessage = ClientGoToMessages.checkDeliveryMessageFromWriter(driver, clientLogin, orderObj, textMessage);
        Assert.assertEquals(clientMessage, textMessage);


    }
}
