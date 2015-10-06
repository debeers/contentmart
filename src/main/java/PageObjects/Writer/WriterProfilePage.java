package PageObjects.Writer;

import PageObjects.General.LeftMenuGeneralPage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 30.09.2015.
 */
public class WriterProfilePage extends LeftMenuGeneralPage {



    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[2]/div[3]/a")
    public WebElement editProfileButton;






                        // Portfolio

    @FindBy(xpath = "/html/body/div/div[3]/div/div/div/div[5]/div[1]/div")
    public WebElement addPortfolioItemButton;

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

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////




                                     // Languages and Expertises block









    public WriterProfilePage(WebDriver driver) {
        super(driver);

    }


    public Boolean newPortfolioAppear(WebDriver driver, String head) {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement el = wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(
                        "//div[contains(@class, 'document_block profile_added_block')]/h4[contains(text(),'" +
                                head + "')]"))));
        if (el != null) {

            return true;
        }

        return false;
    }


    public void clickOnAddPortfolioButton() {

        $(addPortfolioItemButton).click();
        $WaitFor(
                enterPortfolioTitleField,
                enterPortfolioTextField,
                addWorkButton
        );

    }


    public void setPortfolioTitleField(String text) {

        $(enterPortfolioTitleField).sendKeys(text);

    }

    public void setPortfolioTextField(String text) {

        $(enterPortfolioTextField).sendKeys(text);

    }

    public void clickOnaAdWorkButton() {

        $(addWorkButton).click();

    }


    public void clickOnEditProfileButtonkButton() {

        editProfileButton.click();

    }


}
