package PageObjects.Client;

import Entities.OrderObject;
import Helpers.DBUtill;
import Helpers.DateTimeUtils;
import PageObjects.General.OrderWorkFlow;
import PageObjects.General.TopMenuGeneralPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import static Helpers.Randomizers.createNewOrderName;
import static Actions.Client.CreateNewOrder.getExpertisesList;
import static Helpers.UploadingAndDownloadingFiles.uploadFilesByRobot;
import static Helpers.UploadingAndDownloadingFiles.getFileName;
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

    @FindBy(xpath = ".//*[@class='fileupload blue']")
    public WebElement fileUpload;

    @FindBy(className = ".//*[@id='photos']//span[2]")
    public List<WebElement> uploadedFilesNames;

    @FindBy(className = "progress-bar")
    public WebElement progressBar;

    @FindBy(className = "percent")
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

    @FindBy(xpath = ".//*[@id='new_order']/form/div[6]/div[2]/div/div/ul/li[not(contains(@class,'ms-no-results'))]")
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

    @FindBy(xpath = ".//div[@class = 'xdsoft_time xdsoft_current']")
    public WebElement currentOrderTime;

    public WebElement findUploadedFilesByXPath(WebElement element, String name) {
        $(element).shouldHave(text("100%"));
        return $(By.xpath(".//*[@id='photos']//span[2][contains(text(), '" + name + "')]")).shouldBe(visible);
    }

    public WebElement findUploadedFilesByXPathInPublished(String name) {

        return $(By.xpath(".//li/a[contains(text(), '" + name + "')]")).shouldBe(visible);
    }


    public String setOrderNameField(String orderName) {
        orderNameField.clear();
        orderNameField.sendKeys(orderName);
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

    public String setPriceInRupeeField(String price) throws InterruptedException {

        priceInRupeeField.clear();
        priceInRupeeField.sendKeys(price);
        System.out.println(priceInRupeeField.getAttribute("value") + "<<<========");
        Thread.sleep(5000);
        return priceInRupeeField.getAttribute("value");
    }

    public String setPriceInUSDField(String price) {

        priceInUSDField.clear();
        priceInUSDField.sendKeys(price);
        return priceInUSDField.getAttribute("value");
    }

    public String selectWritingCategories(String category){
        Select selectWritingCategories = new Select(categoriesOfWritingSelect);

        if(category == null || category.equalsIgnoreCase("random")){
            selectWritingCategories.selectByIndex(1+ new Random().nextInt(selectWritingCategories.getOptions().size() -1));

        }else
            selectWritingCategories.selectByVisibleText(category);

        return category;
    }

    public String randomSelectLanguage(){

        Select selectLanguages = new Select(languegesOfWritingSelect);
        selectLanguages.selectByIndex(new Random().nextInt(selectLanguages.getOptions().size()));
        return languegesOfWritingSelect.getText();
    }

    public String selectLanguageByParameter(String lang){

        Select selectLanguages = new Select(languegesOfWritingSelect);
        selectLanguages.selectByVisibleText(lang);
        return languegesOfWritingSelect.getText();
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
            return "All";

        }else if (visibility.equalsIgnoreCase("favorites")){
                  visibilityForFavourites.click();
            return "Favorites";

        }else visibilityForSpecific.click();
              $(specificWritersField).shouldBe(visible).sendKeys(visibility);
        return $(By.xpath(".//*[@id='js-visibility-copywriters']/a/b")).getText();
    }

    public String setArticlesQuantity(String articles){
        articlesQuantity.clear();
        $(articlesQuantity).shouldBe(visible).sendKeys(articles);
        return articlesQuantity.getAttribute("value");
    }

    public List<String> chooseExpertise(String method){
        List<String> exp = new ArrayList<>();
        expertisesSelect.click();

        String selectedExpertises = ($(By.xpath(".//*[@id='new_order']/form/div[6]/div[2]/div/button/span")).getText());

        if (method.equalsIgnoreCase("random")) {
            WebElement currentExpertise =
                    expertisesElements.get(new Random().nextInt(expertisesElements.size()));
            currentExpertise.click();
            expertisesSelect.click();
            exp.add(selectedExpertises);
            return exp;

        } else if(method.equalsIgnoreCase("all")){

            for (WebElement expertises : expertisesElements){
                $(expertises).shouldBe(visible).click();
            }
            exp.add($(By.xpath(".//*[@id='new_order']/form/div[6]/div[2]/div/button/span")).getText());
            expertisesSelect.click();
            return exp;
        }
        else for (WebElement exprt : expertisesElements){

            if(exprt.getText().equalsIgnoreCase(method))
                exprt.click();
                expertisesSelect.click();
                exp.add($(By.xpath(".//*[@id='new_order']/form/div[6]/div[2]/div/button/span")).getText());
            return exp;
        }
        return null;
    }

    public OrderWorkFlow clickOnPublishButton() throws InterruptedException {

        $(publishButton).shouldBe(visible).click();
        return  new OrderWorkFlow(driver);
    }

    public String setRandomDeadline() throws InterruptedException {

        $(deadlineField).shouldBe(visible).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xDateBuilder(DateTimeUtils.getDay()))).click();
        currentOrderTime.click();
        orderDetailsField.click();

        return $(deadlineField).shouldBe(visible).getAttribute("value");
    }

    public void setOrder(OrderObject order, Properties props) throws InterruptedException, AWTException, IOException, SQLException {

        order.setOrderName(
                setOrderNameField(
                        createNewOrderName())
        );

        order.setOrderDetails(
                setDetailsField(
                        props.getProperty("OrderDetails"))
        );

        uploadFilesByRobot(
                fileUpload, System.getProperty("user.dir") + props.getProperty("FileForUpload")
        );

        findUploadedFilesByXPath(
                progressCounter,
                getFileName(
                        props.getProperty("FileForUpload"))
        );

        order.setOrderDeadLine(
                setRandomDeadline()
        );

        order.setOrderCategoryOfWriting(
                selectWritingCategories(
                        props.getProperty("CategoriesOfWriting"))
        );

        order.setOrderAvailebleExpertises(
                getExpertisesList());


        chooseExpertise(
                props.getProperty("Expertises")
        );

        order.setOrderLanguage(
                props.getProperty("Language")
                        .equalsIgnoreCase("random") ? randomSelectLanguage() :
                        selectLanguageByParameter(props.getProperty("Language"))
        );

        setOrderPricePer(
                props.getProperty("PricePerWordOrPerOrder")
        );

        order.setOrderArticlesQuantity(
                setArticlesQuantity(
                        props.getProperty("ArticlesQuantity"))
        );

        order.setOrderWordsRequired(
                setWordsRequiredField(
                        props.getProperty("WordsRequired"))
        );

        order.setOrderVisibility(
                setVisibility(
                        props.getProperty("VisibilityForAll"))
        );

            order.setOrderValueInRupee(
                setPriceInRupeeField(
                        props.getProperty("PriceInRupee"))
        );

    }

    public static int checkForWordsRequired(String wordsRequired, String articles){
       return Integer.parseInt(wordsRequired) * Integer.parseInt(articles);
    }


    public NewOrderPage(WebDriver driver) {
        super(driver);
    }
}
