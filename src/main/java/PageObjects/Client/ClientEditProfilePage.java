package PageObjects.Client;

import PageObjects.BirthdayDateInterface;
import PageObjects.General.TopMenuGeneralPage;
import PageObjects.PageObjectWithImages;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 17.10.2015.
 */
public class ClientEditProfilePage extends TopMenuGeneralPage implements PageObjectWithImages, BirthdayDateInterface {


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

    @FindBy(xpath = ".//*[@id='edit_profile_customer']/div[2]/div[1]")
    public WebElement userName;


    public String getUserName(){
        return $(userName).shouldBe(visible).getText();
    }

    @Override
    public URL getImageURL() {

        URL url = null;
        try {
            url = new URL(avatarSrc.getAttribute("src"));
            System.out.println("Current image URL: " + url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

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
