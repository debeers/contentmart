package Actions.Writer;

import Actions.General.RegistrationAndLogin;
import Entities.LoginObject;
import PageObjects.General.MyOrdersPage;
import PageObjects.Writer.WriterEditProfilePage;
import PageObjects.Writer.WriterProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static GeneralHelpers.CreateNewOrderHelper.randomID;
import static GeneralHelpers.Messages.randomTextGeneratorLength;

/**
 * Created by DeBeers on 04.10.2015.
 */
public class GoToWriterProfile {


    public static String randomSelectDateOfBirth(WriterEditProfilePage writerEditProfilePage){

        Select day = new Select(writerEditProfilePage.selectDay);
        Select month = new Select(writerEditProfilePage.selectMonth);
        Select year = new Select(writerEditProfilePage.selectYear);

        day.selectByIndex( new Random().nextInt(day.getOptions().size()));
        month.selectByIndex( new Random().nextInt(month.getOptions().size()));
        year.selectByIndex(new Random().nextInt(year.getOptions().size()));

        return year.getFirstSelectedOption().getText();
    }


    public static Boolean checkForOld(String yearFromRandom, String yearsFromProfilePage){

        int year;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        int yearFromProfile = Integer.parseInt(yearsFromProfilePage);
        int randomYear = Integer.parseInt(yearFromRandom);

        if((year - randomYear) == yearFromProfile){
            return true;
        }

        return false;
    }


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

        return "Portfolio item text - " + randomTextGeneratorLength(70);
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




