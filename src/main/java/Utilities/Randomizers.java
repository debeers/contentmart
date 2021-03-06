package Utilities;

import PageObjects.Client.NewOrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.getWebDriverLogs;


/**
 * Created by ilya on 28.08.2015.
 */
public class Randomizers {

    public static String setRandomUserNickName(String role) {
        if (role.equalsIgnoreCase("writer")) {
            return "WriterBOT-" + DateTimeUtils.getTimestamp();
        } else return "ClientBOT-" + DateTimeUtils.getTimestamp();
    }

    public static String createRandomUserEmail(){
        return  DateTimeUtils.getTimestamp() + "@testmail.com' ";
    }

    public static String createNewOrderName(){
        return "Automation order ID: " + DateTimeUtils.getTimestamp();
    }

    public static String randomTextGeneratorLength(int length) {

        Random ran = new Random();
        int top = length;
        char data = ' ';
        String dat = "";

        for (int i = 0; i <= top; i++) {
            data = (char) (ran.nextInt(25) + 97);
            dat = data + dat;
        }
        System.out.println(dat);

        return dat;
    }

    public static String setRandomDeadline(WebDriver driver) throws InterruptedException {

        NewOrderPage newOrderPage = new NewOrderPage(driver);
        $(newOrderPage.deadlineField).shouldBe(visible).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(newOrderPage.xDateBuilder(DateTimeUtils.getDay()))).click();
        newOrderPage.currentOrderTime.click();
        newOrderPage.orderDetailsField.click();

        return $(newOrderPage.deadlineField).shouldBe(visible).getAttribute("value");
    }

}
