package Entities;

import java.lang.String;public class Order {

    private String entityOrderName = "";
    private String entityOrderSystemID = "";
    private String entityOrderStatus = "";
    private String entityOrderDescription = "";
    private String entityOrderPublicDate = "";
    private String entityOrderDeadLine = "";
    private String entityOrderVisibility = "";
    private String entityOrderValue = "";

    public Order() {

    }



    public Order(String entityOrderName, String entityOrderSystemID,
                 String entityOrderStatus, String entityOrderDescription, String entityOrderPublicDate,
                 String entityOrderDeadLine, String entityOrderVisibility, String entityOrderValue) {

        this.entityOrderName = entityOrderName;
        this.entityOrderSystemID = entityOrderSystemID;
        this.entityOrderStatus = entityOrderStatus;
        this.entityOrderDescription = entityOrderDescription;
        this.entityOrderPublicDate = entityOrderPublicDate;
        this.entityOrderDeadLine = entityOrderDeadLine;
        this.entityOrderVisibility = entityOrderVisibility;
        this.entityOrderValue = entityOrderValue;
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

    public void  setEntityOrderValue(String entityOrderValue) {

        this.entityOrderValue = entityOrderValue;
    }


    public String getEntityOrderVisibility() {

        return entityOrderVisibility;
    }








}
