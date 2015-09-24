package Actions;

import Actions.Writer.WriterGoToAllOrders;
import Entities.Balance;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.General.BalanceGeneralPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

import static Actions.Client.ClientGoToMyOrders.clientGoToCreatedOrder;


/**
 * Created by CMG_TEST on 07.09.2015.
 */
public class BalanceGeneralActions {


    public static void getCurrentBallanceFromMenuButton(WebDriver driver, Balance balance) {
        System.out.println(balance.getTotalBalance());
        BalanceGeneralPage balanceGeneral = new BalanceGeneralPage(driver);
        String ballance = balanceGeneral.clientBallanceFromLeftMenu.getText();
        System.out.println(ballance);
        balance.setTotalBalance(ballance.substring(ballance.indexOf(' ') + 1));
        System.out.println("Setting of total ballance:" + balance.getTotalBalance());
        System.out.println("Total client ballance before:  " + balance.getTotalBalance());

    }


    public static Boolean blockingBallanceDifference(String bal, Order order, Balance balance) {

        double totalBalance = Double.parseDouble(balance.getTotalBalance());
        double orderPrice = Double.parseDouble(order.getEntityOrderValue());
        double actualBalance = Double.parseDouble(bal);

        double res = totalBalance - actualBalance;

        if (res == orderPrice) {
            System.out.println("Balance before: " + balance.getTotalBalance() + "\n" +
                    "Balance price: " + order.getEntityOrderValue() + "\n" + "Actual ballance: " + bal + "\n");
            System.out.println("Balance operation correct! ");

            return true;

        } else {
            System.out.println("!!!!!!! Wrong balance man!!!!!!!!!");
            return false;
        }

    }


    public static OrderInfoAndActions andAwardOrderToWriterAndScanBallance(WebDriver driver, LoginObject clientLogin, OrderObject orderObject,
                                                                           LoginObject writerLogin, Order order, Balance balance) throws InterruptedException {


        WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, orderObject, writerLogin, order);

        MyOrdersPage offersToOrder = clientGoToCreatedOrder(driver, clientLogin, order);
        getCurrentBallanceFromMenuButton(driver, balance);
        OrderInfoAndActions orderInfoClientPage = offersToOrder.clickOnSetAsWinnerButtonAndAprooveMoneyBlocking();

        return orderInfoClientPage;
    }












}
