package Actions.Writer;

import PageObjects.Writer.WriterEditProfilePage;
import org.openqa.selenium.WebDriver;

import java.util.List;


/**
 * Created by DeBeers on 04.10.2015.
 */
public class WriterGoToEditProfile {

    public static List<String> setupSkills(WebDriver driver, String category, String action) throws InterruptedException {

        WriterEditProfilePage writerEditProfilePage = new WriterEditProfilePage(driver);
        List<String> categoryArray = writerEditProfilePage.getCategoriesNames(category, action);

        for (String categoryName : categoryArray) {

            if (action == "remove") {

                writerEditProfilePage.clickOnRemoveSkill(categoryName);

            } else if (action == "add") {

                writerEditProfilePage.clickOnAddSkill(categoryName);

            }
        }
        return categoryArray;
    }
}