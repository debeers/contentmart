package Tests.Balance;

import Actions.BalanceGeneralActions;
import DataProviders.BallanceCheckDataProvider;
import Entities.Balance;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import GeneralHelpers.GeneralWaits;
import PageObjects.General.BalanceGeneralPage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 23.09.2015.
 */
public class BlockingMoney extends BaseTest{






    @Test(groups={"regress 1.0"}, dataProvider= "dataproviderForBallance", dataProviderClass = BallanceCheckDataProvider.class)
    public static void BlockingMoney(Object clientLoginObject, Object orderObject, Object writerLoginObj) throws InterruptedException {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;

        Order order = new Order();
        Balance balance = new Balance();

        BalanceGeneralActions.andAwardOrderToWriterAndScanBallance(driver, clientLogin, orderObj, writerLogin, order, balance);
        BalanceGeneralPage balanceGeneral = new BalanceGeneralPage(driver);

        balanceGeneral.clickOnbalanceLeftMenu();
        GeneralWaits.waitForPageLoad(driver);

        String blocking = balanceGeneral.getBlokingMoneyByOrderId(order.getEntityOrderSystemID());
        assertEquals(blocking, "Blocking");

        String blockingAmount = balanceGeneral.getBlokingAmountMoneyByOrderId(order.getEntityOrderSystemID());
        assertEquals(blockingAmount, "- " + order.getEntityOrderValue());

        String bal = balanceGeneral.getBlockingMoneyBallance(order.getEntityOrderSystemID());
        Boolean differenceCount = BalanceGeneralActions.blockingBallanceDifference(bal, order, balance);
        Assert.assertTrue(differenceCount);

    }












}
