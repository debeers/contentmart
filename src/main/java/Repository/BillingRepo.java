package Repository;

import Helpers.DBUtill;

/**
 * Created by DeBeers on 16.12.2015.
 */
public class BillingRepo {

    public static String getLastUpdatedCurrencyRate() {
        DBUtill db = new DBUtill();
        return db.getColumn(
                "SELECT VALUE\n" +
                "FROM currency_rate\n" +
                "WHERE email=(SELECT MAX(email) FROM currency_rate)", "VALUE"
        );
    }
}
