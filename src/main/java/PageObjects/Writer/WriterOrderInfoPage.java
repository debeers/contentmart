package PageObjects.Writer;

import PageObjects.General.LeftMenuGeneralPage;
import PageObjects.General.MyMessagesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.Thread.sleep;

/**
 * Created by test on 10.09.2015.
 */
public class WriterOrderInfoPage extends LeftMenuGeneralPage {



    @FindBy(xpath = "html/body/div[1]/div[3]/div/div/div[1]/div[2]/div/div/div")
    public WebElement CustomerName;

    @FindBy(xpath = "//a[contains(text(), 'Message')]")
    public WebElement dropTheClientMessageButton;

    @FindBy(xpath = "//a[contains(text(), 'Cancel your offer')")
    public WebElement cancelYourOfferLink;

    @FindBy(xpath = "//textarea[@id='description']")
    public WebElement offerDetails;

    @FindBy(xpath = "//button[@class='confirm']")
    public WebElement acceptDedlinePopup;

    @FindBy(xpath = "//a[contains(text(),'Save as draft')]")
    public WebElement saveAsDraftButton;


    public WriterOrderInfoPage(WebDriver driver) {

        super(driver);
    }

}
