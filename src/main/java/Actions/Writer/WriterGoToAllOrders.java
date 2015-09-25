package Actions.Writer;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import PageObjects.General.LeftMenuGeneralPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

/**
 * Created by CMG_TEST on 17.09.2015.
 */
public class WriterGoToAllOrders {


    public static OrderInfoAndActions CreateNewOrderBidOnItAndLeaveAnOffer(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin,
                                                                           Order order) throws InterruptedException {

        ClientGoToCreateNewOrder.andPublish(driver, clientLogin, orderObject, order);
        LeftMenuGeneralPage leftMenuGeneralPage = new LeftMenuGeneralPage(driver);
        leftMenuGeneralPage.clickOnAllOrdersLeftMenu(driver, writerLogin);
        OrderInfoAndActions orderInfoAndActions = bidOnCreatedOrderByBidButton(driver, order);
        orderInfoAndActions.clickOnLeaveAnOfferButtonFromBidOnOrder(driver);

    return orderInfoAndActions;

}

    public static OrderInfoAndActions bidOnCreatedOrderByBidButton(WebDriver driver, Order order){

            OrderInfoAndActions orderInfoAndActions = new OrderInfoAndActions(driver);

            orderInfoAndActions.clickOnAllOrdersLeftMenuMenu();
            orderInfoAndActions.clickOnBidButton(driver, order);

        return orderInfoAndActions;

    }



    public static OrderInfoAndActions createNewOrderAndBidOnIt(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin,
                                                                           Order order) throws InterruptedException {

        ClientGoToCreateNewOrder.andPublish(driver, clientLogin, orderObject, order);
        LeftMenuGeneralPage leftMenuGeneralPage = new LeftMenuGeneralPage(driver);
        leftMenuGeneralPage.clickOnAllOrdersLeftMenu(driver, writerLogin);
        OrderInfoAndActions orderInfoAndActions = bidOnCreatedOrderByBidButton(driver, order);


        return orderInfoAndActions;

    }




}
