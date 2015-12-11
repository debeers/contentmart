package PageObjects.Landings;

import Actions.General.RegistrationAndLogin;
import PageObjects.BasePageObject;
import PageObjects.General.RegistrationFormPage;
import PageObjects.General.TopMenuGeneralPage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 25.11.2015.
 */
public class ForClientsPage extends BasePageObject {


    @FindBy(xpath = ".//*[@email='registration']/div[5]/button")
    public WebElement registerAsClientButton;

    @FindBy(xpath = ".//*[@email='nick_name']")
    public WebElement nickNameField;

    @FindBy(xpath = ".//*[@email='email']")
    public WebElement emailField;

    @FindBy(xpath = ".//*[@email='password']")
    public WebElement passwordField;

    public ForClientsPage(WebDriver driver) {
        super(driver);
    }

    public ForClientsPage goToForClientsLanding(String URL){

        driver.get(URL);
        $(nickNameField).shouldBe(Condition.visible);
        $(emailField).shouldBe(Condition.visible);
        $(passwordField).shouldBe(Condition.visible);
        $(registerAsClientButton).shouldBe(Condition.visible);

        return this;
    }

    public void enterNickName(String nick){
        nickNameField.sendKeys(nick);
    }

    public void enterEmail(String email){
        emailField.sendKeys(email);
    }

    public void enterPassword(String password){
        passwordField.sendKeys(password);
    }

    public RegistrationFormPage clickOnSubmitRegistrationButton() {

        registerAsClientButton.click();
        return new RegistrationFormPage(driver);
    }

    public RegistrationFormPage fillRegistrationFormFromLanding(String nick, String email, String password) throws InterruptedException {

        enterNickName(nick);
        enterEmail(email);
        enterPassword(password);
        Thread.sleep(3000);
        clickOnSubmitRegistrationButton();

        return new RegistrationFormPage(driver);
    }
}
