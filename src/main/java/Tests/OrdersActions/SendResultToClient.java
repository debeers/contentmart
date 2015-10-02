package Tests.OrdersActions;

import Actions.Writer.WriterGoToOrderWorkflow;
import DataProviders.ActionsWithOrdersDataProvider;
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


    @Test(groups={"regress 1.0"}, dataProvider= "dataproviderForSendResultTest", dataProviderClass = ActionsWithOrdersDataProvider.class)
    public static void SendResult(Object clientLoginObject, Object orderObject, Object writerLoginObj, Object text, Object msg) throws InterruptedException {


        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;
        String writerText = text.toString();
        String successMessage = msg.toString();

        OrderInfoAndActions orderInfoWriter = WriterGoToOrderWorkflow.andSendResultToTheClient(driver, clientLogin, orderObj, writerLogin, writerText);

        Assert.assertEquals(orderInfoWriter.getTextFromSuccessMessageAfterSendResult(), successMessage);
        assertEquals(orderInfoWriter.getTextFromOrderStatus(), "Result sent", "ERROR: wrong status!");
    }


}
