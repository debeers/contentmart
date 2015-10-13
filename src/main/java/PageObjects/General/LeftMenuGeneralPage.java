package PageObjects.General;

import GeneralHelpers.GeneralWaits;
import PageObjects.BasePageObject;
import PageObjects.Client.ClientNewOrderPage;
import PageObjects.Writer.WriterAllOrdersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static Tests.BaseTest.wait;
import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LeftMenuGeneralPage extends BasePageObject {


    @FindBy(xpath = "html/body/div/div[3]/ul/li[2]/div[1]/div/p")
    public WebElement profileLeftMenu;

    @FindBy(xpath = ".//span[contains (@class, 'item')][contains (text(), 'My profile')]")
    public WebElement myProfileLeftMenu;

    @FindBy(xpath = ".//a[contains (text(), 'New Order')]")
    public WebElement newOrderLeftMenu;

    @FindBy(xpath = ".//span[contains (@class, 'item')][contains (text(), 'Account Settings')]")
    public WebElement accountSettingsLeftMenu;

    @FindBy(xpath = "html/body/div/div[3]/ul/li[2]/div[2]/ul/li[3]/a")
    public WebElement myMessagesLeftMenu;

    @FindBy(xpath = ".//span[contains (@class, 'item')][contains (text(), 'Logout')]")
    public WebElement logOutLeftMenu;

    ///////////////////////////////// Static menu links

    @FindBy(xpath = ".//a[contains (text(), 'My Orders')]")
    public WebElement myOrdersLeftMenu;

    @FindBy(xpath = "html/body/div/div[3]/ul/li[4]/a")
    public WebElement allOrdersLeftMenu;

    @FindBy(xpath = ".//a[contains (text(), 'Customers')]")
    public WebElement customersLeftMenu;

    @FindBy(xpath = ".//a[contains (text(), 'Writers')]")
    public WebElement writersLeftMenu;

    @FindBy(xpath = ".//a[contains (text(), 'Notifications')]")
    public WebElement notificationsLeftMenu;

    @FindBy(xpath = ".//a[contains (text(), 'Balance')]")
    public WebElement balanceLeftMenu;

    @FindBy(xpath = ".//a[contains (text(), 'Help Center')]")
    public WebElement helpCenterLeft;

///////////////////////////////////   Left Template_Client Links END   ////////////////////////////////////////////////////////////

    public EditProfilePage clickOnAccountSettingsFromLeftMenu() {

        $(accountSettingsLeftMenu).shouldBe(present).click();
        waitForPageLoad(driver);
        EditProfilePage editProfilePage = new EditProfilePage(driver);
        return editProfilePage;
    }


    public ClientNewOrderPage clickOnNewOrderFromLeftMenu() {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(newOrderLeftMenu)).click();

        waitForPageLoad(driver);
        ClientNewOrderPage clientNewOrderPage = new ClientNewOrderPage(driver);

        $(clientNewOrderPage.publishButton).shouldBe(present);
        $(clientNewOrderPage.saveAsDraftButtonInNewOrder).shouldBe(present);

        return clientNewOrderPage;
    }


    public WriterAllOrdersPage clickOnAllOrdersFromLeftMenu(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(allOrdersLeftMenu)).click();
        waitForPageLoad(driver);
        WriterAllOrdersPage writerAllOrdersPage = new WriterAllOrdersPage(driver);

        return writerAllOrdersPage;
    }

    public void clickOnProfileFromLeftMenu() {

        $(profileLeftMenu).shouldBe(visible).click();
        $WaitFor(accountSettingsLeftMenu, myMessagesLeftMenu, profileLeftMenu, logOutLeftMenu);
    }


    public void clickOnMyProfileFromLeftMenuLeftMenu() {

        $(myProfileLeftMenu).shouldBe(visible).click();
    }


    public void clickOnAllOrdersFromLeftMenuMenu() {

        wait.until(ExpectedConditions.visibilityOf(allOrdersLeftMenu)).click();
        GeneralWaits.waitForPageLoad(driver);
    }

    public BalanceGeneralPage clickOnbalanceFromLeftMenu() {

        $(balanceLeftMenu).shouldBe(visible).click();
        GeneralWaits.waitForPageLoad(driver);
        BalanceGeneralPage balanceGeneral = new BalanceGeneralPage(driver);
        wait.until(ExpectedConditions.visibilityOf(balanceGeneral.availebleBalance));
        return balanceGeneral;
    }


    public MyMessagesPage clickOnMyMessagesFromLeftMenu(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(myMessagesLeftMenu)).click();
        waitForPageLoad(driver);
        MyMessagesPage myMessagesPage = new MyMessagesPage(driver);

        return myMessagesPage;
    }

    public LoginPage clickOnLoOutFromLeftMenu(WebDriver driver) {

        wait.until(ExpectedConditions.visibilityOf(logOutLeftMenu)).click();
        GeneralWaits.waitForPageLoad(driver);
        LoginPage loginPage = new LoginPage(driver);
        return loginPage;
    }


    public LeftMenuGeneralPage(WebDriver driver) {

        super(driver);
    }

    public void init(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

}










