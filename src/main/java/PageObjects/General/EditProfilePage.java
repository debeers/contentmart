package PageObjects.General;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class EditProfilePage extends LeftMenuGeneralPage {




    @FindBy(id = "signature_image")
    public WebElement signatureImage;

    @FindBy(id = "avatar_photo")
    public WebElement photoLink;

    @FindBy(xpath = "//div[2]//form/div[2]/input")
    public WebElement saveCropedAvatarButton;

    @FindBy(xpath = "//form/div[2]/input")
    public WebElement saveCropedSignButton;

    @FindBy(xpath = "//form/div[1]/div[1]/div[2]/div[2]/div[1]")
    public WebElement avatarProgressBar;

    @FindBy(xpath = "//form/div[1]/div[15]/div[1]")
    public WebElement signProgressBar;

    @FindBy(xpath = "//form/div[1]/div[1]/div[2]/div[2]/div[2]")
    public WebElement avatarProgressBarStatus;

    @FindBy(xpath = "//form/div[1]/div[15]/div[2]")
    public WebElement signProgressBarStatus;


    @FindBy(id = "first_name")
    public WebElement firstNameField;

    @FindBy(id = "last_name")
    public WebElement lastNameField;

    @FindBy(xpath = ".//*[@id='birthday']/fieldset/select[1]")
    public WebElement monthBirthDateSelect;

    @FindBy(xpath = ".//*[@id='birthday']/fieldset/select[2]")
    public WebElement dayBirthDateSelect;

    @FindBy(xpath = ".//*[@id='birthday']/fieldset/select[3]")
    public WebElement yearBirthDateSelect;

    @FindBy(id = "phone")
    public WebElement phoneNumberField;

    @FindBy(id = "pan")
    public WebElement panField;

    @FindBy(id = "region")
    public WebElement stateRegionSelect;

    @FindBy(id = "city")
    public WebElement cityRegionSelect;

    @FindBy(id = "address")
    public WebElement adressField;

    @FindBy(id = "zip")
    public WebElement zipField;

    @FindBy(xpath = "//form/div[1]/div[13]/div[2]/span")
    public WebElement signatureImageWrapper;

    @FindBy(xpath = "//form/div[2]/button")
    public WebElement saveButton;

    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[2]/div[2]/form/div[2]/span")
    public WebElement messageAfterSaving;// Settings successfully saved


    public void uploadSign(String path) throws InterruptedException {

        signatureImage.sendKeys(path);
        Thread.sleep(2000);
    }

    public void waitForSignProgressBarAppear() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(signProgressBar));


    }



    public void clickOnsaveCropedSignButton() {

        $(saveCropedSignButton).shouldBe(present).click();
    }


    public void setNewProfilePhoto(String path) throws InterruptedException {

        $(photoLink).sendKeys(path);
        Thread.sleep(2000);
    }

    public void clickOnSaveCropedAvatarButton() {

        $(saveCropedAvatarButton).shouldBe(visible).click();

    }


    public void waitForAvatarProgressBar() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(avatarProgressBar));

    }


    public void clickOnSaveProfileButton() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(saveButton)).click();

    }

    public void waitForSettingsSuccessfullysavedAppear() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(messageAfterSaving));

    }

    public String getAvatarProgressBarStatus() {

        String res = $(avatarProgressBarStatus).shouldBe(visible).getText();
        return res;
    }


    public EditProfilePage(WebDriver driver) {

        super(driver);
    }


}