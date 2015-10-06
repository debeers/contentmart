package Tests.OrdersActions;

import Actions.Writer.WriterGoToStartToWorking;
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


    @Test(groups={"regress 1.0"})
    public static void WriterStartToWorking() throws InterruptedException {

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");
        String msg = "* Warning - Project owner might cancel the order if it's not delivered before the deadline! Submit in time!";

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, orderObj, writerLogin);

        assertEquals(orderInfoWriter.getTextFromWarningTextAfterStartWorking(), msg, "ERROR: Something go wrong, it`s not My Orders page");
        assertEquals(orderInfoWriter.getTextFromOrderStatus(), "In Progress", "ERROR: wrong status!");
    }
}
