package PageObjects.General;

import Entities.LoginObject;
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

import static Actions.RegistrationAndLogin.logOut;
import static Actions.RegistrationAndLogin.loginAs;
import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static Tests.BaseTest.wait;
import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LeftMenuGeneralPage extends BasePageObject {



    @FindBy(xpath="html/body/div/div[3]/ul/li[2]/div[1]/div/p")
    public  WebElement profileLeftMenu;

    @FindBy(xpath = "/html/body/div[1]/div[3]/ul/li[3]/a")
    public  WebElement newOrderLeftMenu;

    @FindBy(xpath="html/body/div/div[3]/ul/li[2]/div[2]/ul/li[2]/a")
    public WebElement editProfileLeftMenu;

    @FindBy(xpath="html/body/div/div[3]/ul/li[2]/div[2]/ul/li[3]/a")
    public  WebElement myMessagesLeftMenu;

    @FindBy(xpath="html/body/div/div[3]/ul/li[2]/div[2]/ul/li[4]/a")
    public WebElement logOutLeftMenuWriter;

    ///////////////////////////////// Static menu links

    @FindBy(xpath="html/body/div/div[3]/ul/li[3]/a")
    public WebElement myOrdersLeftMenu;

    @FindBy(xpath="html/body/div/div[3]/ul/li[4]/a")
    public  WebElement allOrdersLeftMenu;

    @FindBy(xpath="html/body/div/div[3]/ul/li[5]/a")
    public  WebElement customersLeftMenu;

    @FindBy(xpath="html/body/div/div[3]/ul/li[6]/a")
    public  WebElement notificationsLeftMenu;

    @FindBy(xpath="html/body/div/div[3]/ul/li[7]/a")
    public WebElement balanceLeftMenu;

    @FindBy(xpath="html/body/div/div[3]/ul/li[8]/p/a")
    public WebElement helpCenterLeft;


///////////////////////////////////   Left Template_Client Links END   ////////////////////////////////////////////////////////////


    public EditProfilePage clickOnEditProfileLeftMenu() {

        $(editProfileLeftMenu).shouldBe(present).click();
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


    public WriterAllOrdersPage clickOnAllOrdersLeftMenu(WebDriver driver, LoginObject writerLogin) {
        if (driver.getTitle() != "My Orders | ContentMart") {
            logOut(driver);
            loginAs(driver, writerLogin);
        }


        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(allOrdersLeftMenu)).click();
        waitForPageLoad(driver);
        WriterAllOrdersPage writerAllOrdersPage = new WriterAllOrdersPage(driver);

        return writerAllOrdersPage;
    }

    public void clickOnProfileLeftMenu() {

        $(profileLeftMenu).shouldBe(visible).click();
    }

    public void clickOnAllOrdersLeftMenuMenu() {

        wait.until(ExpectedConditions.visibilityOf(allOrdersLeftMenu)).click();
        GeneralWaits.waitForPageLoad(driver);
    }

    public BalanceGeneralPage clickOnbalanceLeftMenu() {

        $(balanceLeftMenu).shouldBe(visible).click();
        GeneralWaits.waitForPageLoad(driver);
        BalanceGeneralPage balanceGeneral = new BalanceGeneralPage(driver);
        wait.until(ExpectedConditions.visibilityOf(balanceGeneral.availebleBalance));
        return balanceGeneral;
    }


    public MyMessagesPage clickOnMyMessagesLeftMenu(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(myMessagesLeftMenu)).click();
        waitForPageLoad(driver);
        MyMessagesPage myMessagesPage = new MyMessagesPage(driver);

        return myMessagesPage;
    }

    public LoginPage clickOnLoOutLeftMenu(WebDriver driver) {

        wait.until(ExpectedConditions.visibilityOf(logOutLeftMenuWriter)).click();
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










