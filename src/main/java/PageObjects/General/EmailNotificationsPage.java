package PageObjects.General;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import static GeneralHelpers.CustomWaits.$WaitFor;
import static com.codeborne.selenide.Selenide.$;

public class EmailNotificationsPage extends TopMenuGeneralPage {

    //For All

    @FindBy(xpath = "//button[contains(text(), 'Save Changes)]")
    public WebElement saveChangesButton;
    /////////////////////////////////// LINKS BLOCK /////////////////////////////////////////////
    @FindBy(xpath = "//a[contains(text(), 'Account details')]")
    public WebElement accountDetailsLink;

    @FindBy(xpath = "//a[contains(text(), 'Change password')]")
    public WebElement changePasswordLink;

    @FindBy(xpath = "//a[contains(text(), 'Email Notifications')]")
    public WebElement emailNotificationsLink;
    ////////////////////////////////////////////////////////////////////////////////////////////
    //Password change

    @FindBy(id = "old_password")
    public WebElement oldPasswordInput;

    @FindBy(id = "new_password")
    public WebElement newPasswordInput;

    @FindBy(id = "repeat_password")
    public WebElement repeatPasswordInput;

    @FindBy(xpath = "//div[contains(text(), 'field is required.')]")
    public WebElement allerFieldIsRequired;

    //Email notifications

    @FindBy(xpath = ".//div[contains(@class, 'switch toggle-on')]")
    public List<WebElement> triggersON;

    @FindBy(xpath = ".//div[contains(@class, 'switch toggle-off')]")
    public List<WebElement> triggersOFF;

    @FindBy(xpath = ".//*[@id='email_notifications']/div[1]/div/div/div/div")
    public List<WebElement> triggers;
    //////////////////////////////////////////////////////////////////////////////////////

    public void clickOnEmailNotificationsLink() {

        $WaitFor(emailNotificationsLink).click();
    }

    public void clickOnChangePasswordLinkLink() {

        $WaitFor(changePasswordLink).click();

    }

    public void clickOnAccountDetailsLinkLinkLink() {
        $WaitFor(accountDetailsLink).click();
    }

    public void switchTriggersOFF() {

        if (triggers.size() != 0) {
            for (WebElement webElement : triggers) {

                if ($(webElement).getAttribute("class").contains("switch toggle-on")) {
                    $(webElement).click();
                }
            }
        }
    }

    public void switchTriggersON() {

        if (triggers.size() != 0) {
            for (WebElement webElement : triggers) {

                if ($(webElement).getAttribute("class").contains("switch toggle-off")) {
                    $(webElement).click();
                }
            }
        }
    }

    public EmailNotificationsPage(WebDriver driver) {

        super(driver);
    }
}