package GeneralHelpers;

import PageObjects.Client.NewOrderPage;
import Tests.BaseTest;
import com.google.common.collect.Sets;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.HashSet;

import static GeneralHelpers.GeneralHelpers.isFileExists;
import static java.lang.System.out;

/**
 * Created by ilya on 09.09.2015.
 */
public class CustomWaits extends BaseTest {


    public static HashSet<String> getElementsShouldBeVisible() {

        return elementsShouldBeVisible;
    }

    public static void setElementsShouldBeVisible(HashSet<String> elementsShouldBeVisible) {
        CustomWaits.elementsShouldBeVisible = elementsShouldBeVisible;
    }

    public static HashSet<String> getElementToBeClickable() {

        return elementToBeClickable;
    }

    public static void setElementToBeClickable(HashSet<String> elementToBeClickable) {
        CustomWaits.elementToBeClickable = elementToBeClickable;
    }

    protected static HashSet<String> elementsShouldBeVisible =
            Sets.newHashSet(
                    "a", "p", "div", "span",
                    "href", "input", "textarea"
            );


    protected static HashSet<String> elementToBeClickable =
            Sets.newHashSet(
                    "button"
            );


    public static void NewOrderWaits(NewOrderPage newOrder) {

        $WaitFor(
                newOrder.orderNameField,
                newOrder.orderDetailsField,
                newOrder.wordsRequired,
                newOrder.priceInRupeeField,
                newOrder.publishButton
        );
    }


    public static WebElement $WaitFor(WebElement... elements) {

        if (elements != null) {

            for (WebElement webElement : elements) {

                if (elementsShouldBeVisible.contains(webElement.getTagName())) {

                    WebElement existedElement = $waitForVisibilityOfElement(webElement);
                    return existedElement;

                } else if (elementToBeClickable.contains(webElement.getTagName())) {

                    WebElement existedElement = $waitForElementToBeClickable(webElement);
                    return existedElement;

                } else if (webElement.getTagName().equalsIgnoreCase("a")) {

                    WebElement existedElement = $waitForLink(webElement);
                    return existedElement;
                }
            }
        } else out.print("No args=...");

        return null;
    }


    public static WebElement $waitForVisibilityOfElement(WebElement webElement) {

        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    public static WebElement $waitForElementToBeClickable(WebElement webElement) {

        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }

    public static WebElement $waitForLink(WebElement webElement) {

        wait.until(ExpectedConditions.visibilityOf(webElement));
        out.println("Waiting for visibility of Link: " + webElement.getText() + webElement.getAttribute("href"));

        if (isFileExists(webElement.getAttribute("href"))) {
            out.println("Link responce is 200, link is OK ");
        }

        return webElement;
    }

}
