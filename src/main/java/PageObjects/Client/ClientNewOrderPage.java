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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.Thread.sleep;

public class ClientNewOrderPage extends LeftMenuGeneralPage {

///////////////////////////////////////////////////   ORDER FIELD`S   //////////////////////////////////////////////////

    @FindBy(xpath = ".//*[@id='title']")
    public WebElement orderNameField;

    @FindBy(id = "description")
    public WebElement descriptionField;

    @FindBy(id = "date_deadline")
    public WebElement deadlineField;

    @FindBy(xpath = ".//*[@id='categories']")
    public WebElement selectCategories;

    @FindBy(className = "ms-choice")
    public WebElement expertise;

    @FindBy(xpath = ".//*[@id='languages']")
    public WebElement languages;

    @FindBy(id = "articles_quantity")
    public WebElement articlesquantityField;

    @FindBy(id = "min")
    public WebElement wordsRequiredField;

    @FindBy(id = "price")
    public WebElement rupeePriceField;

    @FindBy(id = "price-currency")
    public WebElement dollarPriceField;

    @FindBy(xpath = ".//*[@id='fileupload']")
    public WebElement fileuploadInput;

    @FindBy(xpath = ".//*[@id='max-count']")
    public WebElement rupeeOrderValue;

    @FindBy(id = "max-count-currency")
    public WebElement dollarOrderValue;

    @FindBy(id = "d_ord_form")
    public WebElement saveAsDraftButtonInNewOrder;

    @FindBy(xpath = ".//*[@id='new_order']/form/div/div[2]/div[2]/div/a")
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

    @FindBy(xpath = ".//*[@id='new_order']/form/div/div[4]/div[2]/div/div/ul/li/label")
    public WebElement expertisesSelect;


    public void randomSelectWritingCategories(){

        Select selectWritingCategories = new Select(selectCategories);
        selectWritingCategories.selectByIndex(new Random().nextInt(selectWritingCategories.getOptions().size()));
    }


    public void randomSelectLanguage(){

        Select selectLanguages = new Select(languages);
        selectLanguages.selectByIndex(new Random().nextInt(selectLanguages.getOptions().size()));
    }


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


    public String getStopWordsAllert() {

       return  $(stopWordsAllert).shouldBe(visible).getText();
    }


    public void uploadFileToOrder(String filepath) {

        fileuploadInput.sendKeys(filepath);

    }


    public void waitForProgressBarWhenUploadingFilesToNewOrder() {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(progressBar));
        $(progressCounter).shouldHave(text("100%"));
    }


    public String getRupeeOrderValue() {

        String orderVal = $(rupeeOrderValue).shouldBe(visible).getText();
        return orderVal;
    }


    public String getDollarOrderValue() {

        String orderVal = $(dollarOrderValue).shouldBe(visible).getText();
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


    public ClientNewOrderPage setRupeePriceField(String price) {

        rupeePriceField.sendKeys(price);
        return this;
    }


    public ClientNewOrderPage setDollarPriceField(String price) {

        dollarPriceField.sendKeys(price);
        return this;
    }


    public void setOrder(ClientNewOrderPage newOrder, OrderObject order) throws InterruptedException {

        String id = CreateNewOrderHelper.randomID();
        newOrder.orderNameField.clear();
        newOrder.descriptionField.clear();
        newOrder.wordsRequiredField.clear();
        newOrder.dollarPriceField.clear();
        randomSelectWritingCategories();
        newOrder.setOrderNameField(order.getOrdName(), id);
        newOrder.setDescriptionField(order.getDesc());
        newOrder.setWordsRequiredField(order.getWordsReq());

        randomSelectLanguage();
        newOrder.setDollarPriceField(order.getDollarPrice());
        $(newOrder.deadlineField).shouldBe(visible).click();

        order.setOrderValueInRupee(getRupeeOrderValue());
        order.setOrderValueInDollars(getDollarOrderValue());

        System.out.println(order.getOrderValueInDollars() +"  " +  order.getOrderValueInRupee());
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
