package Tests.OrdersActions;

import Actions.Writer.WriterGoToStartToWorking;
import DataProviders.ActionsWithOrdersDataProvider;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 03.09.2015.
 */
public class StartWorking extends BaseTest {


    @Test(groups={"regress 1.0"}, dataProvider= "dataproviderObjectsForStartWorking", dataProviderClass = ActionsWithOrdersDataProvider.class)
    public static void WriterStartToWorking(Object clientLoginObject, Object orderObject, Object writerLoginObj, Object message) throws InterruptedException {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;
        String msg = message.toString();

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, orderObj, writerLogin);

        assertEquals(orderInfoWriter.getTextFromWarningTextAfterStartWorking(), msg, "ERROR: Something go wrong, it`s not My Orders page");
        assertEquals(orderInfoWriter.getTextFromOrderStatus(), "In Progress", "ERROR: wrong status!");
    }
}
