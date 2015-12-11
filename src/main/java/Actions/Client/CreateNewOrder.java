package Actions.Client;

import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.DBUtill;
import PageObjects.Client.NewOrderPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderWorkFlow;
import PageObjects.General.TopMenuGeneralPage;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static Actions.General.RegistrationAndLogin.loginAs;
import static Actions.General.RegistrationAndLogin.switchUser;
import static GeneralHelpers.CreateNewOrderHelper.checkForTotalPrice;
import static GeneralHelpers.CreateNewOrderHelper.getCurrentUserTimezoneFromNewOrderPage;
import static GeneralHelpers.DBWorker.setUserCurrencyToUSD;
import static GeneralHelpers.DateTimeUtils.getEtalonTimezone;
import static GeneralHelpers.DateTimeUtils.getUserTimezoneName;
import static GeneralHelpers.PropertiesLoader.propertyXMLoader;
import static SQLRepo.General.getUserCurrencyID;
import static junit.framework.Assert.assertEquals;


/**
 * Created by DeBeers on 20.11.2015.
 */
public class CreateNewOrder {

    public static OrderWorkFlow clientGoToCreateNewOrder(WebDriver driver, MyOrdersPage myOrdersPage, String userDBconnection, String orderDataParameters, Properties properties) throws InterruptedException, IOException, SQLException, AWTException {

        String orderData  = orderDataParameters;
        Properties props  = propertyXMLoader(System.getProperty("user.dir") + properties);
        OrderObject order = new OrderObject();
        DBUtill dbUtill   = new DBUtill().initDB();

        setUserCurrencyToRupee(dbUtill, props);
        NewOrderPage newOrderPage = myOrdersPage.clickOnNewOrderFromTopMenu();
        newOrderPage.setOrder(dbUtill, order, orderData);
        OrderWorkFlow orderWorkFlow = newOrderPage.clickOnPublishButton();
        Thread.sleep(1500); //server side wait

        order.setOrderSystemID(orderWorkFlow.getOrderID());
        order.setOrderPublicDate(orderWorkFlow.getPublichDate());
        order.setOrderStatus(orderWorkFlow.getOrderStatus());

        return new OrderWorkFlow(driver);
    }

    public static void setUserCurrencyToRupee(DBUtill dbUtill, Properties props) throws IOException, SQLException {
        if(dbUtill.getColumn(
                getUserCurrencyID(
                        props.getProperty("UserEmail")), "currency_id") != null){
            setUserCurrencyToUSD(dbUtill, "debeers1989@gmail.com");
        }
    }
}
