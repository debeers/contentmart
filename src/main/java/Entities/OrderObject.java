package Entities;

public class OrderObject {
    private final String ordName;
    private final String desc;
    private final String wordsReq;
    private final String price;

    public OrderObject(String ordName, String desc, String wordsReq, String price) {
        this.ordName = ordName;
        this.desc = desc;
        this.wordsReq = wordsReq;
        this.price = price;
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
}
