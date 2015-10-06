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


    public static OrderInfoAndActions andAwardOrderToWriter(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin) throws InterruptedException {


        WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, orderObject, writerLogin);

        MyOrdersPage offersToOrder = clientGoToCreatedOrder(driver, clientLogin, orderObject);
        OrderInfoAndActions orderInfoClientPage = offersToOrder.clickOnSetAsWinnerButtonAndAprooveMoneyBlocking();
        orderObject.setTotalBalanceAfterBlocking(getCurrentBallanceFromMenuButton(driver));

        return orderInfoClientPage;
    }



}
