package PageObjects.Writer;

import PageObjects.General.LeftMenuGeneralPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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


    public WriterProfilePage clickOnSaveChangesButton() {

        $(saveChangesButton).shouldBe(Condition.visible).click();
        WriterProfilePage writerProfilePage = new WriterProfilePage(driver);
        return writerProfilePage;
    }


    public void clickOnRemoveSkill(String categoryName) {

        $(driver.findElement(By.xpath(".//div[contains(@class, 'cell test_not_passed_block skill-box')]//span[1][contains(text(), '" + categoryName + "')][not(contains(text(), 'English'))]/following-sibling::span"))).click(); // xxx remove
        System.out.println("Element found! Removing element from added list ====<");

    }


    public void clickOnAddSkill(String categoryName) {

        $(driver.findElement(By.xpath(".//div[contains(@class, 'cell without_test_block skill-box')]//span[contains(@class, 'add-skill move-next-skill-btn')]/span[contains(text(), '" + categoryName + "')][not(contains(text(), 'English'))]/preceding-sibling::span"))).shouldBe(Condition.visible).click(); // +++ add
        System.out.println("Element found! Adding element to added list ====>");

    }


    public List<String> getCategoriesNames(String category, String state) {

        String xStatePath = null;

        if (state == "add") {
            xStatePath = "/following-sibling::div[3]/ul/li/span/span[2][not(contains(text(), 'English'))]";

        } else if (state == "remove") {
            xStatePath = "/following-sibling::div[2]/ul/li/span[1][not(contains(text(), 'English'))]";

        }

        List<String> categoriesNamesArray = new ArrayList<>();

        ElementsCollection addedCategoriesList = $$(driver.findElements(By.xpath(
                ".//div[contains(text(), '" + category + "')]" + xStatePath)));  // .//div[contains(text(), 'Categories')]/following-sibling::div[3]/ul/li/span/span[2] for add
        for (WebElement element : addedCategoriesList) {

            if (element.getText() != null && !element.isDisplayed()) {
                System.out.println(element.getText());
                categoriesNamesArray.add(element.getText());
            }
        }
        return categoriesNamesArray;
    }


    public List<String> getCategoriesNames2(String category, String state) {

        String xStatePath = null;

        if (state == "add") {
            xStatePath = "/following-sibling::div[3]/ul/li";        // /span/span[2][not(contains(text(), 'English'))]";

        } else if (state == "remove") {
            xStatePath = "/following-sibling::div[2]/ul/li/span[1][not(contains(text(), 'English'))]";

        }

        List<String> categoriesNamesArray = new ArrayList<>();

        ElementsCollection addedCategoriesList = $$(driver.findElements(By.xpath(
                ".//div[contains(text(), '" + category + "')]" + xStatePath)));  // .//div[contains(text(), 'Categories')]/following-sibling::div[3]/ul/li/span/span[2] for add
        for (WebElement element : addedCategoriesList) {

            if (element.getText() != null && !element.isDisplayed()) {
                System.out.println(element.getText());
                categoriesNamesArray.add(element.getText());
            }
        }

        return categoriesNamesArray;
    }


    public void getSettetCategoriesNames() {
        ElementsCollection addedCategoriesList = $$(driver.findElements(By.xpath("" +
                ".//p[contains(text(), 'Ex pertises')]/following-sibling::ul/li/span[2]")));
    }

    public WriterEditProfilePage(WebDriver driver) {

        super(driver);
    }
}
