package PageObjects.Client;

import Entities.OrderObject;
import GeneralHelpers.CreateNewOrderHelper;
import PageObjects.General.OrderWorkFlow;
import PageObjects.General.TopMenuGeneralPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static GeneralHelpers.GeneralHelpers.isFileUploaded;
import static com.codeborne.selenide.Condition.or;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.Thread.sleep;

/**
 * Created by DeBeers on 18.11.2015.
 */
public class NewOrderPage extends TopMenuGeneralPage {


    @FindBy(xpath = ".//*[@id='title']")
    public WebElement orderNameField;

    @FindBy(xpath = ".//*[@id='description']")
    public WebElement orderDetailsField;

    @FindBy(xpath = ".//*[@id='new_order']/form/div[2]/div[2]/div/a")
    public WebElement stopWordsAllert;

    @FindBy(className = "fileupload blue")
    public WebElement fileUpload;

    @FindBy(className = ".//*[@id='photos']//span[2]")
    public List<WebElement> uploadedFilesNames;

    @FindBy(className = "progress-bar")
    public WebElement progressBar;

    @FindBy(id = "progress")
    public WebElement progressCounter;

    @FindBy(id = "date_deadline")
    public WebElement deadlineField;

    @FindBy(xpath = ".//*[@class='cell3 timezone m_t-10 p_l-10']")
    public WebElement timezone;

    @FindBy(xpath = ".//*[@id='categories']")
    public WebElement categoriesOfWritingSelect;

    @FindBy(xpath = ".//*[@id='languages']")
    public WebElement languegesOfWritingSelect;

    @FindBy(xpath = ".//*[@id='new_order']/form/div[6]/div[2]/div/button")
    public WebElement expertisesSelect;

    @FindBy(xpath = ".//*[@id='new_order']/form/div[6]/div[2]/div/div/ul/li")
    public List<WebElement> expertisesElements;

    @FindBy(xpath = ".//*[@id='new_order']/form/div[8]//ul/li[1]/label")
    public WebElement perWordLable;

    @FindBy(xpath = ".//*[@id='new_order']/form/div[8]/div[2]/div[1]/ul/li[2]/label")
    public WebElement perOrderLable;

    @FindBy(xpath = ".//*[@id='price']")
    public WebElement priceInRupeeField;

    @FindBy(xpath = ".//*[@id='price-currency']")
    public WebElement priceInUSDField;

    @FindBy(xpath = ".//*[@id='articles_quantity']")
    public WebElement articlesQuantity;

    @FindBy(xpath = ".//*[@id='min']")
    public WebElement wordsRequired;

    @FindBy(xpath = ".//*[@id='new_order']/form/div[11]/div[2]/ul/li[1]/label")
    public WebElement visibilityForAll;

    @FindBy(xpath = ".//*[@id='new_order']/form/div[11]/div[2]/ul/li[2]/label")
    public WebElement visibilityForSpecific;

    @FindBy(xpath = ".//*[@id='js-copywriters']")
    public WebElement specificWritersField;

    @FindBy(xpath = ".//*[@id='new_order']/form/div[11]/div[2]/ul/li[3]/label")
    public WebElement visibilityForFavourites;

    @FindBy(xpath = ".//*[@id='c_ord_form']")
    public WebElement publishButton;

    @FindBy(xpath = ".//*[@id='max-count']")
    public WebElement totalPrice;

    @FindBy(xpath = "html//td[contains(@class, 'xdsoft_current')]")
    public WebElement currentOrderDay;

    @FindBy(xpath = "html/body/div[2]/div[2]/div/div[1]/div[contains(@class, 'xdsoft_current')]")
    public WebElement currentOrderTime;


    public String setOrderNameField(String orderName, String id) {
        orderNameField.clear();
        orderNameField.sendKeys(orderName + id);
        return orderNameField.getAttribute("value");
    }

    public String setDetailsField(String description) {
        orderDetailsField.clear();
        orderDetailsField.sendKeys(description);
        return orderDetailsField.getAttribute("value");
    }

    public String setWordsRequiredField(String words) {
        wordsRequired.clear();
        wordsRequired.sendKeys(words);
        return wordsRequired.getAttribute("value");
    }

    public String setPriceInRupeeField(String price) {
        priceInRupeeField.clear();
        priceInRupeeField.sendKeys(price);
        return priceInRupeeField.getAttribute("value");
    }

    public String setPriceInUSDField(String price) {
        priceInUSDField.clear();
        priceInUSDField.sendKeys(price);
        return priceInUSDField.getAttribute("value");
    }

    public String randomSelectWritingCategories(){

        Select selectWritingCategories = new Select(categoriesOfWritingSelect);
        selectWritingCategories.selectByIndex(new Random().nextInt(selectWritingCategories.getOptions().size()));
        return categoriesOfWritingSelect.getAttribute("value");
    }

    public String randomSelectLanguage(){

        Select selectLanguages = new Select(languegesOfWritingSelect);
        selectLanguages.selectByIndex(new Random().nextInt(selectLanguages.getOptions().size()));
        return languegesOfWritingSelect.getAttribute("value");
    }

    public String xDateBuilder(String day) {

        String xpath = "html//td[.//text()[contains(., '";
        String t = day + "')]] [not(contains(@class, 'xdsoft_disabled'))]";
        return xpath + t;
    }

    public String getCurrentTimezone(){
        return timezone.getText();
    }

    public String getRupeeOrderValue() {
        return $(priceInRupeeField).shouldBe(visible).getAttribute("value");
    }

    public String getDollarOrderValue() {
        return $(priceInUSDField).shouldBe(visible).getAttribute("value");
    }

    public String getStopWordsAllert() {
        return $(stopWordsAllert).shouldBe(visible).getText();
    }

    public String getOrderTotalPriceValue() {
        return $(totalPrice).shouldBe(visible).getText();
    }

    public void uploadFileToOrder(String filepath) {
        fileUpload.sendKeys(filepath);
    }

    public void waitForProgressBarWhenUploadingFilesToNewOrder() {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(progressBar));
        $(progressCounter).shouldHave(text("100%"));
    }

    public void setOrderPricePer(String perWhat){

      if(perWhat.contains("word"))
           $(perWordLable).click();
      else $(perOrderLable).click();
    }

    public String setVisibility(String visibility){

        if(visibility.equalsIgnoreCase("all")){
           visibilityForAll.click();
            return visibilityForAll.getText();

        }else if (visibility.equalsIgnoreCase("favorites")){
                  visibilityForFavourites.click();
            return "Favorites";

        }else visibilityForSpecific.click();
              $(specificWritersField).shouldBe(visible).sendKeys(visibility);
        return $(By.xpath(".//*[@id='js-visibility-copywriters']/a/b")).getText();
    }

    public String setArticlesQuantity(String articles){
        $(articlesQuantity).shouldBe(visible).sendKeys(articles);
        return articlesQuantity.getAttribute("value");
    }

    public String chooseExpertise(String method){

        expertisesSelect.click();

        if (method.equalsIgnoreCase("random")) {
            WebElement currentExpertise =
                    expertisesElements.get(new Random().nextInt(expertisesElements.size()));
            currentExpertise.click();
            expertisesSelect.click();
            return expertisesSelect.getAttribute("value");

        } else if(method.equalsIgnoreCase("all")){

            for (WebElement expertises : expertisesElements){
                $(expertises).shouldBe(visible).click();
            }
        }
        else for (WebElement exp : expertisesElements){

            if(exp.getText().equalsIgnoreCase(method))
                exp.click();
            expertisesSelect.click();
            return expertisesSelect.getAttribute("value");
        }
        return "Sorry something went wrong and expertises did not select";
    }

    public OrderWorkFlow clickOnPublishButton(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        publishButton.click();
        OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
        wait.until(ExpectedConditions.visibilityOf(orderWorkFlow.statusLable));
        wait.until(ExpectedConditions.visibilityOf(orderWorkFlow.orderDeadline));

        return orderWorkFlow;
    }


    public void setOrder(OrderObject order, String filepath, String per, String visibility, String articles) throws InterruptedException {

        String random = "random";
        String id = CreateNewOrderHelper.randomID();

        order.setOrderName(setOrderNameField(order.getOrderName(), id));
        order.setOrderDetails(setDetailsField(order.getOrderDetails()));

        uploadFileToOrder(filepath);
        $(progressCounter).shouldHave(text("100%"));
        isFileUploaded(filepath, uploadedFilesNames);

        $(deadlineField).shouldBe(visible).click();
        driver.findElement(By.xpath(xDateBuilder(CreateNewOrderHelper.getDay()))).click();
        $(currentOrderTime).click();
        $(orderDetailsField).click();

        order.setOrderCategoryOfWriting(randomSelectWritingCategories());
        chooseExpertise(random);
        order.setOrderLanguage(randomSelectLanguage());

        setOrderPricePer(per);
        order.setOrderDollarPrice(setPriceInUSDField(order.getOrderDollarPrice()));
        order.setOrderArticlesQuantity(setArticlesQuantity(articles));
        order.setOrderWordsRequired(setWordsRequiredField(order.getOrderWordsRequired()));
        order.setOrderVisibility(setVisibility(visibility));


        order.setOrderValueInRupee(getRupeeOrderValue());
        order.setOrderValueInDollars(getOrderTotalPriceValue());

    }

    public NewOrderPage(WebDriver driver) {
        super(driver);
    }
}
