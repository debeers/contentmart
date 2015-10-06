package Actions.Writer;

import Actions.General.RegistrationAndLogin;
import Entities.LoginObject;
import PageObjects.General.MyOrdersPage;
import PageObjects.Writer.WriterProfilePage;
import org.openqa.selenium.WebDriver;

import static GeneralHelpers.Messages.randomMessageGeneratorLength;

/**
 * Created by DeBeers on 04.10.2015.
 */
public class GoToWriterProfile {



    public static WriterProfilePage goToMyProfile(WebDriver driver, LoginObject writerLogin){

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
        writerProfilePage.clickOnaAdWorkButton();
        Thread.sleep(3000);

    }








}

