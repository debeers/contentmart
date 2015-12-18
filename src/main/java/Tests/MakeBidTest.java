package Tests;

import Entities.OrderObject;
import PageObjects.General.OrderWorkFlow;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static Actions.Client.CreateNewOrder.clientGoToCreateNewOrder;
import static Actions.General.RegistrationAndLogin.loginAs;
import static Utilities.PropertiesLoader.propertyXMLoader;

/**
 * Created by DeBeers on 17.12.2015.
 */
public class MakeBidTest extends BaseTest{

    @Test
    public void MakeBidTest_Test() throws IOException, InterruptedException, AWTException, SQLException {
        Properties props  = propertyXMLoader(System.getProperty("user.dir") +
                "\\src\\main\\java\\Tests\\TestDataXML\\CreateNewOrder\\OrderData.xml");

        OrderObject order = clientGoToCreateNewOrder(driver, clientLogin, props);
        OrderWorkFlow orderWorkFlow = new OrderWorkFlow(driver);
        orderWorkFlow.clickOnLogOutFromDropMenu();

        loginAs(driver, writerLogin)
                .clickOnAllOrdersFromTopMenu()
                .search(order.getOrderSystemID())
                .clickOnOrderBidButton(order.getOrderSystemID())
                .setDesiredDeadline(orderWorkFlow)
                .setDesiredPrice(
                        props.getProperty("Desired Price"),
                        props.getProperty("Per Word"),
                        orderWorkFlow
                )
                .leaveBidDescription(
                        props.getProperty("Description for Offer"),
                        orderWorkFlow
                )
                .clickOnLeaveAnOfferBidButton(orderWorkFlow);

    }

}
