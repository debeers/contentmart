package Actions.Writer;

import com.codeborne.selenide.Condition;
import com.google.common.collect.Sets;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;


/**
 * Created by DeBeers on 04.10.2015.
 */
public class WriterGoToEditProfile {


    protected static HashSet<String> languages =
            Sets.newHashSet(
                     "Kannada", "Gujarati",
                    "Marathi", "Bengali", "Malayalam",
                    "Telugu", "Hindi", "Tamil"
            );


    protected static HashSet<String> expertises =
            Sets.newHashSet(
                    "Real Estate", "Health & Care", "Business & Finance",
                    "Kids & Parenting", "Politics", "Sports",
                    "Entertainment & Lifestyle", "IT & Software",
                    "Jobs & Education", "Travel & Tourism", "Automotive"
            );


    protected static HashSet<String> categories =
            Sets.newHashSet(
                    "Copywriting / Web Content",
                    "Articles and Blog writing",
                    "Technical writing",
                    "Creative writing",
                    "Editing & Proof Writing"
            );







     // .//div[contains(text(), 'Languages')]/following-sibling::div[1]/ul/li    // � ����������� �������
    //  .//div[contains(text(), 'Languages')]/following-sibling::div[2]/ul/li    //  ����������� �����
    //  .//div[contains(text(), 'Languages')]/following-sibling::div[3]/ul/li  //�� ����������� �����


    //  .//div[contains(text(), 'Expertises')]/following-sibling::div[1]/ul/li    // � ����������� �������
    //  .//div[contains(text(), 'Expertises')]/following-sibling::div[2]/ul/li    //  ����������� ����������
    //  .//div[contains(text(), 'Expertises')]/following-sibling::div[3]/ul/li  //�� ����������� ����������


    //  .//div[contains(text(), 'Categories')]/following-sibling::div[1]/ul/li    // � ����������� �������
    //  .//div[contains(text(), 'Categories')]/following-sibling::div[2]/ul/li    //  ����������� ���������
    //  .//div[contains(text(), 'Categories')]/following-sibling::div[3]/ul/li  //�� ����������� ���������



    public static void compare(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        List<WebElement> passedTestLanguegeList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//div[contains(text(), 'Languages')]/following-sibling::div[1]/ul/li")));
        List<WebElement> addedLanguegesList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//div[contains(text(), 'Languages')]/following-sibling::div[2]/ul/li")));
        List<WebElement> notAddedLanguegesList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//div[contains(text(), 'Languages')]/following-sibling::div[3]/ul/li")));



        List<String> text = null;
       // List<WebElement> addedLanguegesList = driver.findElements(By.xpath(".//div[contains(text(), 'Languages')]/following-sibling::div[2]/ul/li/text()"));


        for(String r : languages){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//li[contains(text(), '" + r + "')]/span[contains(@class, 'close_test move-next-skill-btn')]"))).click();

            System.out.println("Element found... clicking");


            $(driver.findElement(By.xpath(".//span[contains(text(), '" + r + "')]/span[contains(@class, 'test_plus')] "))).should(Condition.appear);





        }


    }
}







//        for (WebElement el : addedLanguegesList){
//            System.out.println(el.getText());
//            if(languages.contains(el.getText())) {
//                text.add(el.getText());
//                for(String r : text){
//
//                    System.out.println(r.toString());
//                }
//
//                //el.click();
//            }else {
//                System.out.println("wrong");
//            }
//        }





//        for(WebElement el : addedLanguegesList){
//
//            if (languages.contains(el.getText())){
//
//               System.out.println("Language found: " + el.getText());
//
//           }else if(!languages.contains(el.getText())){
//
//
//Thread.sleep(3000);
//               $(el).parent().click();
//
//           }
//
//
//
//        }



