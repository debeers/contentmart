package Actions.Writer;

import Actions.General.RegistrationAndLogin;
import Entities.LoginObject;
import PageObjects.Client.ClientEditProfilePage;
import PageObjects.Client.ClientProfilePage;
import PageObjects.General.MyOrdersPage;
import PageObjects.Writer.WriterEditProfilePage;
import PageObjects.Writer.WriterProfilePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import static GeneralHelpers.CreateNewOrderHelper.randomID;
import static GeneralHelpers.Messages.randomTextGeneratorLength;

/**
 * Created by DeBeers on 04.10.2015.
 */
public class WriterGoToProfilePage {

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

        if (addedSkillsList.containsAll(madeChanges)) {

            return true;
        }
        return false;
    }

}




