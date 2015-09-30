package PageObjects.Writer;

import PageObjects.General.LeftMenuGeneralPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by CMG_TEST on 30.09.2015.
 */
public class WriterProfilePage extends LeftMenuGeneralPage {




    @FindBy(xpath = "//div/svg")
    public WebElement addPortfolioButton;

    @FindBy(className="fancybox-wrap fancybox-default fancybox-opened")
    public WebElement portfolioFrame;

    @FindBy(xpath = "//div/form/input[1]")
    public WebElement enterPortfolioTitleField;

    @FindBy(xpath = "//div/form/textarea")
    public WebElement enterPortfolioTextField;

    @FindBy(xpath = "//div/form/input[2]")
    public WebElement addWorkButton;

    @FindBy(className="fancybox_close_link")
    public WebElement closePortfolioFrameButton;

    @FindBy(xpath = "//a[.//text()[contains(., 'READ MORE')]]  [(contains(@class, 'open_link'))]")
    public WebElement readMorePortfolioLink;

    @FindBy(xpath = "//a[.//text()[contains(., 'READ MORE')]]  [(contains(@class, 'read_more_link'))]")
    public WebElement readMoreAboutLink;

    @FindBy(xpath = "//div [(contains(@class, 'document_block profile_added_block'))]//h4")
    public WebElement h4PortfolioBlockHeaders;

    public WriterProfilePage(WebDriver driver) {
        super(driver);

    }




    public Boolean newPortfolioAppear(WebDriver driver, String head){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Boolean res = wait.until(ExpectedConditions.visibilityOf(h4PortfolioBlockHeaders)).getText().contains(head);

        return res;
    }


    public void clickOnAddPortfolioButton(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(addPortfolioButton)).click();
        wait.until(ExpectedConditions.visibilityOf(enterPortfolioTitleField));
        wait.until(ExpectedConditions.visibilityOf(enterPortfolioTextField));
        wait.until(ExpectedConditions.visibilityOf(addWorkButton));
    }



}
