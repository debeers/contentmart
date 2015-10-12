package Actions.Writer;

import Actions.Client.CreateOrderAddBidAndSetAsWinner;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

import static Actions.General.RegistrationAndLogin.logOut;
import static Actions.General.RegistrationAndLogin.loginAs;


/**
 * Created by DeBeers on 18.09.2015.
 */
public class WriterGoToStartToWorking {


    public static OrderInfoAndActions andPressStartWorkingButton(WebDriver driver, LoginObject clientLogin, OrderObject order,
                                                                 LoginObject writerLogin) throws InterruptedException {

        CreateOrderAddBidAndSetAsWinner.andAwardOrderToWriter(driver, clientLogin, order, writerLogin);
        logOut(driver);
        MyOrdersPage myOrders = loginAs(driver, writerLogin);
        OrderInfoAndActions orderInfoWriter = myOrders.writerClickOnCreatedOrderByClientToStartToWorking(order);
        orderInfoWriter.clickOnStartWorkingButtonAndAcceptSweet();

        return orderInfoWriter;
    }

}
