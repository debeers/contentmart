package Actions.Writer;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.LeftMenuGeneralPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

import static Actions.General.RegistrationAndLogin.logOut;
import static Actions.General.RegistrationAndLogin.loginAs;

/**
 * Created by CMG_TEST on 17.09.2015.
 */
public class WriterGoToAllOrders {


    public static OrderInfoAndActions CreateNewOrderBidOnItAndLeaveAnOffer(WebDriver driver, LoginObject clientLogin, OrderObject order,
                                                                           LoginObject writerLogin) throws InterruptedException {

        ClientGoToCreateNewOrder.andPublish(driver, clientLogin, order);
        LeftMenuGeneralPage leftMenuGeneralPage = new LeftMenuGeneralPage(driver);
        logOut(driver);
        loginAs(driver, writerLogin);
        leftMenuGeneralPage.clickOnAllOrdersFromLeftMenu(driver);
        OrderInfoAndActions orderInfoAndActions = bidOnCreatedOrderByBidButton(driver, order);
        orderInfoAndActions.clickOnLeaveAnOfferButtonFromBidOnOrder(driver);

        return orderInfoAndActions;
    }


    public static OrderInfoAndActions bidOnCreatedOrderByBidButton(WebDriver driver, OrderObject order) {

        OrderInfoAndActions orderInfoAndActions = new OrderInfoAndActions(driver);
        orderInfoAndActions.clickOnAllOrdersFromLeftMenuMenu();
        orderInfoAndActions.clickOnBidButton(driver, order);

        return orderInfoAndActions;
    }


    public static OrderInfoAndActions createNewOrderAndBidOnIt(WebDriver driver, LoginObject clientLogin, OrderObject order,
                                                               LoginObject writerLogin) throws InterruptedException {

        ClientGoToCreateNewOrder.andPublish(driver, clientLogin, order);
        LeftMenuGeneralPage leftMenuGeneralPage = new LeftMenuGeneralPage(driver);
        logOut(driver);
        loginAs(driver, writerLogin);
        leftMenuGeneralPage.clickOnAllOrdersFromLeftMenu(driver);
        OrderInfoAndActions orderInfoAndActions = bidOnCreatedOrderByBidButton(driver, order);

        return orderInfoAndActions;
    }

}