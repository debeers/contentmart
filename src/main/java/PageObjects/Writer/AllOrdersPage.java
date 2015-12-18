package PageObjects.Writer;

import PageObjects.General.OrderWorkFlow;
import PageObjects.General.SearchEngine;
import PageObjects.General.TopMenuGeneralPage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 17.12.2015.
 */
public class AllOrdersPage extends TopMenuGeneralPage implements SearchEngine{

    @FindBy(xpath = ".//*[@class = 'js-bid-on-order green_but fl_r m_t-10']")
    WebElement bidButton;

    @FindBy(xpath = ".//*[@class = 'js-open-order blue cell']")
    WebElement orderName;

    public AllOrdersPage search(String searchCriteria){
        $(By.xpath(".//*[@id='text']")).shouldBe(Condition.visible).sendKeys(searchCriteria);
        $(By.xpath(".//*[@class='js-all-orders-search-button but_search']")).shouldBe(Condition.visible).click();
        return new AllOrdersPage(driver);
    }

    public OrderWorkFlow clickOnOrderBidButton(String orderID){
        $(By.xpath(".//*[@id='order_"+orderID+"']/div[4]/div[2]/button")).shouldBe(Condition.visible).click();
        return new OrderWorkFlow(driver);
    }

    public void clickOnOrderHeaderLink(String orderID){
        $(By.xpath(".//*[@id='order_"+orderID+"']/div[1]/a")).shouldBe(Condition.visible).click();
    }

    public AllOrdersPage(WebDriver driver) {
        super(driver);
    }
}
