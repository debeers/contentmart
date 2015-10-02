package Actions.Writer;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.LeftMenuGeneralPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

/**
 * Created by CMG_TEST on 17.09.2015.
 */
public class WriterGoToAllOrders {


    public static OrderInfoAndActions CreateNewOrderBidOnItAndLeaveAnOffer(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin
                                                                            ) throws InterruptedException {

        ClientGoToCreateNewOrder.andPublish(driver, clientLogin, orderObject);
        LeftMenuGeneralPage leftMenuGeneralPage = new LeftMenuGeneralPage(driver);
        leftMenuGeneralPage.clickOnAllOrdersLeftMenu(driver, writerLogin);
        OrderInfoAndActions orderInfoAndActions = bidOnCreatedOrderByBidButton(driver, orderObject);
        orderInfoAndActions.clickOnLeaveAnOfferButtonFromBidOnOrder(driver);

    return orderInfoAndActions;

}

    public static OrderInfoAndActions bidOnCreatedOrderByBidButton(WebDriver driver, OrderObject orderObject){

            OrderInfoAndActions orderInfoAndActions = new OrderInfoAndActions(driver);
            orderInfoAndActions.clickOnAllOrdersLeftMenuMenu();
            orderInfoAndActions.clickOnBidButton(driver, orderObject);

        return orderInfoAndActions;

    }



    public static OrderInfoAndActions createNewOrderAndBidOnIt(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin) throws InterruptedException {

        ClientGoToCreateNewOrder.andPublish(driver, clientLogin, orderObject);
        LeftMenuGeneralPage leftMenuGeneralPage = new LeftMenuGeneralPage(driver);
        leftMenuGeneralPage.clickOnAllOrdersLeftMenu(driver, writerLogin);
        OrderInfoAndActions orderInfoAndActions = bidOnCreatedOrderByBidButton(driver, orderObject);


        return orderInfoAndActions;

    }




}
