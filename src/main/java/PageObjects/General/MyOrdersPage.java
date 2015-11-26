package PageObjects.General;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by DeBeers on 20.11.2015.
 */
public class MyOrdersPage extends TopMenuGeneralPage{

    @FindBy(id="take-the-test-button")
    public WebElement takeTheTestNowButton;

    @FindBy(xpath = "//h1[contains(text(), 'My Orders')]")
    public WebElement myOrdersTitle;




    public MyOrdersPage(WebDriver driver) {
        super(driver);
    }
}
