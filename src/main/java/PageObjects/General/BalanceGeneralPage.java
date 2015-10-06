package PageObjects.General;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 11.09.2015.
 */
public class BalanceGeneralPage extends LeftMenuGeneralPage {



    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[2]/p")
    public WebElement availebleBalance;


    @FindBy(xpath = "//div/div[3]/ul/li[7]/a/span")
    public  WebElement clientBallanceFromLeftMenu;





//////////////////////////////////////////////    Blocking


    public String xBlockingStatus(String ID) {

        String xpath = "html/body//td[.//text()[contains(., '#";
        String t = ID + "')]]/preceding-sibling::td[1][.//text()[contains(., 'Blocking')]]";

        return $(driver.findElement(By.xpath(xpath + t))).shouldBe(visible).getText();
    }

    public String xBlockingAmount(String ID) {

        String xpath = "html/body//td[.//text()[contains(., ' #";
        String t = ID + "')]]/preceding-sibling::td[1][.//text()[contains(., 'Blocking')]]/following-sibling::td[1]/following-sibling::td[1]";

        return $(driver.findElement(By.xpath(xpath + t))).shouldBe(visible).getText();
    }

    public String xBlockingBalance(String ID) {

        String xpath = "html/body//td[.//text()[contains(., ' #";
        String t = ID + "')]]/preceding-sibling::td[1][.//text()[contains(., 'Blocking')]]/following-sibling::td[1]/following-sibling::td[1]/following-sibling::td[1]";

        return $(driver.findElement(By.xpath(xpath + t))).shouldBe(visible).getText();
    }



    ///////////////////////////////////////////    Unblocking


    public String xUnBlockingStatus(String ID) {

        String xpath = "html/body//td[.//text()[contains(., '#";
        String t = ID + "')]]/preceding-sibling::td[1][.//text()[contains(., 'Unblocking')]]";

        return $(driver.findElement(By.xpath(xpath + t))).shouldBe(visible).getText();
    }

    public String xUnBlockingAmount(String ID) {

        String xpath = "html/body//td[.//text()[contains(., '#";
        String t = ID + "')]]/preceding-sibling::td[1][.//text()[contains(., 'Unblocking')]]/following-sibling::td[2]";

        return $(driver.findElement(By.xpath(xpath + t))).shouldBe(visible).getText();
    }

    public String xUnBlockingBallance(String ID) {

        String xpath = "html/body//td[.//text()[contains(., '#";
        String t = ID + "')]]/preceding-sibling::td[1][.//text()[contains(., 'Unblocking')]]/following-sibling::td[1]/following-sibling::td[1]/following-sibling::td[1]";

        return $(driver.findElement(By.xpath(xpath + t))).shouldBe(visible).getText();
    }



///////////////////////////////////////   Transfer



    public String xTransferStatus(String ID) {

        String xpath = "html/body//td[.//text()[contains(., ' #";
        String t = ID + "')]]/preceding-sibling::td[1][(contains(text(), 'Transfer'))]";

        return $(driver.findElement(By.xpath(xpath + t))).shouldBe(visible).getText();
    }

    public String xTransferAmount(String ID) {

        String xpath = "html/body//td[.//text()[contains(., ' #";
        String t = ID + "')]]/preceding-sibling::td[1][(contains(text(), 'Transfer'))]/following-sibling::td[1]/following-sibling::td[1]";

        return $(driver.findElement(By.xpath(xpath + t))).shouldBe(visible).getText();
    }

    public String xTransferBalance(String ID) {

        String xpath = "html/body//td[.//text()[contains(., ' #";
        String t = ID + "')]]/preceding-sibling::td[1][(contains(text(), 'Transfer'))]/following-sibling::td[1]/following-sibling::td[1]/following-sibling::td[1]";

        return $(driver.findElement(By.xpath(xpath + t))).shouldBe(visible).getText();
    }






    public BalanceGeneralPage(WebDriver driver) {

        super(driver);
    }
}
