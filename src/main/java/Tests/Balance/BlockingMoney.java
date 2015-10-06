package Tests.Balance;

import Actions.Client.CreateOrderAddBidAndSetAsWinner;
import Actions.General.GoToBalanceGeneralActions;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.BalanceGeneralPage;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.General.GoToBalanceGeneralActions.clientGoToCheckForBalance;
import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 23.09.2015.
 */
public class BlockingMoney extends BaseTest{






    @Test(groups={"regress 1.0"})
    public static void BlockingMoney() throws InterruptedException {

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");

        CreateOrderAddBidAndSetAsWinner.andAwardOrderToWriter(driver, clientLogin, orderObj, writerLogin);
        BalanceGeneralPage balanceGeneral =
                clientGoToCheckForBalance(driver, clientLogin, orderObj);

        assertEquals(balanceGeneral.xBlockingStatus(orderObj.getEntityOrderSystemID()), "Blocking");
        assertEquals(balanceGeneral.xBlockingAmount(orderObj.getEntityOrderSystemID()), "- " + orderObj.getEntityOrderValue());

        Boolean differenceCount = GoToBalanceGeneralActions.blockingBallanceDifference(orderObj);
        Assert.assertTrue(differenceCount);

    }












}
