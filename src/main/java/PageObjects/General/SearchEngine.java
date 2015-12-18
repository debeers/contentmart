package PageObjects.General;

import PageObjects.Writer.AllOrdersPage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 25.11.2015.
 */
public interface SearchEngine {

    default AllOrdersPage search(WebDriver driver, String searchCriteria){
        $(By.xpath(".//*[@id='text']")).shouldBe(Condition.visible).sendKeys(searchCriteria);
        $(By.xpath(".//*[@class='js-all-orders-search-button but_search']")).shouldBe(Condition.visible).click();
        return new AllOrdersPage(driver);
    }
}
