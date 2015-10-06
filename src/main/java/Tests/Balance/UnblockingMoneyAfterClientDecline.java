package Tests.Balance;

import Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage;
import Entities.OrderObject;
import PageObjects.General.BalanceGeneralPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage.declineWriterTextAction;
import static Actions.General.GoToBalanceGeneralActions.clientGoToCheckForUnBlockingBalance;
import static Actions.General.GoToBalanceGeneralActions.unBlockingBallanceDifference;
import static Actions.Writer.WriterActionsAfterClientDecision.writerAcceptDeclineAfterClientNegativeDecision;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by DeBeers on 04.10.2015.
 */
public class UnblockingMoneyAfterClientDecline extends BaseTest{





    @Test(groups={"regress 1.0"})
    public static void UnblockingMoneyAfterClientDecline() throws InterruptedException {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";
        String declineReason = "Nothing pearsonal, just test!";


        OrderInfoAndActions decisionPage = CreateOrderAddBidSetWinnerGoToDecisionPage.andMakeAChoice(driver, clientLogin, order, writerLogin, writerText);
        declineWriterTextAction(driver, decisionPage, declineReason);
        writerAcceptDeclineAfterClientNegativeDecision(driver, writerLogin, order);
        BalanceGeneralPage balanceGeneral = clientGoToCheckForUnBlockingBalance(driver, order, clientLogin);


        assertEquals(balanceGeneral.xBlockingStatus(order.getEntityOrderSystemID()), "Blocking");
        assertEquals(balanceGeneral.xUnBlockingStatus(order.getEntityOrderSystemID()), "Unblocking");

        assertEquals(balanceGeneral.xBlockingAmount(order.getEntityOrderSystemID()), "- " + order.getEntityOrderValue());
       // assertEquals(balanceGeneral.xBlockingBalance(order.getEntityOrderSystemID()).trim(),  order.getTotalBalanceAfterBlocking()); // have some problem for now with this assert (fixing)

        assertEquals(balanceGeneral.xUnBlockingAmount(order.getEntityOrderSystemID()).trim(), "+ " + order.getEntityOrderValue());
        assertEquals(balanceGeneral.xUnBlockingBallance(order.getEntityOrderSystemID()).trim(), order.getTotalBalanceBefore());

        assertTrue(unBlockingBallanceDifference(order));


    }
}
