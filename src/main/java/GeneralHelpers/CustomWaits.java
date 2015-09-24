package GeneralHelpers;


import PageObjects.Client.ClientNewOrderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by ilya on 09.09.2015.
 */
public class CustomWaits {


    public static void createNewOrderWaits(WebDriver driver, ClientNewOrderPage newOrder){
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.visibilityOf(newOrder.orderNameField));
        wait.until(ExpectedConditions.visibilityOf(newOrder.descriptionField));
        wait.until(ExpectedConditions.visibilityOf(newOrder.wordsRequiredField));
        wait.until(ExpectedConditions.visibilityOf(newOrder.priceField));
        wait.until(ExpectedConditions.elementToBeClickable(newOrder.publishButton));
        System.out.println("HAPPY HOUR`S in McDonald`s! All elements had been loaded successfully! Yuuuuuuupppiiiiii!)))))");

    }



}
