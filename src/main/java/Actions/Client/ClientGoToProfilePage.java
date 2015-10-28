package Actions.Client;

import Actions.General.RegistrationAndLogin;
import Entities.LoginObject;
import PageObjects.Client.ClientEditProfilePage;
import PageObjects.Client.ClientProfilePage;
import PageObjects.General.MyOrdersPage;
import com.google.common.collect.Sets;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by DeBeers on 18.10.2015.
 */
public class ClientGoToProfilePage {


    public static ClientProfilePage goToMyProfile(WebDriver driver, LoginObject writerLogin) {

        MyOrdersPage myOrdersPage = RegistrationAndLogin.loginAs(driver, writerLogin);
        myOrdersPage.clickOnProfileFromLeftMenu();
        myOrdersPage.clickOnMyProfileFromLeftMenuLeftMenu();
        ClientProfilePage clientProfilePage = new ClientProfilePage(driver);

        return clientProfilePage;
    }

}
