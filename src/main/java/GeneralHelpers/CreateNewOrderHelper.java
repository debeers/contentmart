package GeneralHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by CMG_TEST on 26.08.2015.
 */
public class CreateNewOrderHelper {





        public static void check(WebDriver driver, String pub, String id) {


        System.out.println("Total:  " + pub);
        List<WebElement> ordersID = driver.findElements(By.xpath(".//td[1]"));
        for (WebElement sp : ordersID) {
            if (sp.getText().equalsIgnoreCase(id)  ) {
                System.out.println("All ok, order created successfuly! STATUS: PUBLISHED ");
            }
        }


    }



    public static String randomID(){

       int id = (int) (Math.random()*1000);
        String str = String.valueOf(id);
        return str;
    }


    public static String getDay(){

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int i = calendar.get(calendar.DAY_OF_MONTH);
        if(i == 30 || i == 31){
            i = 2;
        }else if(i <=29){
            i+=1;
        }
        String day = Integer.toString(i);

        System.out.println(day);

        return day;
    }
}
