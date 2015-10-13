package PageObjects.General;

import GeneralHelpers.GeneralWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class PartnersPage extends LeftMenuGeneralPage {


    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[2]/form/div[1]/input[1]")
    public WebElement searchFieldPartners;

    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[2]/form/div/input[2]")
    public WebElement searchButtonPartners;

    @FindBy(xpath = "html/body/div/div[3]/div/div/div/ul/li[1]/a")
    public WebElement allCustomers;

    @FindBy(xpath = "html/body/div/div[3]/div/div/div/ul/li[2]/a")
    public WebElement favouritesCustomers;

    @FindBy(xpath = "html/body/div/div[3]/div/div/div/ul/li[3]/a")
    public WebElement blacklistedCustomers;

    @FindBy(xpath = "//form/div/input[1]")
    public WebElement searchField;

    @FindBy(xpath = "//form/div/input[2]")
    public WebElement searchButton;

    @FindBy(className = "//div[3]/div//div/ul/li[1]/a")
    public WebElement allWritersList;

    @FindBy(className = "//div[3]/div/div/div/ul/li[2]")
    public WebElement favouritesWritersList;

    @FindBy(className = "//div[3]/div//ul/li[3]/a")
    public WebElement blacklistedWritersList;


    public void searchBysearchFieldInPartnersPage(String searchText) {

        $(searchFieldPartners).shouldBe(visible).sendKeys(searchText);
        $(searchButtonPartners).shouldBe(present).click();
        GeneralWaits.waitForPageLoad(driver);
    }


    public PartnersPage(WebDriver driver) {

        super(driver);
    }

}
