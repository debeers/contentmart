package PageObjects.Landings;

import PageObjects.BasePageObject;
import PageObjects.General.RegistrationFormPage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 25.11.2015.
 */
public class ForWritersPage extends BasePageObject{

    @FindBy(xpath = ".//a[contains(text(), 'Register Now')]")
    public WebElement registerNowButton;


    public ForWritersPage goToForWritersLanding(String URL){
        driver.get(URL);
        $(registerNowButton).shouldBe(Condition.visible);
        return this;
    }

    public RegistrationFormPage clickOnRegisterNowButton(){
        registerNowButton.click();
        return new RegistrationFormPage(driver);
    }

    public ForWritersPage(WebDriver driver) {
        super(driver);
    }
}
