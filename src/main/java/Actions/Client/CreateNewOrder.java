package Actions.Client;

import Entities.LoginObject;
import Entities.OrderObject;
import Utilities.DBUtill;
import PageObjects.Client.NewOrderPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderWorkFlow;
import Repository.UserModelRepo;
import Repository.OrderRepo;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static Actions.General.RegistrationAndLogin.loginAs;
import static junit.framework.Assert.assertEquals;


/**
 * Created by DeBeers on 20.11.2015.
 */
public class CreateNewOrder {

    public static OrderObject clientGoToCreateNewOrder(WebDriver driver,
                                                         LoginObject clientLogin, Properties props)
            throws InterruptedException, IOException, SQLException, AWTException {

        OrderObject order = new OrderObject();

        setUserCurrencyToRupee(props);

        MyOrdersPage myOrdersPage = loginAs(driver, clientLogin);
        NewOrderPage newOrderPage = myOrdersPage.clickOnNewOrderFromTopMenu();
        newOrderPage.setOrder(order, props);
        OrderWorkFlow orderWorkFlow = newOrderPage.clickOnPublishButton();
        Thread.sleep(1500); //server side wait

        order.setOrderSystemID(orderWorkFlow.getOrderID());
        order.setOrderPublicDate(orderWorkFlow.getPublichDate());
        order.setOrderStatus(orderWorkFlow.getOrderStatus());

        return order;
    }

    public static void setUserCurrencyToRupee(Properties props) throws IOException, SQLException {
        if(UserModelRepo.getUserCurrencyID(
                        props.getProperty("UserEmail")) != null){
            setUserCurrencyToUSD(props.getProperty("UserEmail"));
        }
    }

    public static java.util.List<String> getExpertisesList() throws IOException, SQLException {

        java.util.List<String> orderExpertise = new ArrayList<>();
        ResultSet res = OrderRepo.getOrderExpertises();
        while(res.next()){
            orderExpertise.add(res.getString("NAME"));
            System.out.println(res.getString("NAME"));
        }
        return orderExpertise;
    }

    public static java.util.List<String> getLanguageList() throws IOException, SQLException {

        java.util.List<String> orderLanguages = new ArrayList<>();
        ResultSet res = OrderRepo.getOrderLanguages();
        while(res.next()){
            orderLanguages.add(res.getString("name"));
        }
        return orderLanguages;
    }

    public static java.util.List<String> getCategoriesList() throws IOException, SQLException {

        java.util.List<String> orderCategories = new ArrayList<>();
        ResultSet res = OrderRepo.getOrderCategories();
        while(res.next()){
            orderCategories.add(res.getString("name"));
        }
        return orderCategories;
    }

    public static void setUserCurrencyToUSD(String mail) throws IOException, SQLException {
        DBUtill dbUtill = new DBUtill();
         dbUtill.executeUpdate(UserModelRepo.setUserCurrencyToRupee(mail));
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
