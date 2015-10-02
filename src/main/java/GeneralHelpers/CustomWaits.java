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
public class CustomWaits extends BaseTest{


    public static HashSet<String> getVisibilityOfElement() {

        return visibilityOfElement;
    }

    public static void setVisibilityOfElement(HashSet<String> visibilityOfElement) {
        CustomWaits.visibilityOfElement = visibilityOfElement;
    }

    public static HashSet<String> getElementToBeClickable() {

        return elementToBeClickable;
    }

    public static void setElementToBeClickable(HashSet<String> elementToBeClickable) {
        CustomWaits.elementToBeClickable = elementToBeClickable;
    }

    protected static HashSet<String> visibilityOfElement =
            Sets.newHashSet(
                    "a", "p", "div", "span",
                    "href", "input", "textarea"
            );


    protected static HashSet<String> elementToBeClickable =
            Sets.newHashSet(
                    "button"
            );







    public static void createNewOrderWaits(ClientNewOrderPage newOrder){
        $WaitFor(newOrder.orderNameField,
                newOrder.descriptionField,
                newOrder.wordsRequiredField,
                newOrder.priceField,
                newOrder.publishButton);
        out.println("HAPPY HOUR`S in McDonald`s! All elements had been loaded successfully! Yuuuuuuupppiiiiii!)))))");

    }


    public static WebElement $WaitFor(WebElement... elements) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        if (elements != null) {

            for (WebElement j : elements) {


               if (visibilityOfElement.contains(j.getTagName())) {

                    wait.until(ExpectedConditions.visibilityOf(j));
                    out.println("Waiting for visibility of: " + "Tag name: " + j.getTagName() + " " + "with text: " + j.getText());
                    return j;

                } else if (elementToBeClickable.contains(j.getTagName())) {

                    wait.until(ExpectedConditions.elementToBeClickable(j));
                    out.println("Waiting for visibility of Button: " + j.getText());
                    return j;

                } else if (j.getTagName().equalsIgnoreCase("a")) {
                    wait.until(ExpectedConditions.visibilityOf(j));
                    out.println("Waiting for visibility of Link: " + j.getText() + j.getAttribute("href"));

                    if (isFileExists(j.getAttribute("href"))) {
                        out.println("Link responce is 200, link is OK ");
                    } else out.println("Link is broken, check it please");

                    return j;
                }

            }
        }else out.print("No args=...");



        return null;
    }


    public static String $WaitAndGetTextFrom(WebElement... elements){
        WebDriverWait wait = new WebDriverWait(driver, 15);

        if (elements != null) {

            for (WebElement j : elements) {

                if (visibilityOfElement.contains(j.getTagName())){


                    String str = wait.until(ExpectedConditions.visibilityOf(j)).getText();
                    out.println("Waiting for visibility of: " + "Tag name: " + j.getTagName() + " " + "with text: " + j.getText());
                    return str;

                } else if (elementToBeClickable.contains(j.getTagName())) {

                    String str = wait.until(ExpectedConditions.elementToBeClickable(j)).getText();
                    out.println("Waiting for visibility of BUTTON: " + "Tag name: " + j.getTagName() + " " + "with text: " + j.getText());
                    return str;
                } else return
                        "..:::Can`t get text from element:::..";

            }

        }else out.print("No args=...");

        return null;
    }




}
