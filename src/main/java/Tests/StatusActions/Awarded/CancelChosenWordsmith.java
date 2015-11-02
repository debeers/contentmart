package Tests.StatusActions.Awarded;

import Actions.Client.CreateOrderAddBidAndSetAsWinner;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 06.10.2015.
 */
public class CancelChosenWordsmith extends BaseTest{


    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public static void CancelChosenWordsmith() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String msg = "No writer has responded to your order yet! You may wait for sometime for writers to see your order or edit " +
                "your order to make it a bit enticing in terms of remuneration!";

        OrderInfoAndActions orderInfoClientPage = CreateOrderAddBidAndSetAsWinner.andAwardOrderToWriter(driver, clientLogin, order, writerLogin);
        assertEquals(orderInfoClientPage.getTextFromOrderStatus(), "Awarded");

        orderInfoClientPage.clickOnCancelChosenWorsmith();
        $(orderInfoClientPage.infoMsgAfterCancelChosenWorsmith).should(Condition.appear);
        assertEquals(orderInfoClientPage.getorderStatus(), "Published");
        assertEquals(orderInfoClientPage.infoMsgText(), msg);
    }
}
