package GeneralHelpers;


import PageObjects.Client.ClientNewOrderPage;
import Tests.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static GeneralHelpers.GeneralHelpers.isFileExists;
import static java.lang.System.out;

/**
 * Created by ilya on 09.09.2015.
 */
public class CustomWaits extends BaseTest{


    public static void createNewOrderWaits(ClientNewOrderPage newOrder){
        $WaitFor(newOrder.orderNameField,
                newOrder.descriptionField,
                newOrder.wordsRequiredField,
                newOrder.priceField,
                newOrder.publishButton);
        out.println("HAPPY HOUR`S in McDonald`s! All elements had been loaded successfully! Yuuuuuuupppiiiiii!)))))");

    }


    public static WebElement $WaitFor(WebElement... elements){
        WebDriverWait wait = new WebDriverWait(driver, 15);

        if (elements == null)
            out.print("No args=");

        for (WebElement j : elements){

            if (j.getTagName().equalsIgnoreCase("input")  || j.getTagName().equalsIgnoreCase("div") ||
                    j.getTagName().equalsIgnoreCase("p") || j.getTagName().equalsIgnoreCase("a") ||
                    j.getTagName().equalsIgnoreCase("span")){

                wait.until(ExpectedConditions.visibilityOf(j));
                out.println("Waiting for visibility of: " +"Tag name: " + j.getTagName() + " " + "with text: " + j.getText());
                return j;

            }else if(j.getTagName().equalsIgnoreCase("button") ){

                wait.until(ExpectedConditions.elementToBeClickable(j));
                out.println("Waiting for visibility of Button: " + j.getText());
                return j;

            }else if(j.getTagName().equalsIgnoreCase("a") ){
                wait.until(ExpectedConditions.visibilityOf(j));
                out.println("Waiting for visibility of Link: " + j.getText());

                if(isFileExists(j.getAttribute("href"))){
                    out.println("Link responce is 200, link is OK ");
                }else out.println("Link is broken, check it please");

                return j;
            }

        }


        return null;
    }



    public static String $WaitAndGetTextFrom(WebElement... elements){
        WebDriverWait wait = new WebDriverWait(driver, 15);

        if (elements == null)
            out.print("No args=");

        for (WebElement j : elements){

            if (j.getTagName().equalsIgnoreCase("input")  || j.getTagName().equalsIgnoreCase("div") ||
                    j.getTagName().equalsIgnoreCase("p") || j.getTagName().equalsIgnoreCase("a") ||
                    j.getTagName().equalsIgnoreCase("span")){


                String str = wait.until(ExpectedConditions.visibilityOf(j)).getText();
                out.println("Waiting for visibility of: " + "Tag name: " + j.getTagName() + " " + "with text: " + j.getText());
                return str;

            }else if(j.getTagName().equalsIgnoreCase("button")){

                String str = wait.until(ExpectedConditions.elementToBeClickable(j)).getText();
                out.println("Waiting for visibility of BUTTON: " + "Tag name: " + j.getTagName() + " " + "with text: " + j.getText());
                return str;
            }else return
                    "..:::Can`t get text from element:::..";

        }


        return null;
    }




}
