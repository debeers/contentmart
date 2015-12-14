package Actions.Writer;

import Actions.General.RegistrationAndLogin;
import Entities.LoginObject;
import PageObjects.General.MyOrdersPage;
import PageObjects.Writer.WriterProfilePage;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static Actions.Client.CreateNewOrder.randomID;
import static GeneralHelpers.Messages.randomTextGeneratorLength;

/**
 * Created by DeBeers on 04.10.2015.
 */
public class WriterGoToProfilePage {

    public static WriterProfilePage goToMyProfile(WebDriver driver, LoginObject writerLogin) {

        MyOrdersPage myOrdersPage = RegistrationAndLogin.loginAs(driver, writerLogin);
        myOrdersPage.selectEditWriterProfileFromMenu();
        WriterProfilePage writerProfilePage = new WriterProfilePage(driver);

        return writerProfilePage;
    }


    public static String createTitleForPortfolioItem(){
        return "New automation portfolio item: " + randomID();
    }


    public static String createTextForPortfolioItem(int length){
        return "Portfolio item text - " + randomTextGeneratorLength(length);
    }


    public static void addNewPortfolioItem(WriterProfilePage writerProfilePage, String title,
                                           int textLength) throws InterruptedException {

        writerProfilePage.clickOnAddPortfolioButton();
        String text = randomTextGeneratorLength(textLength);

        writerProfilePage.setPortfolioTitleField(title);
        writerProfilePage.setPortfolioTextField(text);
        writerProfilePage.clickOnaAddWorkButton(title);
    }


    public static Boolean checkForMadeCategoriesChanges(WriterProfilePage writerProfilePage,
                                                        List<String> madeChanges, String category) {

        List<String> addedSkillsList = writerProfilePage.getSettetCategoriesNames(category);

        for (String skillName : addedSkillsList) {
            System.out.println(skillName);
        }
        return addedSkillsList.containsAll(madeChanges);
    }
}




