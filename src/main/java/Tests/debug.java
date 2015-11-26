package Tests;

import PageObjects.General.AccountDetailsPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.PartnersPage;
import PageObjects.Writer.WriterEditProfilePage;
import PageObjects.Writer.WriterProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.General.RegistrationAndLogin.loginAs;
import static GeneralHelpers.DBWorker.checkForExitingUser;
import static GeneralHelpers.DBWorker.setUserNickName;
import static GeneralHelpers.JDBCutil.executeQuery;

/**
 * Created by DeBeers on 26.11.2015.
 */
public class debug extends BaseTest{




    @Test(groups={"regress 1.0"})
    public static void debug() throws Exception {


        String URL       = "https://dev.contentmart.in/forwriters";
        String subject   = "Welcome to ContentMart.in";
        String fromEmail = "info@contentmart.in";
        Boolean isSeen   = false;
        int timeToWait   = 600;

        String userType          = "SELECT * FROM users\n" +
                "WHERE email = \"contentmartmai@gmail.com\"";
        String userNickName      =  "gkbbkhbghgh";
        String userEmail         = "contentmartmai@gmail.com";
        String userPassword      = "7777777";
        String registrationTitle = "Register successful | ContentMart";


        checkForExitingUser(userType, "email", fromEmail);
    }


}
