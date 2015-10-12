package Actions.Writer;

import Actions.General.RegistrationAndLogin;
import Entities.LoginObject;
import PageObjects.General.MyOrdersPage;
import PageObjects.Writer.WriterProfilePage;
import org.openqa.selenium.WebDriver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static GeneralHelpers.CreateNewOrderHelper.randomID;
import static GeneralHelpers.Messages.randomMessageGeneratorLength;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by DeBeers on 04.10.2015.
 */
public class GoToWriterProfile {


    public static WriterProfilePage goToMyProfile(WebDriver driver, LoginObject writerLogin) {

        MyOrdersPage myOrdersPage = RegistrationAndLogin.loginAs(driver, writerLogin);
        myOrdersPage.clickOnProfileFromLeftMenu();
        myOrdersPage.clickOnMyProfileFromLeftMenuLeftMenu();
        WriterProfilePage writerProfilePage = new WriterProfilePage(driver);

        return writerProfilePage;
    }


    public static String createTitleForPortfolioItem(){

        return "New automation portfolio item: " + randomID();
    }


    public static String createTextForPortfolioItem(int length){

        return "Portfolio item text - " + randomMessageGeneratorLength(70);
    }


    public static void addNewPortfolioItem(WriterProfilePage writerProfilePage, String title,
                                           int textLength) throws InterruptedException {

        writerProfilePage.clickOnAddPortfolioButton();

        String text = randomMessageGeneratorLength(textLength);

        writerProfilePage.setPortfolioTitleField(title);
        writerProfilePage.setPortfolioTextField(text);

        writerProfilePage.clickOnaAddWorkButton();
    }


    public static Boolean checkForMadeCategoriesChanges(WriterProfilePage writerProfilePage,
                                                        List<String> madeChanges, String category){

        List<String> addedSkillsList = writerProfilePage.getSettetCategoriesNames(category);

        for(String skillName : addedSkillsList){
            System.out.println(skillName);
        }

        if(addedSkillsList.containsAll(madeChanges)){

            return true;
        }
        return false;
    }

}




