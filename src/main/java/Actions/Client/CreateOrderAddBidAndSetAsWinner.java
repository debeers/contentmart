package Actions.Client;

import Actions.Writer.WriterGoToAllOrders;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

import static Actions.Client.ClientGoToMyOrders.clientGoToCreatedOrder;
import static Actions.General.GoToBalanceGeneralActions.getCurrentBallanceFromMenuButton;

/**
 * Created by DeBeers on 18.09.2015.
 */
public class CreateOrderAddBidAndSetAsWinner {


    public static OrderInfoAndActions andAwardOrderToWriter(WebDriver driver, LoginObject clientLogin, OrderObject order,
                                                            LoginObject writerLogin) throws InterruptedException {

        WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, order, writerLogin);

        MyOrdersPage offersToOrder = clientGoToCreatedOrder(driver, clientLogin, order);
        OrderInfoAndActions orderInfoClientPage = offersToOrder.clickOnSetAsWinnerButtonAndAprooveMoneyBlocking();
        Thread.sleep(3000);
        order.setTotalBalanceAfterBlocking(getCurrentBallanceFromMenuButton(driver));
        System.out.println(order.getTotalBalanceAfterBlocking() + "Balance scan in awward order to writer");

        return orderInfoClientPage;
    }

}
