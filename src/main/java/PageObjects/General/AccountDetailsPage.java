package PageObjects.General;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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
    public Select userState;

    @FindBy(id = "city")
    public Select userCity;

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

        return $(nickNameField).shouldBe(Condition.visible).getText();
    }

    public String getUserFirstName() {

        return $(firstNameField).shouldBe(Condition.visible).getSelectedValue();
    }

    public String getUserLastName() {

        return $(lastNameField).shouldBe(Condition.visible).getSelectedValue();
    }

    public String getUserPhone() {

        return $(phoneField).shouldBe(Condition.visible).getSelectedValue();
    }

    public String getUserPan() {

        return $(panField).shouldBe(Condition.visible).getSelectedValue();
    }

    public String getUserState() {

        return userState.getFirstSelectedOption().getText();
    }

    public String getUserCity() {

        return userCity.getFirstSelectedOption().getText();
    }

    public String getUserAdress() {

        return $(addressField).shouldBe(Condition.visible).getSelectedValue();
    }

    public String getUserZip() {

        return $(userZip).shouldBe(Condition.visible).getSelectedValue();
    }

    public String getUserBio() {

        return $(getUserBio()).shouldBe(Condition.visible).getSelectedValue();
    }


    public void setFirstNameField(String firstName) {

        $(firstNameField).shouldBe(Condition.visible).clear();
        $(firstNameField).shouldBe(Condition.visible).sendKeys(firstName);
    }

    public void setLastNameField(String lastName) {

        $(lastNameField).shouldBe(Condition.visible).clear();
        $(lastNameField).shouldBe(Condition.visible).sendKeys(lastName);
    }

    public void setPhoneField(String phone) {

        $(phoneField).shouldBe(Condition.visible).clear();
        $(phoneField).shouldBe(Condition.visible).sendKeys(phone);
    }

    public void setPanField(String pan) {

        $(panField).shouldBe(Condition.visible).clear();
        $(panField).shouldBe(Condition.visible).sendKeys(pan);
    }

    public void setStateField(String state) {

        userState.selectByValue(state);
    }

    public void setCityField(String city) {

        userCity.selectByValue(city);
    }

    public void setAddressField(String address) {

        $(addressField).shouldBe(Condition.visible).clear();
        $(addressField).shouldBe(Condition.visible).sendKeys(address);
    }

    public void setZipField(String zip) {

        $(userZip).shouldBe(Condition.visible).clear();
        $(userZip).shouldBe(Condition.visible).sendKeys(zip);
    }

    public void setBiographyField(String bio) {

        $(biographyField).shouldBe(Condition.visible).clear();
        $(biographyField).shouldBe(Condition.visible).sendKeys(bio);
    }

    public void clickOnSaveChangesButton() {

        $(saveChangesButton).shouldBe(Condition.visible).click();
        $(successSavedChangesMsg).should(Condition.appear);
    }

    public AccountDetailsPage(WebDriver driver) {

        super(driver);
    }

}
