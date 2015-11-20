package Entities;

public class OrderObject {


    private String timezone                    = "";
    private String orderName                   = "";
    private String orderStatus                 = "";
    private String orderDetails                = "";
    private String orderLanguage               = "";
    private String orderSystemID               = "";
    private String orderDeadLine               = "";
    private String orderPublicDate             = "";
    private String orderRupeePrice             = "";
    private String orderVisibility             = "";
    private String orderDollarPrice            = "";
    private String orderValueInRupee           = "";
    private String orderWordsRequire           = "";
    private String totalBalanceBefore          = "";
    private String orderValueInDollars         = "";
    private String orderArticlesQuantity       = "";
    private String orderCategoryOfWriting      = "";
    private String totalBalanceAfterBlocking   = "";
    private String totalBalanceAfterUnBlocking = "";





    public OrderObject() {

    }

    public OrderObject(String orderName, String orderDetails, String orderWordsRequire, String orderDollarPrice) {

        this.orderName = orderName;
        this.orderDetails = orderDetails;
        this.orderWordsRequire = orderWordsRequire;
        this.orderDollarPrice = orderDollarPrice;
    }

    public OrderObject(String totalBalanceBefore, String totalBalanceAfterBlocking, String totalBalanceAfterUnBlocking, String orderName,
                       String orderDetails, String orderWordsRequire, String orderDollarPrice, String orderRupeePrice, String orderSystemID,
                       String orderStatus, String orderPublicDate, String orderCategoryOfWriting, String orderDeadLine, String orderVisibility,
                       String orderValueInRupee, String orderValueInDollars, String timezone, String orderLanguage, String orderArticlesQuantity) {

        this.timezone                    = timezone;
        this.orderName                   = orderName;
        this.orderStatus                 = orderStatus;
        this.orderDetails                = orderDetails;
        this.orderLanguage               = orderLanguage;
        this.orderDeadLine               = orderDeadLine;
        this.orderSystemID               = orderSystemID;
        this.orderRupeePrice             = orderRupeePrice;
        this.orderVisibility             = orderVisibility;
        this.orderPublicDate             = orderPublicDate;
        this.orderDollarPrice            = orderDollarPrice;
        this.orderWordsRequire           = orderWordsRequire;
        this.orderValueInRupee           = orderValueInRupee;
        this.totalBalanceBefore          = totalBalanceBefore;
        this.orderValueInDollars         = orderValueInDollars;
        this.orderArticlesQuantity       = orderArticlesQuantity;
        this.orderCategoryOfWriting      = orderCategoryOfWriting;
        this.totalBalanceAfterBlocking   = totalBalanceAfterBlocking;
        this.totalBalanceAfterUnBlocking = totalBalanceAfterUnBlocking;

    }

    public String getOrderArticlesQuantity(){
        return orderArticlesQuantity;
    }

    public void setOrderArticlesQuantity(String orderArticlesQuantity){
        this.orderArticlesQuantity = orderArticlesQuantity;
    }


    public String getOrderLanguage(){
        return orderLanguage;
    }

    public void setOrderLanguage(String orderLanguage){
        this.orderLanguage = orderLanguage;
    }

    public String getTimezone(){
        return timezone;
    }

    public void setTimezone(String timezone){
        this.timezone = timezone;
    }

    public String getTotalBalanceBefore() {

        return totalBalanceBefore;
    }

    public String getOrderDollarPrice() {

        return orderDollarPrice;
    }

    public String getOrderCategoryOfWriting(){
        return orderCategoryOfWriting;
    }

    public void setOrderCategoryOfWriting(String orderCategoryOfWriting){
        this.orderCategoryOfWriting = orderCategoryOfWriting;
    }

    public void setOrderDollarPrice(String orderDollarPrice) {

        this.orderDollarPrice = orderDollarPrice;
    }

    public void setTotalBalanceBefore(String totalBalanceBefore) {

        this.totalBalanceBefore = totalBalanceBefore;
    }

    public void setOrderWordsRequired(String orderWordsRequire) {

        this.orderWordsRequire = orderWordsRequire;
    }

    public String getOrderName() {

        return orderName;
    }

    public void setOrderName(String orderName){
        this.orderName = orderName;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails){
        this.orderDetails = orderDetails;
    }

    public String getOrderWordsRequired() {

        return orderWordsRequire;
    }

    public String getOrderRupeePrice() {

        return orderRupeePrice;
    }


    public String getOrderSystemID() {

        return orderSystemID;
    }

    public String getOrderStatus() {

        return orderStatus;
    }

    public String getOrderPublicDate() {

        return orderPublicDate;
    }

    public String getOrderDeadLine() {

        return orderDeadLine;
    }

    public String getOrderValueInRupee() {

        return orderValueInRupee;
    }

    public void setOrderSystemID(String orderSystemID) {

        this.orderSystemID = orderSystemID;
    }

    public void setOrderStatus(String orderStatus) {

        this.orderStatus = orderStatus;
    }

    public void setOrderPublicDate(String orderPublicDate) {

        this.orderPublicDate = orderPublicDate;
    }

    public void setOrderDeadLine(String orderDeadLine) {

        this.orderDeadLine = orderDeadLine;
    }

    public void setOrderVisibility(String orderVisibility) {

        this.orderVisibility = orderVisibility;
    }

    public void setOrderValueInRupee(String entityOrderValue) {

        this.orderValueInRupee = entityOrderValue;
    }

    public String getOrderVisibility() {

        return orderVisibility;
    }

    public String getTotalBalanceAfterBlocking() {

        return totalBalanceAfterBlocking;
    }

    public void setTotalBalanceAfterBlocking(String totalBalanceAfterBlocking) {

        this.totalBalanceAfterBlocking = totalBalanceAfterBlocking;
    }

    public String getTotalBalanceAfterUnBlocking() {

        return totalBalanceAfterUnBlocking;
    }

    public void setTotalBalanceAfterUnBlocking(String totalBalanceAfterUnBlocking) {

        this.totalBalanceAfterUnBlocking = totalBalanceAfterUnBlocking;
    }

    public String getOrderValueInDollars() {

        return orderValueInDollars;
    }

    public void setOrderValueInDollars(String orderValueInDollars) {
        this.orderValueInDollars = orderValueInDollars;
    }
}