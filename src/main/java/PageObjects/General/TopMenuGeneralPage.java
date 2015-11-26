package PageObjects.General;

import PageObjects.BasePageObject;
import PageObjects.Client.NewOrderPage;
import PageObjects.Writer.WriterEditProfilePage;
import PageObjects.Writer.WriterProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static GeneralHelpers.CustomWaits.$WaitFor;
import static Tests.BaseTest.wait;
import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TopMenuGeneralPage extends BasePageObject {

//  Drop down menu items::

    @FindBy(xpath = "//a[@class = 'dropit-click']")
    public WebElement profileTopMenu;

    @FindBy(xpath = "//li[@class = 'user']/a")
    public WebElement userProfileDropMenu;

    @FindBy(xpath = "//li[@class = 'user']/a/p")
    public WebElement userNameFromDropMenu;

    @FindBy(xpath = ".//a[contains (text(), 'Notifications')]")
    public WebElement notificationsDropMenu;

    @FindBy(xpath = ".//li//a[contains (text(), 'Edit Profile')]")
    public WebElement editProfileDropMenu;

    @FindBy(xpath = "//a[contains (text(), 'Account Settings')]")
    public WebElement accountSettingsDropMenu;

    @FindBy(xpath = "//a[contains (text(), 'Logout')]")
    public WebElement logOutDropMenu;

//  Static menu items::

    @FindBy(xpath = "//div[@class = 'container-navbar']/div[7]")
    public WebElement myMessagesDropMenu;

    @FindBy(xpath = "//span[@class = 'new-message-counter']")
    public WebElement messagesCounter;

    @FindBy(xpath = "//div[@class = 'container-navbar']/div[6]")
    public WebElement helpCenterTopMenu;

    @FindBy(xpath = "//div[@class = 'container-navbar']/div[5]")
    public WebElement balanceTopMenu;

    @FindBy(xpath = "//div[@class = 'container-navbar']/div[5]//span[@class = 'balance-amount']")
    public WebElement balanceInRupeeTopMenu;

    @FindBy(xpath = "//div[@class = 'container-navbar']/div[5]//span[@class = 'balance-amount-usd']")
    public WebElement balanceInUSDTopMenu;

    @FindBy(xpath = "//div[@class = 'container-navbar']/div[4]")
    public WebElement writersTopMenu;

    @FindBy(xpath = "//div[@class = 'container-navbar']/div[4]")
    public WebElement clientsTopMenu;

    @FindBy(xpath = "//div[@class = 'container-navbar']/div[3]")
    public WebElement myOrdersTopMenu;

    @FindBy(xpath = "//div[@class = 'container-navbar']/div[2]")
    public WebElement newOrderTopMenu;

    @FindBy(xpath = "//div[@class = 'container-navbar']/div[2]")
    public WebElement allOrdersTopMenu;

    @FindBy(xpath = "//div[@class = 'container-navbar']/div[1]")
    public WebElement logoContentMartTopMenu;


    //  Methods::

    // Drop down profile menu ::

    public void clickOnProfileFromTopMenu() {

        $(profileTopMenu).click();
        $WaitFor(
                userProfileDropMenu,
                accountSettingsDropMenu,
                editProfileDropMenu,
                notificationsDropMenu,
                logOutDropMenu
        );
    }


//    public WriterProfilePage clickOnUserProfileFromDropMenu() {
//
//        $(userProfileDropMenu).shouldBe(visible).click();
//        return new WriterProfilePage(driver);
//    }

    public String getUserNickNameFromProfileDropMenu() {
        clickOnProfileFromTopMenu();
        return $(userNameFromDropMenu).shouldBe(visible).getText();
    }

    public NotificationsPage clickOnNotificationsFromDropMenu() {

        $(notificationsDropMenu).shouldBe(visible).click();
        return new NotificationsPage(driver);
    }

//    public ClientEditProfilePage clientClickOnEditProfileFromDropMenu() {
//
//        $(editProfileDropMenu).shouldBe(visible).click();
//        return new ClientEditProfilePage(driver);
//    }
//
    public WriterEditProfilePage writerClickOnEditProfileFromDropMenu() {

        $(editProfileDropMenu).shouldBe(visible).click();
        return new WriterEditProfilePage(driver);
    }

    public AccountDetailsPage clickOnAccountSettingsDropMenu() {

        accountSettingsDropMenu.click();
        return new AccountDetailsPage(driver);
    }

    public LoginPage clickOnLogOutFromDropMenu() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(logOutDropMenu)).click();
        return new LoginPage(driver);
    }

    // Static menu components::

    public NewOrderPage clickOnNewOrderFromTopMenu() {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(newOrderTopMenu)).click();

        NewOrderPage clientNewOrderPage = new NewOrderPage(driver);
        $(clientNewOrderPage.publishButton).shouldBe(present);
        return clientNewOrderPage;
    }

//    public WriterAllOrdersPage clickOnAllOrdersFromTopMenu() {
//
//        $(allOrdersTopMenu).shouldBe(visible).click();
//        WriterAllOrdersPage writerAllOrdersPage = new WriterAllOrdersPage(driver);
//        return writerAllOrdersPage;
//    }
//
//    public MyOrdersPage clickOnMyOrdersTopMenu() {
//
//        $(myOrdersTopMenu).shouldBe(visible).click();
//        return new MyOrdersPage(driver);
//    }

    public PartnersPage clickOnClientsFromTopMenu() {

        $(clientsTopMenu).shouldBe(visible).click();
        return new PartnersPage(driver);
    }

    public PartnersPage clickOnWritersFromTopMenu() {

        $(writersTopMenu).shouldBe(visible).click();
        return new PartnersPage(driver);
    }

//    public BalanceGeneralPage clickOnbalanceFromLeftMenu() {
//
//        $(balanceTopMenu).shouldBe(visible).click();
//        BalanceGeneralPage balanceGeneral = new BalanceGeneralPage(driver);
//        wait.until(ExpectedConditions.visibilityOf(balanceGeneral.availebleBalance));
//        return balanceGeneral;
//    }

    public String getBalanceInRupeeFromTopMenu() {

        return $(balanceInRupeeTopMenu).shouldBe(visible).getText();
    }

    public String getBalanceInUSDFromTopMenu() {

        return $(balanceInUSDTopMenu).shouldBe(visible).getText();
    }

    public MyMessagesPage clickOnMyMessagesFromTopMenu() {

        wait.until(ExpectedConditions.visibilityOf(myMessagesDropMenu)).click();
        return new MyMessagesPage(driver);
    }

    public String getMessageCountFromTopMenu() {

        return $(messagesCounter).shouldBe(visible).getText();
    }

    public NotificationsPage selectNotificationsFromMenu() {

        clickOnProfileFromTopMenu();
        return clickOnNotificationsFromDropMenu();
    }

    public WriterEditProfilePage selectEditWriterProfileFromMenu() {

        clickOnProfileFromTopMenu();
        return writerClickOnEditProfileFromDropMenu();
    }

    public WriterProfilePage selectWriterProfileFromMenu() {

        clickOnProfileFromTopMenu();
        $(userProfileDropMenu).click();
        return new WriterProfilePage(driver);
    }


//
//    public ClientEditProfilePage selectEditClientProfileFromMenu() {
//
//        clickOnProfileFromTopMenu();
//        return clientClickOnEditProfileFromDropMenu();
//    }

    public AccountDetailsPage selectAccountSettingsFromMenu() {

        clickOnProfileFromTopMenu();
        return clickOnAccountSettingsDropMenu();
    }

    public LoginPage selectLogoutFromMenu() throws InterruptedException {

        clickOnProfileFromTopMenu();
        return clickOnLogOutFromDropMenu();
    }


    public TopMenuGeneralPage(WebDriver driver) {
        super(driver);
    }

    public void init(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}










