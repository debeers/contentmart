package Tests.Balance;

import Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.BalanceGeneralPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage.declineWriterTextAction;
import static Actions.General.GoToBalanceGeneralActions.clientGoToCheckForBalance;
import static Actions.General.GoToBalanceGeneralActions.unBlockingBallanceDifference;
import static Actions.Writer.WriterActionsAfterClientDecision.writerAcceptDeclineAfterClientNegativeDecision;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by DeBeers on 04.10.2015.
 */
public class UnblockingMoneyAfterClientDecline extends BaseTest{





    @Test(groups={"regress 1.0"})
    public static void DeclineTextFromClientSide() throws InterruptedException {

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";
        String declineReason = "Nothing pearsonal, just test!";



        OrderInfoAndActions decisionPage = CreateOrderAddBidSetWinnerGoToDecisionPage.andMakeAChoice(driver, clientLogin, orderObj, writerLogin, writerText);
        declineWriterTextAction(driver, decisionPage, declineReason);
        writerAcceptDeclineAfterClientNegativeDecision(driver, writerLogin, orderObj);
        BalanceGeneralPage balanceGeneral = clientGoToCheckForBalance(driver, clientLogin, orderObj);



        assertEquals(balanceGeneral.xBlockingStatus(orderObj.getEntityOrderSystemID()), "Blocking");
        assertEquals(balanceGeneral.xUnBlockingStatus(orderObj.getEntityOrderSystemID()), "Unblocking");

        assertEquals(balanceGeneral.xBlockingAmount(orderObj.getEntityOrderSystemID()), "- " + orderObj.getEntityOrderValue());
        assertEquals(balanceGeneral.xBlockingBalance(orderObj.getEntityOrderSystemID()).trim(),  orderObj.getTotalBalanceAfterBlocking());

        assertEquals(balanceGeneral.xUnBlockingAmount(orderObj.getEntityOrderSystemID()).trim(), "+ " + orderObj.getEntityOrderValue());
        assertEquals(balanceGeneral.xUnBlockingBallance(orderObj.getEntityOrderSystemID()).trim(), orderObj.getTotalBalanceAfterUnBlocking());

        assertTrue(unBlockingBallanceDifference(orderObj));


    }
}
