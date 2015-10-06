package GeneralHelpers;


import PageObjects.Client.ClientNewOrderPage;
import Tests.BaseTest;
import com.google.common.collect.Sets;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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


    public static void createNewOrderWaits(ClientNewOrderPage newOrder) {

        $WaitFor(
                newOrder.orderNameField,
                newOrder.descriptionField,
                newOrder.wordsRequiredField,
                newOrder.priceField,
                newOrder.publishButton
        );
        out.println("HAPPY HOUR`S in McDonald`s! All elements had been loaded successfully! Yuuuuuuupppiiiiii!)))))");

    }


    public static WebElement $WaitFor(WebElement... elements) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        if (elements != null) {

            for (WebElement webElement : elements) {


                if (elementsShouldBeVisible.contains(webElement.getTagName())) {

                    wait.until(ExpectedConditions.visibilityOf(webElement));
                    out.println("Waiting for visibility of: " + "Tag name: " + webElement.getTagName() + " " + "with text: " + webElement.getText());
                    return webElement;

                } else if (elementToBeClickable.contains(webElement.getTagName())) {

                    wait.until(ExpectedConditions.elementToBeClickable(webElement));
                    out.println("Waiting for visibility of Button: " + webElement.getText());
                    return webElement;

                } else if (webElement.getTagName().equalsIgnoreCase("a")) {
                    wait.until(ExpectedConditions.visibilityOf(webElement));
                    out.println("Waiting for visibility of Link: " + webElement.getText() + webElement.getAttribute("href"));

                    if (isFileExists(webElement.getAttribute("href"))) {
                        out.println("Link responce is 200, link is OK ");
                    } else out.println("Link is broken, check it please");

                    return webElement;
                }

            }
        } else out.print("No args=...");


        return null;
    }


    public static String $WaitAndGetTextFrom(WebElement... elements) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        if (elements != null) {

            for (WebElement webElement : elements) {

                if (elementsShouldBeVisible.contains(webElement.getTagName())) {


                    String str = wait.until(ExpectedConditions.visibilityOf(webElement)).getText();
                    out.println("Waiting for visibility of: " + "Tag name: " + webElement.getTagName() + " " + "with text: " + webElement.getText());
                    return str;

                } else if (elementToBeClickable.contains(webElement.getTagName())) {

                    String str = wait.until(ExpectedConditions.elementToBeClickable(webElement)).getText();
                    out.println("Waiting for visibility of BUTTON: " + "Tag name: " + webElement.getTagName() + " " + "with text: " + webElement.getText());
                    return str;
                } else return
                        "..:::Can`t get text from element:::..";

            }

        } else out.print("No args=...");

        return null;
    }


}
