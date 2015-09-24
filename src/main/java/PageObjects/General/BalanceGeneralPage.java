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


    public String xBlocking(String ID) {

        String xpath = "html/body//td[.//text()[contains(., '#";
        String t = ID + "')]]/preceding-sibling::td[1]";

        return xpath + t;
    }

    public String xBlockingAmount(String ID) {

        String xpath = "html/body//td[.//text()[contains(., ' #";
        String t = ID + "')]]/following-sibling::td[1][not(contains(@class, 'text-right grey_dark'))]";

        return xpath + t;
    }

    public String xBlockingBalance(String ID) {

        String xpath = "html/body//td[.//text()[contains(., ' #";
        String t = ID + "')]]/following-sibling::td[1][not(contains(@class, 'text-right grey_dark'))]/following-sibling::td[1]";

        return xpath + t;
    }

    public String xTransfer(String ID) {

        String xpath = "html/body//td[.//text()[contains(., ' #";
        String t = ID + "')]]/preceding-sibling::td[1][not(contains(text(), 'Blocking'))]";

        return xpath + t;
    }

    public String xTransferAmount(String ID) {

        String xpath = "html/body//td[.//text()[contains(., ' #";
        String t = ID + "')]]/following-sibling::td[1][not(contains(@class, 'text-right red'))]";

        return xpath + t;
    }

    public String xTransferBalance(String ID) {

        String xpath = "html/body//td[.//text()[contains(., ' #";
        String t = ID + "')]]/following-sibling::td[1][not(contains(@class, 'text-right red'))]/following-sibling::td[1]";

        return xpath + t;
    }

    public String getTransferBalance(String systemID) {

        String path = xTransferBalance(systemID);
        String blocking = driver.findElement(By.xpath(path)).getText();

        return blocking;

    }

    public String getBlokingMoneyByOrderId(String systemID) {

        String path = xBlocking(systemID);
        String blocking = driver.findElement(By.xpath(path)).getText();

        return blocking;

    }


    public String getBlokingAmountMoneyByOrderId(String systemID) {

        String amountPath = xBlockingAmount(systemID);
        String amount = driver.findElement(By.xpath(amountPath)).getText();

        return amount;

    }


    public String getBlockingMoneyBallance(String systemID) throws InterruptedException {

        String balancePath = xBlockingBalance(systemID);
        String balance = $(driver.findElement(By.xpath(balancePath))).shouldBe(visible).getText();

        return balance;

    }


    public String getMoneyTransferAmount(String systemID) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        String amountPath = xTransferAmount(systemID);
        String transfer = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(amountPath)))).getText();

        return transfer;

    }


    public String getMoneyBlockingBallance(String systemID) throws InterruptedException {

        String balancePath = xBlockingBalance(systemID);
        String balance = $(driver.findElement(By.xpath(balancePath))).shouldBe(visible).getText();

        return balance;

    }


    public BalanceGeneralPage(WebDriver driver) {

        super(driver);
    }
}
