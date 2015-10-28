package Entities;

public class OrderObject {


    private String totalBalanceBefore = "";
    private String totalBalanceAfterBlocking = "";
    private String totalBalanceAfterUnBlocking = "";
    private String ordName = "";
    private String desc = "";
    private String wordsReq = "";
    private String rupeePrice = "";
    private String dollarPrice = "";
    private String entityOrderName = "";
    private String entityOrderSystemID = "";
    private String entityOrderStatus = "";
    private String entityOrderDescription = "";
    private String entityOrderPublicDate = "";
    private String entityOrderDeadLine = "";
    private String entityOrderVisibility = "";
    private String orderValueInRupee = "";
    private String orderValueInDollars = "";


    public OrderObject() {

    }

    public OrderObject(String ordName, String desc, String wordsReq, String dollarPrice) {

        this.ordName = ordName;
        this.desc = desc;
        this.wordsReq = wordsReq;
        this.dollarPrice = dollarPrice;
    }

    public OrderObject(String totalBalanceBefore, String totalBalanceAfterBlocking, String totalBalanceAfterUnBlocking,
                       String ordName, String desc, String wordsReq, String dollarPrice, String rupeePrice, String entityOrderName,
                       String entityOrderSystemID, String entityOrderStatus, String entityOrderDescription, String entityOrderPublicDate,
                       String entityOrderDeadLine, String entityOrderVisibility, String orderValueInRupee, String orderValueInDollars) {


        this.totalBalanceBefore = totalBalanceBefore;
        this.totalBalanceAfterBlocking = totalBalanceAfterBlocking;
        this.totalBalanceAfterUnBlocking = totalBalanceAfterUnBlocking;
        this.ordName = ordName;
        this.desc = desc;
        this.wordsReq = wordsReq;
        this.dollarPrice = dollarPrice;
        this.rupeePrice = rupeePrice;
        this.entityOrderName = entityOrderName;
        this.entityOrderSystemID = entityOrderSystemID;
        this.entityOrderStatus = entityOrderStatus;
        this.entityOrderDescription = entityOrderDescription;
        this.entityOrderPublicDate = entityOrderPublicDate;
        this.entityOrderDeadLine = entityOrderDeadLine;
        this.entityOrderVisibility = entityOrderVisibility;
        this.orderValueInRupee = orderValueInRupee;
        this.orderValueInDollars = orderValueInDollars;

    }

    public String getTotalBalanceBefore() {

        return totalBalanceBefore;
    }

    public String getDollarPrice() {

        return dollarPrice;
    }

    public void setDollarPrice(String dollarPrice) {

        this.dollarPrice = dollarPrice;
    }

    public void setTotalBalanceBefore(String totalBalanceBefore) {

        this.totalBalanceBefore = totalBalanceBefore;
    }

    public void setWordsReq(String wordsReq) {

        this.wordsReq = wordsReq;
    }

    public String getOrdName() {

        return ordName;
    }

    public String getDesc() {

        return desc;
    }

    public String getWordsReq() {

        return wordsReq;
    }

    public String getRupeePrice() {

        return rupeePrice;
    }

    public String getEntityOrderName() {

        return entityOrderName;
    }

    public String getEntityOrderSystemID() {

        return entityOrderSystemID;
    }

    public String getEntityOrderStatus() {

        return entityOrderStatus;
    }

    public String getEntityOrderDescription() {

        return entityOrderDescription;
    }

    public String getEntityOrderPublicDate() {

        return entityOrderPublicDate;
    }

    public String getEntityOrderDeadLine() {

        return entityOrderDeadLine;
    }

    public String getOrderValueInRupee() {

        return orderValueInRupee;
    }

    public void setEntityOrderName(String entityOrderName) {

        this.entityOrderName = entityOrderName;
    }

    public void setEntityOrderSystemID(String entityOrderSystemID) {

        this.entityOrderSystemID = entityOrderSystemID;
    }

    public void setEntityOrderStatus(String entityOrderStatus) {

        this.entityOrderStatus = entityOrderStatus;
    }

    public void setEntityOrderDescription(String entityOrderDescription) {

        this.entityOrderDescription = entityOrderDescription;
    }

    public void setEntityOrderPublicDate(String entityOrderPublicDate) {

        this.entityOrderPublicDate = entityOrderPublicDate;
    }

    public void setEntityOrderDeadLine(String entityOrderDeadLine) {

        this.entityOrderDeadLine = entityOrderDeadLine;
    }

    public void setEntityOrderVisibility(String entityOrderVisibility) {

        this.entityOrderVisibility = entityOrderVisibility;
    }

    public void setOrderValueInRupee(String entityOrderValue) {

        this.orderValueInRupee = entityOrderValue;
    }

    public String getEntityOrderVisibility() {

        return entityOrderVisibility;
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