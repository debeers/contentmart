package GeneralHelpers;

import Tests.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


/**
 * Created by CMG_TEST on 27.08.2015.
 */
public class GeneralWaits extends BaseTest {


    public static void waitForPageLoad(WebDriver driver) {

        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(driver1 -> {
            System.out.println("Current Window State       : "
                    + String.valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState")));
            return String
                    .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                    .equals("complete");
        });
    }


    public static void waitForTableLoad(List<WebElement> table) {

        wait.until(ExpectedConditions.visibilityOfAllElements(table));
        for (WebElement tr : table) {

            System.out.println(tr.toString());

        }
    }

}