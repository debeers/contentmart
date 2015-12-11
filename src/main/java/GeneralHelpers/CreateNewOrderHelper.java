package GeneralHelpers;

import Entities.OrderObject;
import Entities.UserModel;
import PageObjects.Client.NewOrderPage;
import PageObjects.General.OrderWorkFlow;
import com.codeborne.selenide.Condition;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static SQLRepo.General.getLastUpdatedCurrencyRate;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 26.08.2015.
 */
public class CreateNewOrderHelper {

    public static String randomID() {

        int id = (int) (Math.random() * 1000);
        String str = String.valueOf(id);

        return str;
    }

    public static String getDay() {

        java.util.Calendar calendar = java.util.Calendar.getInstance();
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

    public static Boolean checkForRUPEEcor(DBUtill dbUtill, String usd, String rupee) {

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
                Assert.assertEquals(entry.getValue(), getMonthFromDeadline(orderWorkFlow.getOrderDeadline()));
            }
        }
        Assert.assertEquals(orderWorkFlow.getOrderDeadline().substring(12, 18).trim(), getTimeFromDeadline(order.getOrderDeadLine())) ;
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
