package PageObjects.General;

import Entities.OrderObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static GeneralHelpers.GeneralWaits.waitForPageLoad;
import static GeneralHelpers.GeneralWaits.waitForTableLoad;

public class MyOrdersPage extends LeftMenuGeneralPage {


    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[2]/form/div/input[1]")
    public WebElement searchFieldMyOrders;

    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[2]/form/div/input[2]")
    public WebElement searchButtonMyOrders;

    @FindBy(xpath = ".//*[@id='order_status']/li[1]")
    public WebElement deals;

    @FindBy(xpath = ".//*[@id='order_status']/li[2]")
    public WebElement inProgressClient;

    @FindBy(xpath = "//div/div[3]/div//div/div[3]/ul/li[3]")
    public  WebElement inProgressWriter;

    @FindBy(xpath = ".//*[@id='order_status']/li[3]")
    public WebElement finished;

    @FindBy(xpath = "html/body/div/div[3]/div/div/div/div[1]/h1")
    public WebElement myOrdersHeader;

    @FindBy(name = "s")
    public WebElement searchField;

    @FindBy(className = "but_search")
    public WebElement searchButton;


///////////////////////////////////////   Orders WriterCheckVisibility   //////////////////////////////////////

    @FindBy(xpath = ".//*[@id='order_status']/li[1]")
    public WebElement draftLinkMyOrderClient;

    @FindBy(xpath = ".//*[@id='order_status']/li[2]")
    public  WebElement publishedLinkMyOrdersClient;

    @FindBy(xpath = ".//*[@id='order_status']/li[3]")
    public  WebElement inProgressLinkMyOrdersClient;

    @FindBy(xpath = ".//*[@id='order_status']/li[4]")
    public WebElement finishedLinkMyOrdersClient;





    public OrderInfoAndActions clickOnSetAsWinnerButtonAndAprooveMoneyBlocking() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html//table/tbody/tr/td[3]/a[1]"))).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='winner_form']/button[1]"))).click();
        waitForPageLoad(driver);
        OrderInfoAndActions bidPage = new OrderInfoAndActions(driver);

        return bidPage;
    }


    public OrderInfoAndActions writerClickOnCreatedOrderByClientToStartToWorking(OrderObject order) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(order.getEntityOrderName())))).click();
        waitForPageLoad(driver);
        OrderInfoAndActions orderInfoAndActions= new OrderInfoAndActions(driver);

        wait.until(ExpectedConditions.visibilityOf(orderInfoAndActions.orderName));
        wait.until(ExpectedConditions.visibilityOf(orderInfoAndActions.orderStatus));

        return orderInfoAndActions;
    }

    public  void clickOnPublishedLinkMyOrdersClient() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(publishedLinkMyOrdersClient)).click();
    }


    public  void clickOnInProgressLinkMyOrdersClient() {

        inProgressWriter.click();
        waitForPageLoad(driver);

        List<WebElement> tableDeals = driver.findElements(By.xpath("//table/tbody/tr"));
        waitForTableLoad(tableDeals);

    }


    public  void searchBySearchEngineMyOrdersWriter(MyOrdersPage myOrders, OrderObject orderObject){
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.visibilityOf(myOrders.searchFieldMyOrders)).sendKeys(orderObject.getEntityOrderName());
        wait.until(ExpectedConditions.elementToBeClickable(myOrders.searchButtonMyOrders)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("html//a[.//text()[contains(., '" + orderObject.getEntityOrderName() + "')]]")))).click();
        waitForPageLoad(driver);

    }




    public MyOrdersPage(WebDriver driver) {

        super(driver);
    }


}


