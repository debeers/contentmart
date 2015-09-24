package Actions.Client;

import Actions.Writer.WriterGoToAllOrders;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

import static Actions.Client.ClientGoToMyOrders.clientGoToCreatedOrder;

/**
 * Created by DeBeers on 18.09.2015.
 */
public class CreateOrderAddBidAndSetAsWinner {


    public static OrderInfoAndActions andAwardOrderToWriter(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin, Order order) throws InterruptedException {


        WriterGoToAllOrders.CreateNewOrderBidOnItAndLeaveAnOffer(driver, clientLogin, orderObject, writerLogin, order);

        MyOrdersPage offersToOrder = clientGoToCreatedOrder(driver, clientLogin, order);
        OrderInfoAndActions orderInfoClientPage = offersToOrder.clickOnSetAsWinnerButtonAndAprooveMoneyBlocking();

        return orderInfoClientPage;
    }



}
