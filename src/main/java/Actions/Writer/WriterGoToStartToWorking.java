package Actions.Writer;

import Actions.Client.CreateOrderAddBidAndSetAsWinner;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.WebDriver;

import static Actions.RegistrationAndLogin.logOut;
import static Actions.RegistrationAndLogin.loginAs;


/**
 * Created by DeBeers on 18.09.2015.
 */
public class WriterGoToStartToWorking {


    public static OrderInfoAndActions andPressStartWorkingButton(WebDriver driver, LoginObject clientLogin, OrderObject orderObject, LoginObject writerLogin) throws InterruptedException {

        CreateOrderAddBidAndSetAsWinner.andAwardOrderToWriter(driver, clientLogin, orderObject, writerLogin);
        logOut(driver);
        MyOrdersPage myOrders = loginAs(driver, writerLogin);
        OrderInfoAndActions orderInfoWriter = myOrders.writerClickOnCreatedOrderByClientToStartToWorking(orderObject);
        orderInfoWriter.clickOnStartWorkingButtonAndAcceptSweet();

        return orderInfoWriter;

    }



}
