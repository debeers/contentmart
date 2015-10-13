package Tests.Balance;

import Actions.Client.CreateOrderAddBidAndSetAsWinner;
import Actions.General.GoToBalanceGeneralActions;
import Entities.OrderObject;
import PageObjects.General.BalanceGeneralPage;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.General.GoToBalanceGeneralActions.clientGoToCheckForBlockingBalance;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by CMG_TEST on 23.09.2015.
 */
public class BlockingMoney extends BaseTest{

    @Test(groups={"regress 1.0"})
    public static void BlockingMoney() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");

        CreateOrderAddBidAndSetAsWinner.andAwardOrderToWriter(driver, clientLogin, order, writerLogin);
        BalanceGeneralPage balanceGeneral = clientGoToCheckForBlockingBalance(driver, order);

        assertEquals(balanceGeneral.xBlockingStatus(order.getEntityOrderSystemID()), "Blocking");
        assertEquals(balanceGeneral.xBlockingAmount(order.getEntityOrderSystemID()), "- " + order.getEntityOrderValue());
        assertTrue(GoToBalanceGeneralActions.checkForCorrectBlockingMoneyOperation(order));

    }
}
