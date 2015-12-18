package Tests;

import Actions.Client.CreateNewOrder;
import Entities.OrderObject;
import PageObjects.Client.NewOrderPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderWorkFlow;
import junit.framework.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static Actions.Client.CreateNewOrder.setUserCurrencyToRupee;
import static Actions.General.RegistrationAndLogin.logOut;
import static Actions.General.RegistrationAndLogin.loginAs;
import static Utilities.DateTimeUtils.getEtalonTimezone;
import static Utilities.DateTimeUtils.getUserTimezoneName;
import static Utilities.UploadingAndDownloadingFiles.getFileName;
import static Utilities.PropertiesLoader.propertyXMLoader;
import static PageObjects.Client.NewOrderPage.checkForWordsRequired;
import static PageObjects.General.OrderWorkFlow.compareExpertises;
import static Repository.UserModelRepo.getUserCurrencyID;
import static junit.framework.Assert.*;

public class  CreateNewOrderTest extends BaseTest {

    @Test
    public void CreateNewOrderTest_Test() throws InterruptedException, IOException, AWTException, SQLException {

        Properties props  = propertyXMLoader(System.getProperty("user.dir") +
                "\\src\\main\\java\\Tests\\TestDataXML\\CreateNewOrder\\OrderData.xml");
        System.out.println(getUserCurrencyID("debeers1989@gmail.com"));
        OrderObject order = new OrderObject();


        setUserCurrencyToRupee(props);
        String etalTime = getEtalonTimezone(
                getUserTimezoneName(props.getProperty("UserEmail")));

        MyOrdersPage myOrdersPage = loginAs(driver, clientLogin);
        NewOrderPage newOrderPage = myOrdersPage.clickOnNewOrderFromTopMenu();
        newOrderPage.setOrder(order, props);

        assertEquals(CreateNewOrder.checkForTotalPrice(

                order.getOrderValueInRupee(),
                order.getOrderWordsRequired(),
                order.getOrderArticlesQuantity(),
                props.getProperty("PricePerWordOrPerOrder")),
                Double.parseDouble(newOrderPage.getOrderTotalPriceValue())
        );

        assertEquals(etalTime, CreateNewOrder.getCurrentUserTimezoneFromNewOrderPage(newOrderPage));
        Thread.sleep(1500); //server side wait
        OrderWorkFlow orderWorkFlow = newOrderPage.clickOnPublishButton();

        order.setOrderSystemID(orderWorkFlow.getOrderID());
        order.setOrderPublicDate(orderWorkFlow.getPublichDate());
        order.setOrderStatus(orderWorkFlow.getOrderStatus());

        CreateNewOrder.dateComparator(order, orderWorkFlow);
        Assert.assertEquals(orderWorkFlow.getOrderStatus(), "Published");
        Assert.assertTrue(orderWorkFlow.getOrderStatusInfo().contains(props.getProperty("StatusInfo")));
        Assert.assertEquals(order.getOrderName(), orderWorkFlow.getOrderTitle());
        Assert.assertEquals(order.getOrderDetails(), orderWorkFlow.getOrderDescription());
        Assert.assertEquals(order.getOrderVisibility(), orderWorkFlow.orderVisibility.getText());
        Assert.assertTrue(newOrderPage.findUploadedFilesByXPathInPublished(getFileName(props.getProperty("FileForUpload"))).isDisplayed());
        Assert.assertEquals(order.getOrderCategoryOfWriting(), orderWorkFlow.getCategory());

        Assert.assertEquals(Integer.toString(
                        checkForWordsRequired(
                                props.getProperty("WordsRequired"),
                                props.getProperty("ArticlesQuantity"))),
                orderWorkFlow.getTotalWordsCount()
        );

        Assert.assertEquals(order.getOrderArticlesQuantity(), orderWorkFlow.getArticlesCount());
        Assert.assertTrue(compareExpertises(order.getOrderAvailebleExpertises(), orderWorkFlow.getExpertises()));

        orderWorkFlow.clickOnMyOrdersFromTopMenu();

        assertEquals(order.getOrderName(), myOrdersPage.getOrderNameFromOrdersTable(order.getOrderName()));
        assertEquals(order.getOrderStatus(), myOrdersPage.getOrderStatusFromOrdersTable(order.getOrderName()));
        assertEquals(order.getOrderSystemID(), myOrdersPage.getOrderIDFromOrdersTable(order.getOrderName()));

        myOrdersPage.getOrderFromOrdersTable(order.getOrderName()).click();

        CreateNewOrder.dateComparator(order, orderWorkFlow);
        Assert.assertEquals(orderWorkFlow.getOrderStatus(), "Published"                                                      );
        Assert.assertTrue(orderWorkFlow.getOrderStatusInfo().contains(props.getProperty("StatusInfo")));
        Assert.assertEquals(order.getOrderName(), orderWorkFlow.getOrderTitle()                                              );
        Assert.assertEquals(order.getOrderDetails(), orderWorkFlow.getOrderDescription());
        Assert.assertEquals(order.getOrderVisibility(), orderWorkFlow.orderVisibility.getText());
        Assert.assertTrue(newOrderPage.findUploadedFilesByXPathInPublished(getFileName(props.getProperty("FileForUpload"))).isDisplayed());
        Assert.assertEquals(order.getOrderCategoryOfWriting(), orderWorkFlow.getCategory());

        Assert.assertEquals(Integer.toString(
                        checkForWordsRequired(
                                props.getProperty("WordsRequired"),
                                props.getProperty("ArticlesQuantity"))),
                orderWorkFlow.getTotalWordsCount());

        Assert.assertEquals(order.getOrderArticlesQuantity(), orderWorkFlow.getArticlesCount());
        Assert.assertTrue(compareExpertises(order.getOrderAvailebleExpertises(), orderWorkFlow.getExpertises()));

        logOut(driver);
    }
}
