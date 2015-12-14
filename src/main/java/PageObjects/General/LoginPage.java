package PageObjects.General;


import PageObjects.BasePageObject;
import Tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePageObject {

    @FindBy(xpath = ".//*[@id='copywriters']/div/div[2]/div/a")
    public WebElement RegisterLink;

    @FindBy(id = "header_login")
    public WebElement loginLink;

    @FindBy(id = "u_login")
    public WebElement loginField;

    @FindBy(name = "u_passwd")
    public WebElement passwordField;

    @FindBy(xpath = ".//*[@id='login_form']/button")
    public WebElement submitButton;

    @FindBy(css = ".fancybox-form.cell.login_block>h1")
    public WebElement welcomeLoginHeader;

    @FindBy(xpath = ".//a[2][contains(text(), 'Register')]")
    public WebElement registrationLinkNewUser;

    @FindBy(xpath = "//div[1]//div/div[1]/button")
    public WebElement asWriterButtonNewUser;

    @FindBy(xpath = "//*[@id='email']")
    public WebElement emailNewUser;

    @FindBy(xpath = "//*[@id='nick_name']")
    public WebElement nicknameFieldNewUser;

    @FindBy(xpath = "//*[@id='password']")
    public WebElement passwordFieldNewUser;

    @FindBy(xpath = "//*[@id='registration']/div[5]/button")
    public WebElement registerButtonNewUser;

    @FindBy(xpath = "//div[@class = 'cell6 writer_block']")
    public WebElement registerAsWriter;

    @FindBy(xpath = "//div[@class = 'cell6 client_block']")
    public WebElement registerAsClient;

    @FindBy(xpath = "//h1[contains(text(), 'Register as a Writer')]")
    public WebElement writerHeader;

    @FindBy(xpath = "//h1[contains(text(), 'Register as a Client')]")
    public WebElement clientHeader;


    public LoginPage goToLoginPage(WebDriver driver) {

        driver.get(BaseTest.baseUrl);
        LoginPage page = new LoginPage(driver);

        return page;
    }

    public LoginPage setLoginField(String clientLogin) {
        $(loginField).shouldBe(visible).sendKeys(clientLogin);
        return this;
    }

    public LoginPage setPasswordField(String clientPassword) {
        $(passwordField).shouldBe(visible).sendKeys(clientPassword);
        return this;
    }

    public MyOrdersPage submit() {

        $(submitButton).shouldBe(visible).click();
        MyOrdersPage page = new MyOrdersPage(driver);

        return page;
    }

    public void loginLinkClick() {

        $(loginLink).shouldBe(visible).click();
        $(loginField).shouldBe(visible);
        $(passwordField).shouldBe(visible);
    }

    public String welcomeLoginHeader() {

        String res = $(welcomeLoginHeader).getText();
        return res;
    }

    public void registrationLinkClick() throws InterruptedException {
        registrationLinkNewUser.click();
        $(registerAsClient).should(appear);
        $(registerAsWriter).should(appear);
    }

    public RegistrationFormPage clickOnRegisterAsWriter() {

        $(registerAsWriter).shouldBe(visible).click();
        RegistrationFormPage registrationFormPage = new RegistrationFormPage(driver);

        $WaitFor(
                registrationFormPage.writerHeader,
                registrationFormPage.nickNameFiled,
                registrationFormPage.emailFiled,
                registrationFormPage.passwordField,
                registrationFormPage.submitRegistrationButton
        );
        return registrationFormPage;
    }

    public RegistrationFormPage clickOnRegisterAsClient() {

        $(registerAsClient).shouldBe(visible).click();
        RegistrationFormPage registrationFormPage = new RegistrationFormPage(driver);

        $WaitFor(
                registrationFormPage.clientHeader,
                registrationFormPage.nickNameFiled,
                registrationFormPage.emailFiled,
                registrationFormPage.passwordField,
                registrationFormPage.submitRegistrationButton
        );
        return registrationFormPage;
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}