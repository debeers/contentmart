package Actions.Writer;

import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderInfoAndActions;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;

import static Actions.General.RegistrationAndLogin.logOut;
import static Actions.General.RegistrationAndLogin.loginAs;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 04.10.2015.
 */
public class WriterActionsAfterClientDecision {


    public static void writerAcceptDeclineAfterClientNegativeDecision(WebDriver driver, LoginObject writerLogin, OrderObject order) {

        MyOrdersPage myOrdersPage = loginAs(driver, writerLogin);
        myOrdersPage.searchBySearchEngineMyOrdersWriter(myOrdersPage, order);
        OrderInfoAndActions orderInfoAndActions = new OrderInfoAndActions(driver);
        orderInfoAndActions.clickOnAcceptDeclineButton();
        $(myOrdersPage.searchFieldMyOrders).shouldBe(Condition.visible);

        logOut(driver);

    }


}
