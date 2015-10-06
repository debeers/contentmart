package Entities;

public class OrderObject {


    private String totalBalanceBefore = "";
    private String totalBalanceAfterBlocking = "";
    private String totalBalanceAfterUnBlocking = "";

    private String orderValue = "";

    private String ordName = "";
    private String desc = "";
    private String wordsReq = "";
    private String price = "";

    private String entityOrderName = "";
    private String entityOrderSystemID = "";
    private String entityOrderStatus = "";
    private String entityOrderDescription = "";
    private String entityOrderPublicDate = "";
    private String entityOrderDeadLine = "";
    private String entityOrderVisibility = "";
    private String entityOrderValue = "";


    public OrderObject() {

    }

    public OrderObject(String ordName, String desc, String wordsReq, String price) {

        this.ordName = ordName;
        this.desc = desc;
        this.wordsReq = wordsReq;
        this.price = price;

    }


    public OrderObject(String totalBalanceBefore, String totalBalanceAfterBlocking, String totalBalanceAfterUnBlocking,
                       String orderValue, String ordName, String desc, String wordsReq, String price, String entityOrderName,
                       String entityOrderSystemID, String entityOrderStatus, String entityOrderDescription, String entityOrderPublicDate,
                       String entityOrderDeadLine, String entityOrderVisibility, String entityOrderValue) {


        this.totalBalanceBefore = totalBalanceBefore;
        this.totalBalanceAfterBlocking = totalBalanceAfterBlocking;
        this.totalBalanceAfterUnBlocking = totalBalanceAfterUnBlocking;
        this.orderValue = orderValue;

        this.ordName = ordName;
        this.desc = desc;
        this.wordsReq = wordsReq;
        this.price = price;

        this.entityOrderName = entityOrderName;
        this.entityOrderSystemID = entityOrderSystemID;
        this.entityOrderStatus = entityOrderStatus;
        this.entityOrderDescription = entityOrderDescription;
        this.entityOrderPublicDate = entityOrderPublicDate;
        this.entityOrderDeadLine = entityOrderDeadLine;
        this.entityOrderVisibility = entityOrderVisibility;
        this.entityOrderValue = entityOrderValue;

    }


    public String getTotalBalanceBefore() {

        return totalBalanceBefore;
    }

    public String getOrderValue() {

        return orderValue;
    }


    public void setTotalBalanceBefore(String totalBalanceBefore) {

        this.totalBalanceBefore = totalBalanceBefore;
    }

    public void setOrderValue(String orderValue) {

        this.orderValue = orderValue;
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

    public String getPrice() {

        return price;
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

    public String getEntityOrderValue() {

        return entityOrderValue;
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

    public void setEntityOrderValue(String entityOrderValue) {

        this.entityOrderValue = entityOrderValue;
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
}
