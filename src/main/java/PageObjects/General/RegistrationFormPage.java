package PageObjects.General;

import PageObjects.BasePageObject;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 04.11.2015.
 */
public class RegistrationFormPage extends BasePageObject {



    @FindBy(xpath = ".//div/div[@class = 'cell cntr']/h1")
    public WebElement registrationHeader;

    @FindBy(xpath = "//h1[contains(text(), 'Register as a Writer')]")
    public WebElement writerHeader;

    @FindBy(xpath = "//h1[contains(text(), 'Register as a Client')]")
    public WebElement clientHeader;

    @FindBy(xpath = ".//*[@id='nick_name']")
    public WebElement nickNameFiled;

    @FindBy(xpath = ".//*[@id='email']")
    public WebElement emailFiled;

    @FindBy(xpath = ".//*[@id='password']")
    public WebElement passwordField;

    @FindBy(xpath = ".//*[@id='registration']/div[5]/button")
    public WebElement submitRegistrationButton;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/h1")
    public WebElement successMessageAfterSubmitRegistration;


    public String getHeader() {
        return $(registrationHeader).getText();
    }

    public void clickOnHeaderToDropWarnings() throws InterruptedException {
        $(registrationHeader).click();
        Thread.sleep(2000);
    }

    public void setUserNickName(String nickname) {

        $(nickNameFiled).shouldBe(Condition.visible).clear();
        $(nickNameFiled).shouldBe(Condition.visible).sendKeys(nickname);
    }


    public void setUserEmail(String email) {

        $(emailFiled).shouldBe(Condition.visible).clear();
        $(emailFiled).shouldBe(Condition.visible).sendKeys(email);
    }


    public void setUserPassword(String password) {

        $(passwordField).shouldBe(Condition.visible).clear();
        $(passwordField).shouldBe(Condition.visible).sendKeys(password);

    }

    public void clickOnRegisterButton(){

        $(submitRegistrationButton).click();
        $(successMessageAfterSubmitRegistration).should(Condition.appear);
    }

    public RegistrationFormPage register(String nickname, String email, String password) throws InterruptedException {
        setUserNickName(nickname);
        setUserEmail(email);
        setUserPassword(password);
        clickOnHeaderToDropWarnings();
        clickOnRegisterButton();
        return this;
    }

    public RegistrationFormPage(WebDriver driver) {
        super(driver);
    }
}
