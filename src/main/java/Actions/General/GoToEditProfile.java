package Actions.General;

import Entities.LoginObject;
import PageObjects.General.EditProfilePage;
import PageObjects.General.MyOrdersPage;
import com.codeborne.selenide.Condition;
import com.google.sitebricks.client.Web;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static Tests.BaseTest.driver;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by CMG_TEST on 08.09.2015.
 */
public class GoToEditProfile {


    public static EditProfilePage goToEditProfile(WebDriver driver, LoginObject clientLogin) throws InterruptedException {

        MyOrdersPage defaultClientPage = RegistrationAndLogin.loginAs(driver, clientLogin);
        defaultClientPage.clickOnProfileLeftMenu();
        EditProfilePage editProfilePage = defaultClientPage.clickOnEditProfileLeftMenu();

        $WaitFor(

                editProfilePage.nickNameField,
                editProfilePage.zipField,
                editProfilePage.saveChangesButton,
                editProfilePage.adressTextArea,
                editProfilePage.panField,
                editProfilePage.phoneField,
                editProfilePage.stateSelect,
                editProfilePage.citySelect

        );

        return editProfilePage;


    }



    public static Boolean checkForOFFTriggersStatus(){

        EditProfilePage ep = new EditProfilePage(driver);

    for (WebElement r : ep.triggers){


        if ($(r).getAttribute("class").contains("switch toggle-on") ){
            System.out.println("Some elements did not save changes: " + r.getAttribute("class"));
            return false;

        } else if ($(r).getAttribute("class").contains("switch toggle-off") ){
            System.out.println("All changes were saved properly: status HURRAY!!!)  " + r.getAttribute("class"));
            return true;
        }

    }

            return false;

    }

    public static Boolean checkForONTriggersStatus(){

        EditProfilePage ep = new EditProfilePage(driver);

        for (WebElement r : ep.triggers){


            if ($(r).getAttribute("class").contains("switch toggle-off") ){
                System.out.println("Some elements did not save changes: " + r.getAttribute("class"));
                return false;

            } else if ($(r).getAttribute("class").contains("switch toggle-on") ){
                System.out.println("All changes were saved properly: status HURRAY!!!)  " + r.getAttribute("class"));
                return true;
            }

        }

        return false;

    }





}
