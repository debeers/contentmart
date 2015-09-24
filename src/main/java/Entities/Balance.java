package Entities;

/**
 * Created by DeBeers on 22.09.2015.
 */
public class Balance {

    private String totalBalance = "";
    private String orderValue = "";

    public Balance() {

    }

    public Balance(String totalBalance, String orderValue) {

        this.totalBalance = totalBalance;
        this.orderValue = orderValue;

    }


    public String getTotalBalance() {

        return totalBalance;
    }

    public String getOrderValue() {

        return orderValue;
    }






    public void setTotalBalance(String totalBalance) {

        this.totalBalance = totalBalance;
    }

    public void setOrderValue(String orderValue) {

        this.orderValue = orderValue;
    }

}
