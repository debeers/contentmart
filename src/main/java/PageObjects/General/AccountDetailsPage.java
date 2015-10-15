package PageObjects.General;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 13.10.2015.
 */
public class AccountDetailsPage extends LeftMenuGeneralPage{


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

    @FindBy(id = "address")
    public WebElement addressField;

    @FindBy(id = "region")
    public WebElement userState;

    @FindBy(id = "city")
    public WebElement userCity;

    @FindBy(id = "zip")
    public WebElement userZip;

    @FindBy(id = "biography")
    public WebElement biographyField;

    @FindBy(xpath = "//button[contains(text(), 'Save Changes')]")
    public WebElement saveChangesButton;

    @FindBy(className = "text-success")
    public WebElement successSavedChangesMsg;


    public EmailNotificationsPage clickOnEmailNotificationsLink(WebDriver driver) {

        $WaitFor(emailNotificationsLink).click();
        waitForPageLoad(driver);
        EmailNotificationsPage emailNotificationsPage = new EmailNotificationsPage(driver);
        return emailNotificationsPage;
    }

    public void clickOnChangePasswordLinkLink() {

        $WaitFor(changePasswordLink).click();
    }

    public void clickOnAccountDetailsLinkLinkLink() {

        $WaitFor(accountDetailsLink).click();
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

    public String getUserBio() {

        return $(biographyField).shouldBe(Condition.visible).getAttribute("value");
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

    public String setBiographyField(String bio) {

        $(biographyField).shouldBe(Condition.visible).clear();
        $(biographyField).shouldBe(Condition.visible).sendKeys(bio);

        return bio;
    }

    public void clickOnSaveChangesButton() {

        $(saveChangesButton).shouldBe(Condition.visible).click();
        $(successSavedChangesMsg).should(Condition.appear);
    }

    public AccountDetailsPage(WebDriver driver) {

        super(driver);
    }

}
