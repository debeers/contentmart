package PageObjects.Writer;

import PageObjects.General.LeftMenuGeneralPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by CMG_TEST on 10.09.2015.
 */
public class LanguageTestPage extends LeftMenuGeneralPage {


    @FindBy(xpath="//h1[.//text()[contains(., 'English Language Test')]]")
    public WebElement testHeader;

    @FindBy(xpath="//div[3]/div/div/p")
    public WebElement testFAQ;

    @FindBy(xpath="//p[(contains(@class, 'question'))]")
    public WebElement question;


    @FindBy(xpath="//div/span[.//text()[contains(., 'Beginner')]]")
    public WebElement begginnerTest;

    @FindBy(xpath="//div[3]/div[.//text()[contains(., 'Intermediate')]]")
    public WebElement intermediateTest;

    @FindBy(xpath="//div[5]/div[.//text()[contains(., 'Advanced')]]")
    public WebElement AdvancedTest;


    @FindBy(xpath="//div[3]//div/p")
    public WebElement msgBeforeTest;

    @FindBy(xpath="//a[.//text()[contains(., 'Start Language Test')]]")
    public WebElement startTestButton;


    public LanguageTestPage(WebDriver driver) {

        super(driver);
    }


}
