package PageObjects.Writer;

import PageObjects.General.LeftMenuGeneralPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by CMG_TEST on 08.10.2015.
 */
public class WriterEditProfilePage extends LeftMenuGeneralPage {


    @FindBy(xpath = "//button[contains(text(), 'SAVE CHANGES')]")
    public WebElement saveChangesButton;

    @FindBy(xpath = "//button[contains(text(), 'CANCEL')]")
    public WebElement cancelButton;

    @FindBy(xpath = "//form/div[5]/textarea")
    public WebElement aboutTextArea;



    public String languagesId = "language-box";
    public String expertisesId = "expertise-box";
    public String categoriesId = "category-box";


    public String notAddedSkillsArray(String skillBox){

        String str = ".//div[@id = '" + skillBox + "']/following-sibling::div[contains(@class,'cell without_test_block skill-box')]" +
                "/ul/li[not (contains(@class,'none'))]//span[contains(@class,'skill-name')]";
        return str;
    }


    public String addedSkillsArray(String skillBox){

        String str = ".//div[@id = '" + skillBox + "']/following-sibling::div[contains(@class,'cell test_not_passed_block skill-box')]" +
                "/ul/li[not (contains(@class,'none'))]//span[contains(@class,'skill-name')]";
        return str;
    }


    public WriterProfilePage clickOnSaveChangesButton() {

        $(saveChangesButton).shouldBe(visible).click();
        WriterProfilePage writerProfilePage = new WriterProfilePage(driver);
        return writerProfilePage;
    }


    public void clickOnRemoveSkill(String category, String skillName) {
        String skillBox = setCategoryBox(category);

        $(driver.findElement(By.xpath(".//div[@id = '" + skillBox + "']/following-sibling::div[contains(@class,'cell test_not_passed_block skill-box')]" +
                "/ul/li[not (contains(@class,'none'))]//span[contains(@class,'skill-name') and (contains (text(), '" + skillName + "'))]//following-sibling::span[contains(@class,'close_test move-next-skill-btn')]")))
                .shouldBe(visible).click(); // xxx remove
        System.out.println("Element found! Removing element from added list ====<");

    }


    public void clickOnAddSkill(String category, String skillName) {
        String skillBox = setCategoryBox(category);

        $(driver.findElement(By.xpath(".//div[@id = '" + skillBox + "']/following-sibling::div[contains(@class,'cell without_test_block skill-box')]" +
                "/ul/li[not (contains(@class,'none'))]//span[contains(@class,'skill-name') and (contains (text(), '" + skillName + "'))]//preceding-sibling::span[contains(@class,'test_plus')]")))
                .shouldBe(visible).click(); // +++ add
        System.out.println("Element found! Adding element to added list ====>");

    }


    public String setCategoryBox(String category){

        String categoryBox = "";

        if(category == "Languages"){
            categoryBox = languagesId;
            return categoryBox;
        }
        else if(category == "Expertises"){
            categoryBox = expertisesId;
            return categoryBox;
        }
        else if(category == "Categories"){
            categoryBox = categoriesId;
            return categoryBox;

        }else return null;
    }


    public List<String> getCategoriesNames(String category, String state) {

        String xStatePath = null;
        String categoryBox = setCategoryBox(category);

        if (state == "add") {

            xStatePath = notAddedSkillsArray(categoryBox);

        } else if (state == "remove") {

            xStatePath = addedSkillsArray(categoryBox);
        }

        List<String> categoriesNamesArray = new ArrayList<>();
        ElementsCollection addedCategoriesList = $$(driver.findElements(By.xpath(xStatePath)));

        for (WebElement element : addedCategoriesList) {
                System.out.println(element.getText());
                categoriesNamesArray.add(element.getText());
        }
        return categoriesNamesArray;
    }


    public void clear() {

        ElementsCollection removeList = $$(driver.findElements(By.xpath(".//li[not (contains(@class,'none'))]//span[contains(@class,'close_test move-next-skill-btn')]")));

        if (removeList != null) {
            for (WebElement remove : removeList) {

                $(remove).shouldBe(visible).click();
            }
            sleep(3000); //server side wait
        }else System.out.println("All list`s of skills are empty, we`re begin!");
    }

    public WriterEditProfilePage(WebDriver driver) {

        super(driver);
    }
}
