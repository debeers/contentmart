package PageObjects.Client;

import PageObjects.BirthdayDateInterface;
import PageObjects.General.TopMenuGeneralPage;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 18.10.2015.
 */
public class ClientProfilePage extends TopMenuGeneralPage implements BirthdayDateInterface{

    @FindBy(xpath = ".//a[contains(@class, 'blue_border_button edit_button')]")
    public WebElement editClientProfileButton;
    // Portfolio

    @FindBy(xpath = ".//div[contains(@class, 'age d_in m_l-10')]")
    public WebElement clientYearsOld;

    @FindBy(xpath = ".//div[contains(@class, 'region d_in m_l-10')]")
    public WebElement clientCity;

    @FindBy(xpath = ".//div[contains(@class, 'city d_in')]")
    public WebElement clientRegion;

    @FindBy(xpath = ".//p[@class = 'user_name']")
    public WebElement clientName;

    @FindBy(xpath = ".//div[@class = 'phone-fixed']/p")
    public WebElement phoneBlock;

    public String getNumberFromThePhoneBlock(){
        return phoneBlock.getText();
    }

    public String getClientName(){
        return $(clientName).shouldBe(Condition.visible).getText();
    }

    public ClientEditProfilePage clickOnEditProfileButton() {

        $(editClientProfileButton).shouldBe(Condition.visible).click();
        ClientEditProfilePage clientEditProfilePage = new ClientEditProfilePage(driver);

        return clientEditProfilePage;
    }


    public ClientProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getUserYearsOld() {
        String year = clientYearsOld.getText();
        System.out.println(year.substring(0, year.lastIndexOf('y') - 1));
        return year.substring(0, year.lastIndexOf('y') - 1);
    }

    @Override
    public WebElement selectDay() {
        return null;
    }

    @Override
    public WebElement selectYear() {
        return null;
    }

    @Override
    public WebElement selectMonth() {
        return null;
    }

}
