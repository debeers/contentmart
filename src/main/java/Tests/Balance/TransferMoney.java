package Tests.Balance;

import Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage;
import Entities.OrderObject;
import PageObjects.General.BalanceGeneralPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage.acceptWriterText;
import static Actions.General.GoToBalanceGeneralActions.clientGoToCheckForBlockingBalance;
import static Actions.General.GoToBalanceGeneralActions.transferBallanceDifference;
import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by CMG_TEST on 23.09.2015.
 */
public class TransferMoney extends BaseTest{



    @Test(groups={"regress 1.0"})
    public static void TransferMoney() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";


        OrderInfoAndActions decisionPage = CreateOrderAddBidSetWinnerGoToDecisionPage.andMakeAChoice(driver, clientLogin, order, writerLogin, writerText);
        acceptWriterText(decisionPage);
        BalanceGeneralPage balanceGeneral =
                clientGoToCheckForBlockingBalance(driver, order);


        String transferAmount = balanceGeneral.xTransferAmount(order.getEntityOrderSystemID());
        System.out.println("transfer money " + transferAmount);
        sleep(5000);
        driver.navigate().refresh();

        assertEquals(balanceGeneral.xBlockingAmount(order.getEntityOrderSystemID()), "- " + order.getEntityOrderValue());
        assertEquals(balanceGeneral.xTransferAmount(order.getEntityOrderSystemID()).trim(), order.getEntityOrderValue());

        assertEquals(balanceGeneral.xBlockingBalance(order.getEntityOrderSystemID()), balanceGeneral.xTransferBalance(order.getEntityOrderSystemID()));
        assertTrue(transferBallanceDifference( order));

    }



}
