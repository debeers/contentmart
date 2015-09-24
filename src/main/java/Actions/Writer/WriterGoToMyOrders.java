package Actions.Writer;

import PageObjects.General.OrderInfoAndActions;
import PageObjects.General.MyOrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static org.testng.Assert.assertEquals;

/**
 * Created by CMG_TEST on 17.09.2015.
 */
public class WriterGoToMyOrders {


    public static OrderInfoAndActions FindCreatedOrderFromMyOrdersWriter(WebDriver driver, MyOrdersPage myOrders, String createdOrderName){

            OrderInfoAndActions offerPage = searchOrderBySearchEngineFromMyOrdersWriter(driver, myOrders, createdOrderName);

            String order = offerPage.orderName.getText();
            assertEquals(order, createdOrderName, "ERROR: not your order opened!");
            System.out.println("Balance successfully found!");

        return offerPage;

    }

    public static OrderInfoAndActions searchOrderBySearchEngineFromMyOrdersWriter(WebDriver driver, MyOrdersPage myOrders, String createdOrderName){
        WebDriverWait wait = new WebDriverWait(driver, 15);

        myOrders.searchBySearchEngineMyOrdersWriter(myOrders, createdOrderName);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(createdOrderName))).click();
            waitForPageLoad(driver);
        OrderInfoAndActions offerPage = new OrderInfoAndActions(driver);

        return offerPage;

    }
}
