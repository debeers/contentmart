package PageObjects.Writer;

import GeneralHelpers.GeneralWaits;
import PageObjects.General.LeftMenuGeneralPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by ilya on 28.08.2015.
 */
public class WriterAllOrdersPage extends LeftMenuGeneralPage {


    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[2]/form/div[1]/input[1]")
    public WebElement searchField;

    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[2]/form/div/input[2]")
    public WebElement searchButton;

    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[2]/form/div[2]/span")
    public WebElement stripe;


    public void searchBySearchEngineFromAllOrdersWriter(WebDriver driver, String createdOrderName) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.visibilityOf(searchField)).sendKeys(createdOrderName);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        GeneralWaits.waitForPageLoad(driver);

    }


    public WriterAllOrdersPage(WebDriver driver) {
        super(driver);

    }


}
