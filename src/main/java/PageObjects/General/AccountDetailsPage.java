package PageObjects.General;

import PageObjects.PageObjectWithImages;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;
import java.net.URL;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static GeneralHelpers.GeneralHelpers.jsDeleteClasses;
import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 13.10.2015.
 */
public class AccountDetailsPage extends LeftMenuGeneralPage implements PageObjectWithImages {


    @FindBy(xpath = "//a[contains(text(), 'Account details')]")
    public WebElement accountDetailsLink;

    @FindBy(xpath = "//a[contains(text(), 'Change password')]")
    public WebElement changePasswordLink;

    @FindBy(xpath = "//a[contains(text(), 'Email Notifications')]")
    public WebElement emailNotificationsLink;

    @FindBy(id = "nick_name")
    public WebElement nickNameField;

    @FindBy(id = "first_name")
    public WebElement firstNameField;

    @FindBy(id = "last_name")
    public WebElement lastNameField;

    @FindBy(id = "phone")
    public WebElement phoneField;

    @FindBy(id = "pan")
    public WebElement panField;

    @FindBy(id = "currency")
    public WebElement currencyField;

    @FindBy(id = "country")
    public WebElement countryField;

    @FindBy(id = "address")
    public WebElement addressField;

    @FindBy(id = "region")
    public WebElement userState;

    @FindBy(id = "city")
    public WebElement userCity;

    @FindBy(id = "timezone")
    public WebElement userTimeZone;

    @FindBy(id = "zip")
    public WebElement userZip;

    @FindBy(id = "biography")
    public WebElement biographyField;

    @FindBy(id = "signature_image")
    public WebElement signature;

    @FindBy(xpath = ".//button[contains(text(), 'Save Changes')]")
    public WebElement saveChangesButton;

    @FindBy(className = "text-success")
    public WebElement successSavedChangesMsg;

    @FindBy(xpath = ".//input[contains(@class, 'blue_but fl_r')]")
    public WebElement saveSignatureButton;

    @FindBy(id = "signature_image_crop")
    public WebElement signatureSrc;

    @FindBy(xpath = ".//div[contains (@class, 'jcrop-holder')]")
    public WebElement signatureSrcHolder;

    @Override
    public URL getImageURL() {

        URL url = null;
        try {
            url = new URL(signatureSrc.getAttribute("src"));
            System.out.println("Current image URL: " + url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public int getImgHolderHeigh() {
        return signatureSrcHolder.getSize().getHeight();
    }
    @Override
    public int getImgHolderWidth() {
        return signatureSrcHolder.getSize().getWidth();
    }
    @Override
    public WebElement imgSrcElement(){
        return signatureSrc;
    }


    public EmailNotificationsPage clickOnEmailNotificationsLink(WebDriver driver) {

        $WaitFor(emailNotificationsLink).click();
        waitForPageLoad(driver);
        EmailNotificationsPage emailNotificationsPage = new EmailNotificationsPage(driver);
        return emailNotificationsPage;
    }

    public void clickOnSaveSignatureButton(){

        $(saveSignatureButton).shouldBe(Condition.visible).click();
    }

    public void uploadNewSignature(String path) throws InterruptedException {
        Thread.sleep(2000);
        jsDeleteClasses(".//*[@id='signature_image']", driver);
        signature.sendKeys(path);
    }

    public void clickOnChangePasswordLink() {

        $WaitFor(changePasswordLink).click();
    }

    public String getUserCountry() {

        return $(countryField).shouldBe(Condition.visible).getText();
    }

    public String getUserCurrency() {

        return $(currencyField).shouldBe(Condition.visible).getText();
    }

    public String getUserTimeZone() {

        return $(userTimeZone).shouldBe(Condition.visible).getText();
    }

    public String getUserNickName() {

        return $(nickNameField).shouldBe(Condition.visible).getAttribute("value");
    }

    public String getUserFirstName() {

        return $(firstNameField).shouldBe(Condition.visible).getAttribute("value");
    }

    public String getUserLastName() {

        return $(lastNameField).shouldBe(Condition.visible).getAttribute("value");
    }

    public String getUserPhone() {

        return $(phoneField).shouldBe(Condition.visible).getAttribute("value");
    }

    public String getUserPan() {

        return $(panField).shouldBe(Condition.visible).getAttribute("value");
    }

    public String getUserState() {

        return $(userState).shouldBe(Condition.visible).getText();
    }

    public String getUserCity() {

        return $(userCity).shouldBe(Condition.visible).getText();
    }

    public String getUserAdress() {

        return $(addressField).shouldBe(Condition.visible).getAttribute("value");
    }

    public String getUserZip() {

        return $(userZip).shouldBe(Condition.visible).getAttribute("value");
    }

    public String setFirstNameField(String firstName) throws InterruptedException {

        $(firstNameField).shouldBe(Condition.visible).clear();
        Thread.sleep(2000);
        $(firstNameField).shouldBe(Condition.visible).sendKeys(firstName);

        return firstName;
    }

    public String setLastNameField(String lastName) {

        $(lastNameField).shouldBe(Condition.visible).clear();
        $(lastNameField).shouldBe(Condition.visible).sendKeys(lastName);
        return lastName;
    }

    public String setPhoneField(String phone) throws InterruptedException {

        $(phoneField).shouldBe(Condition.visible).clear();
        $(phoneField).shouldBe(Condition.visible).sendKeys(phone);

        return phone;
    }

    public String setPanField(String pan) {

        $(panField).shouldBe(Condition.visible).clear();
        $(panField).shouldBe(Condition.visible).sendKeys(pan);

        return pan;
    }

    public void setStateField(String state) {

        $(userState).shouldBe(Condition.visible).sendKeys(state);
    }

    public void setCityField(String city) {

        $(userCity).shouldBe(Condition.visible).sendKeys(city);
    }

    public String setAddressField(String address) {

        $(addressField).shouldBe(Condition.visible).clear();
        $(addressField).shouldBe(Condition.visible).sendKeys(address);

        return address;
    }

    public String setZipField(String zip) {

        $(userZip).shouldBe(Condition.visible).clear();
        $(userZip).shouldBe(Condition.visible).sendKeys(zip);

        return zip;
    }

    public void clickOnSaveChangesButton() throws InterruptedException {

        $(saveChangesButton).shouldBe(Condition.visible).click();
        $(successSavedChangesMsg).should(Condition.appear);
    }

    public AccountDetailsPage(WebDriver driver) {

        super(driver);
    }

}
