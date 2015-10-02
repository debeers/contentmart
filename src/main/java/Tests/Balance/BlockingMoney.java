package Tests.Balance;

import Actions.goToBalanceGeneralActions;
import DataProviders.BallanceCheckDataProvider;
import Entities.LoginObject;
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

        goToBalanceGeneralActions.andAwardOrderToWriterAndScanBallance(driver, clientLogin, orderObj, writerLogin);
        BalanceGeneralPage balanceGeneral = new BalanceGeneralPage(driver);

        balanceGeneral.clickOnbalanceLeftMenu();
        GeneralWaits.waitForPageLoad(driver);

        String blocking = balanceGeneral.getBlokingMoneyByOrderId(orderObj.getEntityOrderSystemID());
        assertEquals(blocking, "Blocking");

        String blockingAmount = balanceGeneral.getBlokingAmountMoneyByOrderId(orderObj.getEntityOrderSystemID());
        assertEquals(blockingAmount, "- " + orderObj.getEntityOrderValue());

        String bal = balanceGeneral.getBlockingMoneyBallance(orderObj.getEntityOrderSystemID());
        Boolean differenceCount = goToBalanceGeneralActions.blockingBallanceDifference(bal, orderObj);
        Assert.assertTrue(differenceCount);

    }












}
