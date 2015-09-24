package Actions.Writer;

/**
 * Created by CMG_TEST on 09.09.2015.
 */
public class WriterActionsWithOrders {





//    public static WriterAllOrdersPage writerGoToInProgressOrders(WebDriver driver, LoginObject writerLogin) throws InterruptedException {
//        if(driver.getTitle() != "My Orders | ContentMart"){
//            logOut(driver);
//            loginAsWriter(driver, writerLogin);
//        }
//            WriterAllOrdersPage allOrders = clickOnInProgressLinkMyOrdersClient(driver);
//
//        return allOrders;
//    }


    //



//    public static OrderInfoPage FindCreatedOrderFromAllOrdersWriter(WebDriver driver, MyOrdersPage myOrders, String createdOrderName, LoginObject writerLogin){
//        WebDriverWait wait = new WebDriverWait(driver, 15);
//        if(driver.getTitle() != "My Orders | ContentMart"){
//            logOut(driver);
//            loginAsWriter(driver, writerLogin);
//        }
//            clickOnAllOrdersLeftMenuWriter(driver, myOrders);
//            searchBySearchEngineFromAllOrdersWriter(driver, createdOrderName);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(createdOrderName))).click();
//            waitForPageLoad(driver);
//            OrderInfoPage offerPage = new OrderInfoPage(driver);
//
//            String order = offerPage.orderName.getText();
//            Assert.assertEquals(order, createdOrderName, "ERROR: not your order opened!");
//            System.out.println("Balance successfully found!");
//
//        return offerPage;
//
//    }
}
