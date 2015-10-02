package Tests.Messages;

import Actions.Client.ClientGoToMessages;
import Actions.Writer.WriterGoToMessages;
import DataProviders.MessagesDataProvider;
import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.Messages;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static GeneralHelpers.Messages.findMessage;

/**
 * Created by ilya on 01.09.2015.
 */
public class SendTextMessages extends BaseTest {





    @Test(groups={"regress 1.0"}, dataProvider= "dataproviderForMessages", dataProviderClass = MessagesDataProvider.class)
    public static void SendTextMessageToClient(Object clientLoginObject, Object orderObject, Object writerLoginObj) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;


        String textMessage = Messages.randomMessageGeneratorLength(20);
        WriterGoToMessages.sendMessageToClient(driver, clientLogin, orderObj, writerLogin, textMessage);

        String msg = findMessage(driver, textMessage);
        Assert.assertEquals(msg, textMessage);

        String clientMessage = ClientGoToMessages.checkDeliveryMessageFromWriter(driver, clientLogin, orderObj, textMessage);
        Assert.assertEquals(clientMessage, textMessage);


    }
}
