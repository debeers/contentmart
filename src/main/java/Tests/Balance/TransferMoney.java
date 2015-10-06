package Tests.Balance;

import Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.BalanceGeneralPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage.acceptWriterText;
import static Actions.General.GoToBalanceGeneralActions.clientGoToCheckForBalance;
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

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))";


        OrderInfoAndActions decisionPage = CreateOrderAddBidSetWinnerGoToDecisionPage.andMakeAChoice(driver, clientLogin, orderObj, writerLogin, writerText);
        acceptWriterText(decisionPage);
        BalanceGeneralPage balanceGeneral =
                clientGoToCheckForBalance(driver, clientLogin, orderObj);


        String transferAmount = balanceGeneral.xTransferAmount(orderObj.getEntityOrderSystemID());
        System.out.println("transfer money " + transferAmount);
        sleep(5000);
        driver.navigate().refresh();

        assertEquals(balanceGeneral.xBlockingAmount(orderObj.getEntityOrderSystemID()), "- " + orderObj.getEntityOrderValue());
        assertEquals(balanceGeneral.xTransferAmount(orderObj.getEntityOrderSystemID()).trim(), orderObj.getEntityOrderValue());

        assertEquals(balanceGeneral.xBlockingBalance(orderObj.getEntityOrderSystemID()), balanceGeneral.xTransferBalance(orderObj.getEntityOrderSystemID()));
        assertTrue(transferBallanceDifference( orderObj));

    }



}
