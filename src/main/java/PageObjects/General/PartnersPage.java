package PageObjects.General;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class PartnersPage extends TopMenuGeneralPage {


    @FindBy(xpath = "html/body/div[4]/div[1]/div/div/div[1]/form/div/input[1]")
    public WebElement searchFieldPartners;

    @FindBy(xpath = ".//input[@class = 'but_search']")
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
    }

    public Boolean searchBySearchEngine(String searchText) {

        searchBysearchFieldInPartnersPage(searchText);
        if($(By.linkText(searchText)).exists())
            return true;
        else
            return false;
    }

    public WebElement search(String searchText) {

        searchBysearchFieldInPartnersPage(searchText);
        WebElement el = $(By.xpath(".//*[contains(text(), '"+ searchText +"')]")) ;
        return  el;
    }

    public PartnersPage(WebDriver driver) {

        super(driver);
    }

}
