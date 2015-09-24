package Tests.Balance;

import Actions.Client.CreateOrderAddBidSetWinnerGoToDecisionPage;
import Entities.Balance;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import GeneralHelpers.GeneralWaits;
import PageObjects.General.BalanceGeneralPage;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static Actions.goToBalanceGeneralActions.getCurrentBallanceFromMenuButton;
import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 23.09.2015.
 */
public class TransferMoney extends BaseTest{



    @Test(groups={"regress 1.0"}, dataProvider= "AcceptWriterText", dataProviderClass = AcceptWriterTextDataProvider.class)
    public static void TransferMoney(Object clientLoginObject, Object orderObject, Object writerLoginObj, Object text) throws InterruptedException {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;
        String writerText = text.toString();

        Order order = new Order();
        Balance balance = new Balance();

        OrderInfoAndActions decisionPage = CreateOrderAddBidSetWinnerGoToDecisionPage.andMakeAChoice(driver, clientLogin, orderObj, writerLogin, order, writerText);
        getCurrentBallanceFromMenuButton(driver, balance);
        decisionPage.clickOnAcceptButtonOnDecisionPage();

        BalanceGeneralPage balanceGeneral = new BalanceGeneralPage(driver);
        balanceGeneral.clickOnbalanceLeftMenu();
        GeneralWaits.waitForPageLoad(driver);

        String transferAmount = balanceGeneral.getMoneyTransferAmount(order.getEntityOrderSystemID());
        System.out.println("transfer money " + transferAmount);
        sleep(5000);
        driver.navigate().refresh();

        assertEquals(balanceGeneral.getBlokingAmountMoneyByOrderId(order.getEntityOrderSystemID()), "- " + order.getEntityOrderValue());
        assertEquals(balanceGeneral.getMoneyTransferAmount(order.getEntityOrderSystemID()).trim(), order.getEntityOrderValue());
        assertEquals(balanceGeneral.getBlockingMoneyBallance(order.getEntityOrderSystemID()), balanceGeneral.getTransferBalance(order.getEntityOrderSystemID()));

    }



}
