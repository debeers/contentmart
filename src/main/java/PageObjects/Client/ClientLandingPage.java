package PageObjects.Client;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 27.11.2015.
 */
public class ClientLandingPage {



    @FindBy(xpath = ".//div[@class = 'box-in']/a[contains(text(), 'Register as a Client')]")
    public WebElement registerAsClientButtonTop;

    @FindBy(xpath = ".//*[@id='nick_name']")
    public WebElement nickNameField;

    @FindBy(xpath = ".//*[@id='email']")
    public WebElement emailField;

    @FindBy(xpath = ".//*[@id='password']")
    public WebElement passwordField;

    @FindBy(xpath = ".//*[@id='registration']/div[5]/button")
    public WebElement submitButton;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/h1")
    public WebElement successMessageAfterSubmitRegistration;


    public void clickOnregisterAsClientButtonTop(){
        $(registerAsClientButtonTop).shouldBe(Condition.visible).click();
    }

    public String enterNickName(String nickname){

        $(nickNameField).shouldBe(Condition.visible).clear();
        $(nickNameField).shouldBe(Condition.visible).sendKeys(nickname);
        return $(nickNameField).shouldBe(Condition.visible).getAttribute("value");
    }

    public String enterEmail(String email){

        $(emailField).shouldBe(Condition.visible).clear();
        $(emailField).shouldBe(Condition.visible).sendKeys(email);
        return $(emailField).shouldBe(Condition.visible).getAttribute("value");
    }

    public void enterPassword(String password){

        $(emailField).shouldBe(Condition.visible).clear();
        $(emailField).shouldBe(Condition.visible).sendKeys(password);
    }

    public void clickOnSubmitButton(){

        $(submitButton).shouldBe(Condition.visible).click();
        $(successMessageAfterSubmitRegistration).should(Condition.appear);
    }


}
