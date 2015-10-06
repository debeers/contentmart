package GeneralHelpers;

import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.HttpURLConnection;
import java.net.URL;

import static Tests.BaseTest.wait;
import static com.codeborne.selenide.Selenide.$;


/**
 * Created by ilya on 28.08.2015.
 */
public class GeneralHelpers {


    public static void findCreatedOrderAndClickOnIt(WebDriver driver, OrderObject order) {

        OrderInfoAndActions orderInfoAndActions = new OrderInfoAndActions(driver);
        String orderXPath = orderInfoAndActions.xOrder(order.getEntityOrderName());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(orderXPath))).click();

    }


    public static void jsDeleteClasses(String xpath, WebDriver driver) throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "document.evaluate(\"" + xpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null)" +
                ".singleNodeValue.setAttribute('class', '');";
        js.executeScript(script);

    }


    public static void uploadFileToHidenInput(WebDriver driver, String filepath) throws InterruptedException {

        String xPath = ".//*[@id='fileupload']";
        jsDeleteClasses(xPath, driver);
        $(By.xpath(xPath)).shouldBe(Condition.visible).sendKeys(filepath);

    }


    public static String getFileName(String path) {

        String resultStr = path.substring(path.indexOf("Resources") + 10, path.lastIndexOf('.'));
        System.out.println("You will upload file with name: " + resultStr);
        return resultStr;
    }


    public static boolean isFileExists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean entityAppear(String name){

    if(wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(text(),'" + name + "')]"))).isDisplayed()){
        return true;
    }
        return false;

    }


}
