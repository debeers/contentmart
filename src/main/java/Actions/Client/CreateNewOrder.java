package Actions.Client;

import Entities.OrderObject;
import GeneralHelpers.DBUtill;
import PageObjects.Client.NewOrderPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderWorkFlow;
import SQLRepo.General;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static GeneralHelpers.PropertiesLoader.propertyXMLoader;
import static SQLRepo.General.*;
import static junit.framework.Assert.assertEquals;


/**
 * Created by DeBeers on 20.11.2015.
 */
public class CreateNewOrder {

    public static OrderWorkFlow clientGoToCreateNewOrder(WebDriver driver, MyOrdersPage myOrdersPage,
                                                         String orderDataParameters, Properties properties)
            throws InterruptedException, IOException, SQLException, AWTException {

        String orderData  = orderDataParameters;
        Properties props  = propertyXMLoader(System.getProperty("user.dir") + properties);
        OrderObject order = new OrderObject();
        DBUtill dbUtill   = new DBUtill().initDBConnection();

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

    public static java.util.List<String> getExpertisesList(DBUtill dbUtill) throws IOException, SQLException {

        java.util.List<String> orderExpertise = new ArrayList<>();
        ResultSet res = dbUtill.getResultSet(getOrderExpertises());
        while(res.next()){
            orderExpertise.add(res.getString("NAME"));
            System.out.println(res.getString("NAME"));
        }
        return orderExpertise;
    }

    public static java.util.List<String> getLanguageList(DBUtill dbUtill) throws IOException, SQLException {

        java.util.List<String> orderLanguages = new ArrayList<>();
        ResultSet res = dbUtill.getResultSet(getOrderLanguages());
        while(res.next()){
            orderLanguages.add(res.getString("name"));
        }
        return orderLanguages;
    }

    public static java.util.List<String> getCategoriesList(DBUtill dbUtill) throws IOException, SQLException {

        java.util.List<String> orderCategories = new ArrayList<>();
        ResultSet res = dbUtill.getResultSet(getOrderCategories());
        while(res.next()){
            orderCategories.add(res.getString("name"));
        }
        return orderCategories;
    }

    public static void setUserCurrencyToUSD(DBUtill dbUtill, String mail) throws IOException, SQLException {
         dbUtill.executeUpdate(General.setUserCurrencyToRupee(mail));
    }

    public static String randomID() {

        int id = (int) (Math.random() * 1000);
        String str = String.valueOf(id);

        return str;
    }

    public static String getDay() {

        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_MONTH);
        if (i == 30 || i == 31) {
            i = 2;
        } else if (i <= 29) {
            i += 1;
        }
        return Integer.toString(i);
    }

    public static String createNewOrderName(){
        return "Automation order ID: " + randomID();
    }

    public static Boolean checkForINRcorrection(DBUtill dbUtill, String usd, String rupee) {

        double currate = Double.parseDouble(
                dbUtill.getColumn(
                        getLastUpdatedCurrencyRate(), "value").substring(0, 5));

        int us = Integer.parseInt(usd);
        double ruup = Double.parseDouble(rupee);

        if (currate * us == ruup) {
            return true;
        }
        return false;
    }

    public static String getSettedOrderValue(OrderObject orderObject ){
        if(orderObject.getOrderValueInDollars()!= null){
            return orderObject.getOrderValueInDollars();

        }else return orderObject.getOrderValueInRupee();
    }

    public static double checkForTotalPrice(String price, String wordsRequired, String articles, String pricePer){

        if(pricePer.equalsIgnoreCase("word"))
            return getTotalPriceForWords(price, wordsRequired, articles);
        else return Double.parseDouble(price);
    }

    public static double getTotalPriceForWords(String price, String wordsRequired, String articles){
        double res = Double.parseDouble(articles) *
                (Double.parseDouble(price) *
                        Double.parseDouble(wordsRequired));
        return res;
    }

    public static String getTotalPrice(String price, String wordsRequired, String articles){
        int res = Integer.parseInt(articles) *
                (Integer.parseInt(price) *
                        Integer.parseInt(wordsRequired));

        return Integer.toString(res);
    }

    public static void dateComparator(OrderObject order, OrderWorkFlow orderWorkFlow){

        Map<String, String> month = new HashMap<>();
        month.put("01", "Jan");
        month.put("02", "Feb");
        month.put("03", "Mar");
        month.put("04", "Apr");
        month.put("05", "May");
        month.put("06", "Jun");
        month.put("07", "Jul");
        month.put("08", "Aug");
        month.put("09", "Sep");
        month.put("10", "Oct");
        month.put("11", "Nov");
        month.put("12", "Dec");

        for (Map.Entry<String, String> entry : month.entrySet()) {

            if(order.getOrderDeadLine().substring(1, 2).equalsIgnoreCase(entry.getKey())){
                assertEquals(entry.getValue(), getMonthFromDeadline(orderWorkFlow.getOrderDeadline()));
            }
        }
        assertEquals(orderWorkFlow.getOrderDeadline().substring(12, 18).trim(), getTimeFromDeadline(order.getOrderDeadLine())) ;
    }

    public static String getMonthFromDeadline(String deadline){
        return deadline.substring(0, 3);
    }

    public static String getTimeFromDeadline(String deadline){
        return deadline.substring(11, 16);
    }

    public static String getCurrentUserTimezoneFromNewOrderPage(NewOrderPage newOrderPage){
        return newOrderPage.getCurrentTimezone().substring(0, 12);
    }
}
