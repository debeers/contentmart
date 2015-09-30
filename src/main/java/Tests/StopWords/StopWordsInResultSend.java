package Tests.StopWords;

import Actions.Writer.WriterGoToStartToWorking;
import DataProviders.StopWordsDataProvider;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by CMG_TEST on 28.09.2015.
 */
public class StopWordsInResultSend extends BaseTest {






    @Test(groups={"regress 1.0"}, dataProvider= "stopWordsForSendResultTest", dataProviderClass = StopWordsDataProvider.class)
    public static void StopWordsInResultSend(Object clientLoginObject, Object orderObject, Object writerLoginObj, Object text, Object stopMsg) throws InterruptedException {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;
        String msg = (String)stopMsg;
        String writerText = text.toString();

        Order order = new Order();
        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, orderObj, writerLogin, order);
        orderInfoWriter.sendTextToTheClientTextArea(driver, writerText);
        Assert.assertEquals(orderInfoWriter.waitForStopWordsAllert(), msg);

    }







}
