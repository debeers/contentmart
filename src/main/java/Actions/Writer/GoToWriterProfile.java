package Actions.Writer;

import Actions.General.RegistrationAndLogin;
import Entities.LoginObject;
import PageObjects.General.MyOrdersPage;
import PageObjects.Writer.WriterProfilePage;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static GeneralHelpers.Messages.randomMessageGeneratorLength;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by DeBeers on 04.10.2015.
 */
public class GoToWriterProfile {


    public static WriterProfilePage goToMyProfile(WebDriver driver, LoginObject writerLogin) {

        MyOrdersPage myOrdersPage = RegistrationAndLogin.loginAs(driver, writerLogin);
        myOrdersPage.clickOnProfileLeftMenu();
        myOrdersPage.clickOnMyProfileLeftMenuLeftMenu();
        WriterProfilePage writerProfilePage = new WriterProfilePage(driver);

        return writerProfilePage;
    }


    public static void addNewPortfolioItem(WriterProfilePage writerProfilePage, String title, int textLength) throws InterruptedException {

        writerProfilePage.clickOnAddPortfolioButton();
        writerProfilePage.setPortfolioTitleField(title);

        String text = randomMessageGeneratorLength(textLength);

        writerProfilePage.setPortfolioTextField(text);
        writerProfilePage.clickOnaAddWorkButton();

    }


    public static Boolean checkForMadeCategoriesChanges(WebDriver driver, String category, List<String> madeChanges){

        ElementsCollection addedCategoriesList = $$(driver.findElements(By.xpath(".//p[contains(text(), '"+ category +"')]/following-sibling::ul/li/span[2]")));
        List<String> categoriesNamesArray = new ArrayList<>();

        for(WebElement element : addedCategoriesList){
            System.out.println(element.getText());
            categoriesNamesArray.add(element.getText());
        }

        if(categoriesNamesArray.containsAll(madeChanges)){

            return true;
        }
        return false;
    }
}




