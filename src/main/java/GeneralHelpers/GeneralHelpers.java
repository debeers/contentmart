package GeneralHelpers;

import com.codeborne.selenide.Condition;
import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.internal.Streams;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static com.codeborne.selenide.Selenide.$;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;


/**
 * Created by ilya on 28.08.2015.
 */
public class GeneralHelpers {

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

        String resultStr = path.substring(path.indexOf("resources2") + 10, path.lastIndexOf('.'));
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


    public static Boolean isFileUploaded(String filePath, List<WebElement> elementList) {
        String fileName = getFileName(filePath);

        for (WebElement el : elementList) {

            if (el.getText().contains(fileName)) {

                System.out.println("File successfully found! " + el);
                return true;

            } else {

                System.out.println("File not found!!!");
                return false;
            }
        }
        return true;
    }


    public static void safeJavaScriptClick(WebDriver driver, WebElement element) throws Exception {
        try {
            if (element.isEnabled() && element.isDisplayed()) {
                System.out.println("Clicking on element with using java script click");

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                System.out.println("Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document "+ e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element was not found in DOM "+ e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to click on element "+ e.getStackTrace());
        }
    }


    public static List<String> hintSeeker(List<WebElement> hints){
        List<String> hintsText = new ArrayList<>();

        for (WebElement hintText : hints){
            hintsText.add(hintText.getAttribute("data-value"));
        }
        return hintsText;
    }


    public static Boolean hintComparator(List<WebElement> hints, List<String> matcher){
       return CollectionUtils.isEqualCollection(hintSeeker(hints), matcher);
    }

    public static String getSystemDate(){
        return new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
    }

    public static String getSystemTime_24(){
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public static String getSystemTime_AM_PM(){
        return new SimpleDateFormat("hh:mm a").format(Calendar.getInstance().getTime());
    }

    public static String setRandomUserNickName(String role){

        if(role.equalsIgnoreCase("writer")){
        return "WriterBOT-" + randomNumeric(4) + randomAlphabetic(3);
        }else return "ClientBOT-" + randomNumeric(4) + randomAlphabetic(3);
    }
}
