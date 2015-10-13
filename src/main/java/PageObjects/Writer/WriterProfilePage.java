package PageObjects.Writer;

import PageObjects.General.LeftMenuGeneralPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by CMG_TEST on 30.09.2015.
 */
public class WriterProfilePage extends LeftMenuGeneralPage {


    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[2]/div[3]/a")
    public WebElement editProfileButton;
                        // Portfolio

    @FindBy(xpath = ".//div[contains(@class, 'user_main_info')]//div[2][contains(@class, 'd_in m_r-10')]")
    public WebElement yearsOld;

    @FindBy(css = ".portfolio-item-delete-confirm-btn.new_blue_but")
    public WebElement confirmSweetAllertButton;

    @FindBy(xpath = ".//a[contains (text(), 'CANCEL')]")
    public WebElement cancelSweetAllertButton;

    @FindBy(xpath = ".//div[contains (@class, 'add_portfolio_item')]")
    public WebElement addPortfolioItemButton;

    @FindBy(className="fancybox-wrap fancybox-default fancybox-opened")
    public WebElement portfolioFrame;

    @FindBy(xpath = "//div/form/input[1]")
    public WebElement enterPortfolioTitleField;

    @FindBy(xpath = "//div/form/textarea")
    public WebElement enterPortfolioTextField;

    @FindBy(xpath = "//div/form/input[2]")
    public WebElement addWorkButton;

    @FindBy(className="fancybox_close_link")
    public WebElement closePortfolioFrameButton;

    @FindBy(xpath = "//a[.//text()[contains(., 'READ MORE')]]  [(contains(@class, 'open_link'))]")
    public WebElement readMorePortfolioLink;

    @FindBy(xpath = "//a[.//text()[contains(., 'READ MORE')]]  [(contains(@class, 'read_more_link'))]")
    public WebElement readMoreAboutLink;

    @FindBy(xpath = "//div[(contains(@class, 'document_block profile_added_block'))]//h4")
    public WebElement h4PortfolioBlockHeaders;

    @FindBy(xpath = ".//a[contains(text(), 'EDIT')]")
    public WebElement editPortfolioItemButton;

    @FindBy(xpath = ".//a[contains(text(), 'BACK TO PORTFOLIO')]")
    public WebElement backToPortfolioButton;

    @FindBy(xpath = ".//p[contains(@class, 'portfolio-item-text')]")
    public WebElement portfolioItemText;

    @FindBy(xpath = ".//h2[contains(@class, 'portfolio-item-title')]")
    public WebElement portfolioItemTitle;

    @FindBy(xpath = ".//a[contains(text(), 'SAVE')]")
    public WebElement portfolioItemSaveButton;

    @FindBy(xpath = ".//a[contains (@class, 'portfolio-item-delete-btn')]")
    public WebElement deletePortfolioItemButton;



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                     // Languages and Expertises block

    public String settetExpertisesClassName = "cell expertises m_b-20";
    public String settetLanguagesClassName = "cell languages m_b-20";
    public String settetCategoriesOfWritingClassName = "cell m_b-20";


    public WriterProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getYearsOld(){

        String year = yearsOld.getText();
        System.out.println(year.substring(0, year.lastIndexOf('y') - 1));
        return year.substring(0, year.lastIndexOf('y')-1);
    }


    public Boolean addedPortfolioItem(WebDriver driver, String header) {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement portfolioHeader = wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(
                        "//div[contains(@class, 'document_block profile_added_block')]/h4[contains(text(),'" +
                                header + "')]"))));
        if (portfolioHeader != null) {

            return true;
        }

        return false;
    }


    public void openAddedPortfolioItem(WebDriver driver, String header){

        if(addedPortfolioItem(driver, header)){

            $(driver.findElement(By.xpath(".//h4[contains (text(), '" + header + "')]/following-sibling::a[contains (text(), 'READ MORE')]"))).click();
            $WaitFor(
                    portfolioItemTitle,
                    portfolioItemText,
                    backToPortfolioButton,
                    editPortfolioItemButton
            );
        }
    }


    public void clickOnDeletePortfolioItemButton(String itemName) throws InterruptedException {

        $(By.xpath(".//h4[contains(text(), '"+ itemName +"')]/preceding-sibling::a[contains " +
                "(@class, 'portfolio-item-delete-btn')]")).shouldBe(Condition.visible).click();
        Thread.sleep(3000);
    }


    public String getPortfolioItemTitle(){

        return $(portfolioItemTitle).shouldBe(Condition.visible).getText();
    }

    public String getPortfolioItemText(){

        return $(portfolioItemText).shouldBe(Condition.visible).getText();
    }

    public void clickOnEditPortfolioItemButton(){

        $(editPortfolioItemButton).click();
        $(portfolioItemSaveButton).should(Condition.appear);
    }

    public void clickOnSavePortfolioItemButton(){

        $(portfolioItemSaveButton).click();
    }

    public void clickOnAddPortfolioButton() {

        $(addPortfolioItemButton).click();
        $WaitFor(
                enterPortfolioTitleField,
                enterPortfolioTextField,
                addWorkButton
        );
    }


    public void setPortfolioTitleField(String text) {

        $(enterPortfolioTitleField).sendKeys(text);
    }

    public void clearPortfolioTitleField() {

        $(enterPortfolioTitleField).clear();
    }

    public void setPortfolioTextField(String text) {

        $(enterPortfolioTextField).sendKeys(text);
    }

    public void clearPortfolioTextField() {

        $(enterPortfolioTextField).clear();
    }

    public void clickOnaAddWorkButton(String title) throws InterruptedException {

        $(addWorkButton).click();
        findPortfolioItem(title);

    }

    public void clickOnEditProfileButton() {

        $(editProfileButton).click();
    }

    public void clickOnBackToPortfolioButton() {

        $(backToPortfolioButton).click();
        $(backToPortfolioButton).should(Condition.disappear);
    }

    public String setCategoryBox(String category){

        String categoryBox = "";

        if(category == "Languages"){
            categoryBox = settetLanguagesClassName;
            return categoryBox;
        }
        else if(category == "Expertises"){
            categoryBox = settetExpertisesClassName;
            return categoryBox;
        }
        else if(category == "Categories"){
            categoryBox = settetCategoriesOfWritingClassName;
            return categoryBox;

        }else return null;
    }


    public List<String> getSettetCategoriesNames(String category) {
        String skillBox = setCategoryBox(category);
        ElementsCollection addedCategoriesList =
                $$(driver.findElements(By.xpath(".//div[contains(@class,'" + skillBox + "')]//following-sibling::ul/li//span[contains(@class,'skill-name')][not (contains (text(), 'English'))]")));

        List<String> addedSkillsList = new ArrayList<>();

        for (WebElement skillName : addedCategoriesList) {
            System.out.println(skillName.getText());
            addedSkillsList.add(skillName.getText());
        }
        return addedSkillsList;
    }


    public void editPortfolioItem(WebDriver driver, String title, String editTitle, String editText) {

        openAddedPortfolioItem(driver, title);
        clickOnEditPortfolioItemButton();
        clearPortfolioTitleField();
        clearPortfolioTextField();
        setPortfolioTitleField(editTitle);
        setPortfolioTextField(editText);
        clickOnSavePortfolioItemButton();

    }

    public void clickOnConfirmSweetAllertButton() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("document.getElementsByClassName('portfolio-item-delete-confirm-btn')[0].click();");
        $(cancelSweetAllertButton).should(Condition.disappear);
        Thread.sleep(5000);
    }


    public Boolean findPortfolioItem(String title) throws InterruptedException {
        Thread.sleep(5000);
        if( $(By.xpath("//div[(contains(@class, 'document_block profile_added_block'))]//h4[contains (text(), '"+ title +"')]")).isDisplayed() ){

            return true;
        }
        return false;
    }



}
