package PageObjects.Client;


import Entities.OrderObject;
import GeneralHelpers.CreateNewOrderHelper;
import PageObjects.General.LeftMenuGeneralPage;
import PageObjects.General.MyOrdersPage;
import PageObjects.General.OrderInfoAndActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.Thread.sleep;

public class ClientNewOrderPage extends LeftMenuGeneralPage {

///////////////////////////////////////////////////   ORDER FIELD`S   //////////////////////////////////////////////////

    @FindBy(xpath = ".//*[@id='title']")
    public WebElement orderNameField;

    @FindBy(id = "description")
    public WebElement descriptionField;

    @FindBy(id = "date_deadline")
    public WebElement deadlineField;

    @FindBy(id = "articles_quantity")
    public WebElement articlesquantityField;

    @FindBy(id = "min")
    public WebElement wordsRequiredField;

    @FindBy(id = "price")
    public WebElement priceField;

    @FindBy(xpath = ".//*[@id='fileupload']")
    public WebElement fileuploadInput;

    @FindBy(id = "max_count")
    public WebElement orderValue;

    @FindBy(id = "d_ord_form")
    public WebElement saveAsDraftButtonInNewOrder;

    @FindBy(className = "red banned_words_notice")
    public WebElement stopWordsAllert;

    /////////////////////  TOP
    //// drafted
    @FindBy(xpath = "//a[contains(text(), 'Publish order')]")
    public WebElement publishOrderButtonTop;

    @FindBy(xpath = "//button[contains(text(), 'Yes')]")
    public WebElement publishOrderQuestionSweet;

    @FindBy(xpath = "//button[contains(text(), 'OK')]")
    public WebElement publishOrderOKSweet;

    /// after publish

    //// edit order BOTTOM

    @FindBy(xpath = "//button[contains(text(), 'Publish')]")
    public WebElement publishNewOrderButton;

    @FindBy(xpath = "//button[contains(text(), 'Cancel')]")
    public WebElement cancelOrderButtonBottom;

    @FindBy(xpath = "html//div[2]/div[6]/ul/li/a")
    public List<WebElement> attachedFiles;

    @FindBy(xpath = "html//td[contains(@class, 'xdsoft_current')]")
    //   /following-sibling::td[1]   next   html//td[.//text()[contains(., '29')]]  [not(contains(@class, 'xdsoft_disabled'))]
    public WebElement currentOrderDay;

    @FindBy(xpath = "html/body/div[2]/div[2]/div/div[1]/div[contains(@class, 'xdsoft_current')]")
    public WebElement currentOrderTime;

///////////////////////////////////////////////////   BUTTONS   ////////////////////////////////////////////////////////

    @FindBy(id = "c_ord_form")
    public WebElement publishButton;

    @FindBy(className = "item")
    public List<WebElement> uploadedFilesUnderTextField;

    @FindBy(xpath = "//button[contains(text(), 'Save as draft')]")
    public WebElement saveAsDraftButtonBottom;

    @FindBy(className = "progress-bar")
    public WebElement progressBar;

    @FindBy(id = "progress")
    public WebElement progressCounter;


    public OrderInfoAndActions andClickOnSaveAsDraftButton(WebDriver driver) throws InterruptedException {

        $(saveAsDraftButtonBottom).shouldBe(visible).click();
        waitForPageLoad(driver);
        OrderInfoAndActions orderInfoAndActions = new OrderInfoAndActions(driver);

        return orderInfoAndActions;
    }


    public String xDateBuilder(String day) {

        String xpath = "html//td[.//text()[contains(., '";
        String t = day + "')]] [not(contains(@class, 'xdsoft_disabled'))]";
        return xpath + t;
    }


    public MyOrdersPage clickOnCancelOrderButton() {

        $(cancelOrderButtonBottom).shouldBe(visible).click();
        waitForPageLoad(driver);
        MyOrdersPage myOrders = new MyOrdersPage(driver);
        return myOrders;
    }


    public OrderInfoAndActions andClickOnPublishNewOrderButton(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        publishButton.click();
        OrderInfoAndActions orderInfoAndActions = new OrderInfoAndActions(driver);
        waitForPageLoad(driver);
        wait.until(ExpectedConditions.visibilityOf(orderInfoAndActions.orderStatus));
        wait.until(ExpectedConditions.visibilityOf(orderInfoAndActions.orderDeadline));

        return orderInfoAndActions;
    }


    public Boolean waitForStopWordsAllertAppear() {

        if ($(stopWordsAllert).is(visible)) {
            return true;
        }
        System.out.println("Stop words allert did not appear!");
        return false;
    }


    public void uploadFileToOrder(String filepath) {

        fileuploadInput.sendKeys(filepath);

    }

    public void waitForProgressBarWhenUploadingFilesToNewOrder() {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(progressBar));
        $(progressCounter).shouldHave(text("100%"));
    }


    public String getOrderValue() {

        String orderVal = $(orderValue).shouldBe(visible).getText();
        return orderVal;
    }


    public ClientNewOrderPage setOrderNameField(String orderName, String id) {

        orderNameField.sendKeys(orderName + id);
        return this;
    }


    public ClientNewOrderPage setDescriptionField(String description) {

        descriptionField.sendKeys(description);
        return this;
    }


    public ClientNewOrderPage setWordsRequiredField(String words) {

        wordsRequiredField.sendKeys(words);
        return this;
    }


    public ClientNewOrderPage setPriceField(String price) {

        priceField.sendKeys(price);
        return this;
    }


    public void setOrder(WebDriver driver, ClientNewOrderPage newOrder, OrderObject order) throws InterruptedException {

        String id = CreateNewOrderHelper.randomID();
        newOrder.orderNameField.clear();
        newOrder.descriptionField.clear();
        newOrder.wordsRequiredField.clear();
        newOrder.priceField.clear();
        newOrder.setOrderNameField(order.getOrdName(), id);
        newOrder.setDescriptionField(order.getDesc());
        newOrder.setWordsRequiredField(order.getWordsReq());
        newOrder.setPriceField(order.getPrice());
        $(newOrder.deadlineField).shouldBe(visible).click();

        order.setEntityOrderValue($(newOrder.orderValue).shouldBe(visible).getText());

        sleep(3000);

        String day = CreateNewOrderHelper.getDay();
        String path = xDateBuilder(day);

        WebElement setDate = driver.findElement(By.xpath(path));
        setDate.click();
        $(newOrder.currentOrderTime).click();
        $(newOrder.descriptionField).click();

    }


    public ClientNewOrderPage(WebDriver driver) {

        super(driver);
    }

}
