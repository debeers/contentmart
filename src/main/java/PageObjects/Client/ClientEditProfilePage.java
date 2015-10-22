package PageObjects.Client;

import PageObjects.BirthdayDateInterface;
import PageObjects.General.LeftMenuGeneralPage;
import PageObjects.PageObjectWithImages;
import PageObjects.Writer.WriterProfilePage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 17.10.2015.
 */
public class ClientEditProfilePage extends LeftMenuGeneralPage implements PageObjectWithImages, BirthdayDateInterface {


    @FindBy(xpath = ".//button[contains(@class, 'new_blue_but m_r-10')]")
    public WebElement saveChangesButton;

    @FindBy(xpath = ".//fieldset/select[1]")
    public WebElement selectMonth;

    @FindBy(xpath = ".//fieldset/select[2]")
    public WebElement selectDay;

    @FindBy(xpath = ".//fieldset/select[3]")
    public WebElement selectYear;

    @FindBy(id = "avatar_photo")
    public WebElement avatarPhoto;

    @FindBy(xpath = ".//input[contains (@class, 'blue_but fl_r')]")
    public WebElement saveAvatarPhotoButton;

    @FindBy(className = "progress-bar")
    public WebElement avatarUploadingProgressBar;

    @FindBy(className = "percent")
    public WebElement avatarUploadingProgress;

    @FindBy(xpath = ".//img[@id = 'img_crop']")
    public WebElement avatarSrc;

    @FindBy(xpath = ".//div[contains (@class, 'jcrop-holder')]")
    public WebElement avatarSrcHolder;


    @Override
    public int getImgHolderHeigh() {
        return avatarSrcHolder.getSize().getHeight();
    }

    @Override
    public int getImgHolderWidth() {
        return avatarSrcHolder.getSize().getWidth();
    }

    @Override
    public WebElement imgSrcElement(){
        return avatarSrc;
    }


    public ClientProfilePage clickOnSaveProfileChangesButton() {

        $(saveChangesButton).shouldBe(visible).click();
        ClientProfilePage clientProfilePage = new ClientProfilePage(driver);
        return clientProfilePage;
    }


    public Boolean avatarProgressCount() {
        if ($(avatarUploadingProgress).shouldBe(visible).has(text("100%"))) {
            return true;
        } else return false;
    }


    public void uploadNewAvatarPhoto(String path) {

        avatarPhoto.sendKeys(path);
    }


    public void saveAvatarPhotoButtonClick() {

        $(saveAvatarPhotoButton).shouldBe(Condition.visible).click();
    }


    public ClientEditProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getUserYearsOld() {
        return null;
    }

    @Override
    public WebElement selectDay() {
        return selectDay;
    }

    @Override
    public WebElement selectYear() {
        return selectYear;
    }

    @Override
    public WebElement selectMonth() {
        return selectMonth;
    }
}
