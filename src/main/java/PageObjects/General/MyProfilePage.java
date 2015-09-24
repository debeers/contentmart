package PageObjects.General;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by CMG_TEST on 10.09.2015.
 */
public class MyProfilePage extends LeftMenuGeneralPage {


    @FindBy(xpath = "//p/a[.//text()[contains(., 'All Tests')]]")
    public WebElement allTestsLink;   /// to profile


    public MyProfilePage(WebDriver driver) {

        super(driver);
    }


}
