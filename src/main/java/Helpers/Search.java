package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by DeBeers on 03.10.2015.
 */
public class Search {

    public static String findMessage(WebDriver driver, String toFind) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        String str = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[contains(text(),'" + toFind + "')]"))).getText();

        return str;
    }
}
