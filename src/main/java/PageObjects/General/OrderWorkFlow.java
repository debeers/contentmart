package PageObjects.General;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 19.11.2015.
 */
public class OrderWorkFlow {

    @FindBy(xpath = ".//span[contains(@class, 'name tooltip-item')]")
    public WebElement statusLable;

    @FindBy(xpath = ".//div[contains(@class, 'order-status-info')]")
    public WebElement orderStatusInfo;

    @FindBy(xpath = ".//*[@id='form-order-completed']/div[1]/a")
    public WebElement editOrder;

    @FindBy(xpath = ".//*[@id='form-order-completed']/div[2]/a")
    public WebElement closeOrder;

    @FindBy(xpath = ".//h3[contains(@class, 'order-title cell')]")
    public WebElement orderTitle;

    @FindBy(xpath = ".//span[contains(@class, 'date')]")
    public WebElement publicDate;

    @FindBy(xpath = ".//span[contains(@class, 'id')]/span")
    public WebElement orderID;

    @FindBy(xpath = ".//span[1][@class = 'price']")
    public WebElement orderPricePerOrderInRupee;

    @FindBy(xpath = ".//p[@class = 'price-per-order']//span[@class = 'price-usd']")
    public WebElement orderPricePerOrderInUSD;

    @FindBy(xpath = ".//span[@class = 'price-per-word small-text']//span[@class = 'price small-text']")
    public WebElement orderWordsCount;

    @FindBy(xpath = ".//span[@class = 'price-per-word small-text']//span[@class = 'price-usd']")
    public WebElement orderPricePerWordInUSD;

    @FindBy(xpath = ".//div[@class = 'item item-date d_in']//div[@class = 'd_in']/p")
    public WebElement orderDeadline;

    @FindBy(xpath = ".//p[@class = 'description cell']")
    public WebElement orderDescriptionField;

    @FindBy(xpath = ".//p[contains(text(), 'Category')]/following-sibling::span[@class = 'bold']")
    public WebElement chosenCategory;

    @FindBy(xpath = ".//p[contains(text(), 'Required expertises')]/following-sibling::span[@class = 'bold']")
    public WebElement chosenExpertises;

    @FindBy(xpath = ".//p[contains(text(), 'Shared')]/following-sibling::div[@class = 'bold']")
    public WebElement orderVisibility;

//bids

    @FindBy(xpath = ".//*[@id='bids']/div/div/div[2]/p")
    public WebElement writerProposalText;

    @FindBy(xpath = ".//*[@class='bid-list-price-box bid-list-hint cell10 m_b-10']//span[@class='currency']")
    public WebElement writerProposalPrice;

    @FindBy(xpath = ".//*[@id='bids']//span[@class = 'currency-per-word cell m_t-10']/span")
    public WebElement writerProposalPricePerWord;

    @FindBy(xpath = ".//*[@id='bids']//span[@class = 'price-usd']/span")
    public WebElement writerProposalPricePerWordInUSD;

    @FindBy(xpath = ".//a[contains(text(), 'MESSAGE')]")
    public WebElement messageButtonInBids;

    @FindBy(xpath = ".//*[@class='favourite']")
    public WebElement favouriteLable;

    @FindBy(xpath = ".//a[contains(text(), 'SET AS WINNER AND START WORK')]")
    public WebElement setAsWinnerButton;

    @FindBy(xpath = ".//a[contains(text(), 'CANCEL')]")
    public WebElement cancelButton;

//Copywriter work on the project block

    @FindBy(xpath = ".//*[@class = 'name bold']")
    public WebElement writerNameField;

    @FindBy(xpath = ".//a[contains(text(), 'Portfolio')]")
    public WebElement writerPortfolioLink;

    @FindBy(xpath = ".//a[contains(text(), 'SEND A MESSAGE')]")
    public WebElement sendMessageToWriter;

    @FindBy(xpath = "Cancel chosen wordsmith")
    public WebElement cancelChosenWordsmith;

    @FindBy(xpath = ".//a[contains(text(), 'Publish')]")
    public WebElement publishButton;


    public String getOrderStatus(){
       return  $(statusLable).shouldBe(visible).getText();
    }

    public String getOrderStatusInfo(){
        return $(orderStatusInfo).shouldBe(visible).getText();
    }

    public String getOrderTitle(){
        return $(orderTitle).shouldBe(visible).getText();
    }

    public String getPublichDate(){
        return $(publicDate).shouldBe(visible).getText();
    }

    public String getOrderID(){
        return $(orderID).shouldBe(visible).getText();
    }

    public String getPricePerOrderInRupee(){
        return $(orderPricePerOrderInRupee).shouldBe(visible).getText();
    }

    public String getPricePerOrderInUSD(){
        return $(orderPricePerOrderInUSD).shouldBe(visible).getText();
    }

    public String getPricePerWordInUSD(){
        return $(orderPricePerWordInUSD).shouldBe(visible).getText();
    }

    public String getOrderDeadline(){
        return $(orderDeadline).shouldBe(visible).getText();
    }

    public String getTotalOrderWordsRequired(){
        return $(orderWordsCount).shouldBe(visible).getText();
    }

    public String getCategory(){
        return $(chosenCategory).shouldBe(visible).getText();
    }

    public String getExpertises(){
        return $(chosenExpertises).shouldBe(visible).getText();
    }

    public String getSharingStatus(){
        return $(orderVisibility).shouldBe(visible).getText();
    }

    public void clickOnEditOrderButton(){
        $(editOrder).shouldBe(visible).click();
    }

    public void clickOnCloseOrderButton(){
        $(closeOrder).shouldBe(visible).click();
    }

    public void clickOnPublishButton(){
        $(publishButton).shouldBe(visible).click();
    }




}
