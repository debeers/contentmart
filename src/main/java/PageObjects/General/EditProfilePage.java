package PageObjects.General;

import GeneralHelpers.GeneralHelpers;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static com.codeborne.selenide.Condition.hasClass;
import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EditProfilePage extends LeftMenuGeneralPage {




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


                                   //Account details


    @FindBy(id = "nick_name")
    public WebElement nickNameField;

    @FindBy(id = "phone")
    public WebElement phoneField;

    @FindBy(id = "pan")
    public WebElement panField;

    @FindBy(id = "region")
    public WebElement stateSelect;

    @FindBy(id = "city")
    public WebElement citySelect;

    @FindBy(id = "address")
    public WebElement adressTextArea;

    @FindBy(id = "zip")
    public WebElement zipField;

    @FindBy(id = "biography")
    public WebElement biographyTextArea;

    @FindBy(id = "signature_image")
    public WebElement signatureInput;

    @FindBy(xpath = ".//*[@id='copywriter_details']/div[10]/div[2]/div[3]/a")
    public WebElement viewSingatureInInvoiceLink;



    //////////////////////////////////////////////////////////////////////////////////////

                                     //Password change

    @FindBy(id = "old_password")
    public WebElement oldPasswordInput;

    @FindBy(id = "new_password")
    public WebElement newPasswordInput;

    @FindBy(id = "repeat_password")
    public WebElement repeatPasswordInput;

    @FindBy(xpath = "//div[contains(text(), 'field is required.')]")
    public WebElement allerFieldIsRequired;


    //////////////////////////////////////////////////////////////////////////////////////

                                    //Email notifications

    @FindBy(xpath = ".//div[contains(@class, 'switch toggle-on')]")
    public List<WebElement> triggersON;

    @FindBy(xpath = ".//div[contains(@class, 'switch toggle-off')]")
    public List<WebElement> triggersOFF;

    @FindBy(xpath = ".//*[@id='email_notifications']/div[1]/div/div/div/div")
    public List<WebElement> triggers;

    //////////////////////////////////////////////////////////////////////////////////////




    public void clickOnEmailNotificationsLink(){

        $WaitFor(emailNotificationsLink).click();
        waitForPageLoad(driver);
    }

    public void clickOnChangePasswordLinkLink(){

        $WaitFor(changePasswordLink).click();

    }

    public void clickOnAccountDetailsLinkLinkLink(){

        $WaitFor(accountDetailsLink).click();

    }

    public void switchTriggersOFF(){

        if(triggers.size() != 0) {
            for (WebElement r : triggers) {

                if ($(r).getAttribute("class").contains("switch toggle-on")) {
                    $(r).click();

                }

            }
        }
    }

    public void switchTriggersON(){

        if(triggers.size() != 0) {
            for (WebElement r : triggers) {

                if ($(r).getAttribute("class").contains("switch toggle-off")) {
                    $(r).click();

                }

            }
        }
    }




    public EditProfilePage(WebDriver driver) {

        super(driver);
    }


}