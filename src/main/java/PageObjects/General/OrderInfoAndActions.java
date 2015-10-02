package PageObjects.General;

import Entities.OrderObject;
import GeneralHelpers.CreateNewOrderHelper;
import PageObjects.Client.ClientNewOrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static GeneralHelpers.CustomWaits.$WaitAndGetTextFrom;
import static GeneralHelpers.CustomWaits.$WaitFor;
import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static Tests.BaseTest.wait;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.Thread.sleep;

/**
 * Created by test on 10.09.2015.
 */
@SuppressWarnings("ConstantConditions")
public class OrderInfoAndActions extends LeftMenuGeneralPage {
    @FindBy(xpath = "//button[contains(text(), 'Close order')]")
    public WebElement saveAsDraftSweetAllert;

    @FindBy(css = "#new_results > div:nth-child(4) > span:nth-child(1)")
    public WebElement yourResultHasBeenDeliveredMsg;

    @FindBy(xpath = "//a[contains(text(), 'Edit order')]")
    public WebElement editOrderButtonTop;

    @FindBy(xpath = "//button[contains(text(), 'Delete order')]")
    public WebElement deleteOrderButtonTop;

    @FindBy(xpath = "//a[contains(text(), 'Close order')]")
    public WebElement closeOrderSweet;

    @FindBy(xpath = "//button[contains(text(), 'OK')]")
    public WebElement closeOrderOKSweet;

    @FindBy(xpath = "//button[contains(text(), 'Clone order')]")
    public WebElement cloneOrderButtonTop;

    @FindBy(xpath = "//input[@value='Start working']")
    public WebElement startWorkingButton;

    @FindBy(xpath = ".//*[@id='proposal']/div/div[4]/input")
    public WebElement leaveAnOfferButton;

    @FindBy(xpath = ".//*[@id='text_edit']")
    public WebElement sendTextToTheClientTextArea;

    @FindBy(xpath = "//a[text()='Send completed order']")
    public WebElement sendCompletedOrderButton;

    @FindBy(xpath = ".//*[@id='new_results']/div[4]/span")
    public WebElement successMessageAfterSendResult;


    @FindBy(xpath = "//button[contains(text(),'Drop the Client a Message')]")
    public WebElement dropTheCustomerMessage;

    @FindBy(xpath = "//div[@class='progress-bar']")
    public WebElement uploadFilesProgressBar;

    @FindBy(xpath = "//div[1]/div[3]/div/div/div[3]/h3")
    public WebElement successMessageTextAfterBid;

    @FindBy(xpath = "//div[3]//div[3]//div[2]/div[4]/span")
    public WebElement warningText;


    @FindBy(xpath = "//div[@title='#ID']")
    public WebElement systemOrderID;

    @FindBy(xpath = "//div[@title='Status']")
    public WebElement orderStatus;

    @FindBy(xpath = "//*[@id='order_name']")
    public WebElement orderName;

    @FindBy(xpath = "//div[@class='grey']")
    public WebElement orderDescription;

    @FindBy(xpath = "//div[@title='Publication Date']")
    public WebElement orderPublicationDate;

    @FindBy(xpath = "//div[@title='Deadline']")
    public WebElement orderDeadline;

    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[1]/div[2]/div[2]/div[2]/div[2]/div[3]/div[2]")
    public WebElement typeOfSharing;



    @FindBy(xpath = ".//*[@id='new_results']/div[1]/span")
    public WebElement textAcceptedLablenDecision;

    @FindBy(xpath = "//div[2]/form/div[1]/span")
    public WebElement contentRejectedLablenDecision;

    @FindBy(xpath = "//form/div[2]/div[2]/button[1]")
    public WebElement reasonOfRefusalDeclineButtonDecision;

    @FindBy(xpath = "//div/div[3]/div/div/div/div[2]/div[4]/div")
    public WebElement declineReasonFromClientDecision;


    @FindBy(xpath = "//div[2]/form/div[2]/textarea")
    public WebElement reasonOfRefusalTextAreaDecision;

    @FindBy(xpath = "//button[@action='cancel']")
    public WebElement reasonOfRefusalCancelButtonDecision;

    @FindBy(xpath = ".//*[@id='request']")
    public WebElement reassignTextFieldDecision;

    @FindBy(xpath = ".//*[@id='new_results']/div[2]/div[2]/div/input")
    public WebElement reassignDateFieldOnDecision;

    @FindBy(xpath = ".//*[@id='new_results']/div[2]/div[3]/button[1]")
    public WebElement reassignSendButtonDecision;

    @FindBy(xpath = ".//*[@id='new_results']/div[2]/div[3]/button[1]")
    public WebElement reassignCancelButtonDecision;

    @FindBy(xpath = "html/body/div[2]/div[2]/div/div[1]/div[contains(@class, 'xdsoft_current')]")
    public WebElement currentOrderTimeReassign;

    @FindBy(xpath = ".//*[@id='form-order-completed']/div[3]/a")
    public WebElement closeOrderButton;

    @FindBy(xpath = "//a[@action='accept']")
    public WebElement acceptButtonDecision;

    @FindBy(xpath = "//a[@action='request_to']")
    public WebElement reassignButtonDecision;

    @FindBy(xpath = "//a[@action='one_side_declined']")
    public WebElement declineButtonDecision;

    @FindBy(xpath = "//a[contains(text(), 'Publish order')]")
    public WebElement publishOrderButtonTop;

    @FindBy(xpath = "//button[contains(text(), 'Yes')]")
    public WebElement publishOrderQuestionSweet;

    @FindBy(xpath = "//button[contains(text(), 'OK')]")
    public WebElement publishOrderOKSweet;

    @FindBy(id="description")
    public WebElement leaveAnOfferDetailsField;

    @FindBy(xpath="//form/div/div[1]")
    public WebElement stopwordsAllert;

    @FindBy(xpath="//*[@id=new_results]/div[4]/div[2]")
    public WebElement stopWordsAllertInSendResult;

    @FindBy(partialLinkText="why not to work outside ContentMart")
    public WebElement stopWordsAllert;




    public OrderInfoAndActions(WebDriver driver) {

        super(driver);
    }

    public String waitForStopWordsAllert(){

        String res = $WaitAndGetTextFrom(stopWordsAllert);
        return res;
    }

    public String getTextFromLable() {

        String res = $WaitAndGetTextFrom(contentRejectedLablenDecision);
        return res;
    }

    public String getTextFromSuccessMessageAfterSendResult() {

        String res = $WaitAndGetTextFrom(successMessageAfterSendResult);
        return res;
    }

    public String getTextFromDeclineReasonOnDecisionPage() {

        String res = $WaitAndGetTextFrom(declineReasonFromClientDecision);
        return res;
    }

    public String getTextFromWarningTextAfterStartWorking() {

        String res = $WaitAndGetTextFrom(warningText);
        return res;
    }


    public void clickOnReassingButtonDecision() {

        $WaitFor(reassignButtonDecision).click();
    }

    public void clickOnReassignSendButton() {

        $WaitFor(reassignSendButtonDecision).click();
    }

    public void clickOnreassignDateFieldOnDecisionPage() {

        reassignDateFieldOnDecision.click();
    }

    public void clickOnCurrentOrderTimeForReassignOnDecisionPage() {

        currentOrderTimeReassign.click();
    }

    public void clickOnreassignTextFieldOnDecisionPage() {

        reassignTextFieldDecision.click();
    }


    public void typeDetailsOfYourOfferField(String details){

        $WaitFor(leaveAnOfferDetailsField).sendKeys(details);
    }

    public String stopwordsAllertMsg() {

        String res = wait.until(ExpectedConditions.visibilityOf(stopwordsAllert)).getText();
        return res;
    }


    public ClientNewOrderPage andClickOnPublishOrderButtonTop() throws InterruptedException {

        $WaitFor(publishOrderButtonTop).click();
        sleep(2000);
        $WaitFor(publishOrderQuestionSweet).click();
        sleep(2000);
        $WaitFor(publishOrderOKSweet).click();
        waitForPageLoad(driver);
        ClientNewOrderPage clientNewOrderPage = new ClientNewOrderPage(driver);
        return clientNewOrderPage;
    }

    public OrderInfoAndActions clickOnBidButton(WebDriver driver, OrderObject order) {

        String myOrderBidButton = xBidButton(order.getEntityOrderName());
        $(driver.findElement(By.xpath(myOrderBidButton))).shouldBe(visible).click();
        OrderInfoAndActions orderInfoAndActions = new OrderInfoAndActions(driver);
        waitForPageLoad(driver);

        return orderInfoAndActions;
    }

    public String xBidButton(String orderName) {

        String xpath = "//*[contains(text(),'";
        String t = orderName + "')]/following::div[13]/button";
        return xpath + t;
    }

    public WebElement waitForUploadingFilesToOrder(String filename) {

        WebElement el = $WaitFor(
                driver.findElement(By.xpath("//form//span[2][.//text()[contains(., '" + filename + "')]]")));
        return el;
    }


    public void sendTextToTheClientTextArea(WebDriver driver, String textClassVar) {

       $WaitFor(sendTextToTheClientTextArea).sendKeys(textClassVar);

        waitForPageLoad(driver);
        $WaitFor(orderStatus); //страховка
    }

    public void clickOnTheSendCompletedOrderButton(WebDriver driver) {

        $WaitFor(sendCompletedOrderButton).click();
        waitForPageLoad(driver);
        $WaitFor(yourResultHasBeenDeliveredMsg);

    }

    public MyMessagesPage clickOnTheDropTheCustomerMessageButton(WebDriver driver) {

        $WaitFor(dropTheCustomerMessage).click();
        waitForPageLoad(driver);
        MyMessagesPage myMessagesPage = new MyMessagesPage(driver);
        $WaitFor(myMessagesPage.sendMessageButton);
        return myMessagesPage;
    }

    public void clickOnLeaveAnOfferButtonFromBidOnOrder(WebDriver driver) {

        $WaitFor(leaveAnOfferButton).click();
        waitForPageLoad(driver);
    }

    public void clickOnStartWorkingButtonAndAcceptSweet() throws InterruptedException {

        $WaitFor(startWorkingButton).click();
        sleep(2000);
        driver.findElement(By.xpath("html/body/div[3]/div[7]/button[2]")).click();
        waitForPageLoad(driver);
    }

    public MyOrdersPage clickOnDeleteOrderButton() {

        $WaitFor(deleteOrderButtonTop).click();
        waitForPageLoad(driver);
        MyOrdersPage myOrders = new MyOrdersPage(driver);
        return myOrders;
    }

    public ClientNewOrderPage clickOnCloneOrderButton() {

        $WaitFor(cloneOrderButtonTop).click();
        waitForPageLoad(driver);
        ClientNewOrderPage clientNewOrderPage = new ClientNewOrderPage(driver);
        return clientNewOrderPage;
    }

    public ClientNewOrderPage andClickOnEditOrderButton() {

        $WaitFor(editOrderButtonTop).click();
        waitForPageLoad(driver);
        ClientNewOrderPage clientNewOrderPage = new ClientNewOrderPage(driver);
        return clientNewOrderPage;
    }

    public ClientNewOrderPage andClickOnCloseOrderButton() throws InterruptedException {

        $WaitFor(closeOrderButton).click();
        sleep(2000);
        $WaitFor(closeOrderSweet).click();
        sleep(2000);
        $WaitFor(closeOrderOKSweet).click();
        waitForPageLoad(driver);
        ClientNewOrderPage clientNewOrderPage = new ClientNewOrderPage(driver);

        return clientNewOrderPage;
    }

    public void setReassignDate() throws InterruptedException {

        String day = CreateNewOrderHelper.getDay();
        String path = xDateBuilderReassign(day);
        clickOnreassignDateFieldOnDecisionPage();
        sleep(2000);

        WebElement setDate = driver.findElement(By.xpath(path));
        System.out.println(setDate);
        setDate.click();

        clickOnCurrentOrderTimeForReassignOnDecisionPage();
        clickOnreassignTextFieldOnDecisionPage();

    }

    public String xDateBuilderReassign(String day) {

        String xpath = "html//td[.//text()[contains(., '";
        String t = day + "')]] [not(contains(@class, 'xdsoft_disabled'))]";
        return xpath + t;
    }

    public String xOrder(String createdOrderName) {

        String xpath = "//*[contains(text(),'";
        String t = createdOrderName + "')]";
        return xpath + t;
    }

    public String xContains(String createdOrderName) {

        String xpath = "//*[contains(text(),'";
        String t = createdOrderName + "')]";
        return xpath + t;
    }

    public String getorderName() {

        String param = $WaitAndGetTextFrom(orderName);
        return param;
    }

    public String getsystemOrderID() {

        String param = $WaitAndGetTextFrom(systemOrderID);
        return param;
    }

    public String gettypeOfSharing() {

        String param = $WaitAndGetTextFrom(typeOfSharing);
        return param;
    }
    public String getorderStatus() {

        String param = $WaitAndGetTextFrom(orderStatus);
        return param;
    }

    public String getorderDescription() {
        String param = $WaitAndGetTextFrom(orderDescription);
        return param;
    }

    public String getorderPublicationDate() {
        String param = $WaitAndGetTextFrom(orderPublicationDate);
        return param;
    }

    public String getorderDeadline() {
        String param = $WaitAndGetTextFrom(orderDeadline);
        return param;
    }

    public String getTextFromOrderStatus() {
        String res = $WaitAndGetTextFrom(orderStatus);
        return res;
    }

    public String getTextFromSuccessMessageTextAfterBid() {
        String res = $WaitAndGetTextFrom(successMessageTextAfterBid);
        return res;
    }

    public void clickOnAcceptButtonOnDecisionPage() {

        $WaitFor(acceptButtonDecision).click();
        $WaitFor(textAcceptedLablenDecision);
    }

    public String acceptTextLableOnDecisionPage() {

        String str = $WaitAndGetTextFrom(textAcceptedLablenDecision);
        return str;
    }

    public String getTextFromOrderName() {

        String str = $WaitAndGetTextFrom(orderName);
        return str;
    }


    public void clickOndeclineButtonOnDecisionPage() {

        $WaitFor(declineButtonDecision).click();
        waitForPageLoad(driver);
    }


    public void sendReasonOfRefusalTextAreaDecision(WebDriver driver, String declineReason) {

        $WaitFor(reasonOfRefusalTextAreaDecision).sendKeys(declineReason);
        waitForPageLoad(driver);
    }


    public void clickOnDeclineButtonAfterRefusalDecision(WebDriver driver) {

        $WaitFor(reasonOfRefusalDeclineButtonDecision).click();
        waitForPageLoad(driver);
    }


    public OrderInfoAndActions clickOnCloseOrderButtonTopAndAcceptSweet(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        $WaitFor(closeOrderButton).click();
        sleep(1500);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[3]/div[7]/button[2]"))).click();

        sleep(1500);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[3]/div[7]/button[2]"))).click();
        waitForPageLoad(driver);
        OrderInfoAndActions orderInfoAndActions = new OrderInfoAndActions(driver);
        waitForPageLoad(driver);
        return orderInfoAndActions;
    }


}
