package Entities;

import java.util.List;

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
    private String orderTotalPrice             = "";
    private String orderValueInRupee           = "";
    private String orderWordsRequire           = "";
    private String totalBalanceBefore          = "";
    private String orderValueInDollars         = "";
    private String orderArticlesQuantity       = "";
    private String orderCategoryOfWriting      = "";
    private String totalBalanceAfterBlocking   = "";
    private String totalBalanceAfterUnBlocking = "";
    private List<String> orderAvailebleExpertises  ;
    private List<String> orderAvailebleCategories  ;
    private List<String> orderAvailebleLanguages   ;
    private List<String> orderChosenExpertise      ;

    public OrderObject() {

    }

    public OrderObject(String orderName, String orderDetails, String orderWordsRequire, String orderTotalPrice) {

        this.orderName = orderName;
        this.orderDetails = orderDetails;
        this.orderWordsRequire = orderWordsRequire;
        this.orderTotalPrice = orderTotalPrice;
    }

    public OrderObject(String totalBalanceBefore, String totalBalanceAfterBlocking, String totalBalanceAfterUnBlocking, String orderName,
                       String orderDetails, String orderWordsRequire, String orderTotalPrice, String orderRupeePrice, String orderSystemID,
                       String orderStatus, String orderPublicDate, String orderCategoryOfWriting, String orderDeadLine, String orderVisibility,
                       String orderValueInRupee, String orderValueInDollars, String timezone, String orderLanguage, String orderArticlesQuantity,
                       List<String> orderAvailebleExpertises, List<String> orderAvailebleCategories, List<String> orderAvailebleLanguages,
                       List<String> orderChosenExpertise) {

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
        this.orderTotalPrice             = orderTotalPrice;
        this.orderWordsRequire           = orderWordsRequire;
        this.orderValueInRupee           = orderValueInRupee;
        this.totalBalanceBefore          = totalBalanceBefore;
        this.orderValueInDollars         = orderValueInDollars;
        this.orderArticlesQuantity       = orderArticlesQuantity;
        this.orderCategoryOfWriting      = orderCategoryOfWriting;
        this.totalBalanceAfterBlocking   = totalBalanceAfterBlocking;
        this.totalBalanceAfterUnBlocking = totalBalanceAfterUnBlocking;
        this.orderAvailebleExpertises    = orderAvailebleExpertises;
        this.orderAvailebleLanguages     = orderAvailebleLanguages;
        this.orderAvailebleCategories    = orderAvailebleCategories;
        this.orderChosenExpertise        = orderChosenExpertise;
    }


    public List<String> getOrderChosenExpertise() {
        return orderChosenExpertise;
    }

    public void setOrderChosenExpertise(List<String> orderChosenExpertise) {
        this.orderChosenExpertise = orderChosenExpertise;
    }

    public String getOrderArticlesQuantity(){
        return orderArticlesQuantity;
    }

    public void setOrderArticlesQuantity(String orderArticlesQuantity){
        this.orderArticlesQuantity = orderArticlesQuantity;
    }

    public List<String> getOrderAvailebleExpertises() {
        return orderAvailebleExpertises;
    }

    public void setOrderAvailebleExpertises(List<String> orderExpertises) {
        this.orderAvailebleExpertises = orderExpertises;
    }

    public List<String> getOrderAvailebleCategories() {
        return orderAvailebleCategories;
    }

    public void setOrderAvailebleCategories(List<String> orderAvailebleCategories) {
        this.orderAvailebleCategories = orderAvailebleCategories;
    }

    public List<String> getOrderAvailebleLanguages() {
        return orderAvailebleLanguages;
    }

    public void setOrderAvailebleLanguages(List<String> orderAvailebleLanguages) {
        this.orderAvailebleLanguages = orderAvailebleLanguages;
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

    public String getOrderTotalPrice() {

        return orderTotalPrice;
    }

    public String getOrderCategoryOfWriting(){
        return orderCategoryOfWriting;
    }

    public void setOrderCategoryOfWriting(String orderCategoryOfWriting){
        this.orderCategoryOfWriting = orderCategoryOfWriting;
    }

    public void setOrderTotalPrice(String orderTotalPrice) {

        this.orderTotalPrice = orderTotalPrice;
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