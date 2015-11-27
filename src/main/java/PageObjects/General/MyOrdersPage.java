package PageObjects.General;

import PageObjects.Client.NewOrderPage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 20.11.2015.
 */
public class MyOrdersPage extends TopMenuGeneralPage{


    @FindBy(xpath = "//h1[contains(text(), 'My Orders')]")
    public WebElement myOrdersTitle;

    //writer
    @FindBy(id="take-the-test-button")
    public WebElement takeTheTestNowButton;

    //client
    @FindBy(xpath = ".//h2[contains (text(), 'Post an order')]")
    public WebElement postAnOrderElement;

    @FindBy(xpath = ".//h2[contains (text(), 'Choose a writer')]")
    public WebElement chooseAwriterElement;

    @FindBy(xpath = ".//h2[contains (text(), 'Review Content')]")
    public WebElement reviewContentElement;

    @FindBy(xpath = ".//h2[contains (text(), 'Project complete')]")
    public WebElement projectCompleteElement;

    @FindBy(xpath = ".//a[contains (text(), 'New order')]")
    public WebElement newOrderButton;

    @FindBy(xpath = ".//div[@class = 'phone-fixed']")
    public WebElement phoneBlock;


    public String getProjectCompleteElementElement(){
        return $(projectCompleteElement).shouldBe(Condition.visible).getText().trim();
    }

    public String getReviewContentElementElement(){
        return $(reviewContentElement).shouldBe(Condition.visible).getText().trim();
    }

    public String getChooseAwriterElement(){
        return $(chooseAwriterElement).shouldBe(Condition.visible).getText().trim();
    }

    public String getPostAnOrderElementText(){
        return $(postAnOrderElement).shouldBe(Condition.visible).getText().trim();
    }

    public NewOrderPage clickOnNewOrderButton(){
        $(newOrderButton).shouldBe(Condition.visible).click();
        return new NewOrderPage(driver);
    }

    public String getMyOrdersH1(){
        return $(myOrdersTitle).shouldBe(Condition.visible).getText().trim();
    }

    public String getNumberFromThePhoneBlock(){
        return $(phoneBlock).shouldBe(Condition.visible).getText();
    }

    public MyOrdersPage(WebDriver driver) {
        super(driver);
    }
}
