package Tests.StatusActions.Awarded;

import Actions.Writer.WriterGoToStartToWorking;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 03.09.2015.
 */
public class StartWorking extends BaseTest {


    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public static void WriterStartToWorking() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String msg = "* Warning - Project owner might cancel the order if it's not delivered before the deadline! Submit in time!";

        OrderInfoAndActions orderInfoWriter = WriterGoToStartToWorking.andPressStartWorkingButton(driver, clientLogin, order, writerLogin);
        assertEquals(orderInfoWriter.getTextFromWarningTextAfterStartWorking(), msg);
        assertEquals(orderInfoWriter.getTextFromOrderStatus(), "In Progress");
    }
}
