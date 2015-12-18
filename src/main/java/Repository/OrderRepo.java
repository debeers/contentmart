package Repository;

import Utilities.DBUtill;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DeBeers on 16.12.2015.
 */
public class OrderRepo {

    public static ResultSet getOrderCategories() throws IOException, SQLException {
        DBUtill db = new DBUtill();
        return db.getResultSet("SELECT NAME FROM writing_category");
    }

    public static ResultSet getOrderExpertises() throws IOException, SQLException {
        DBUtill db = new DBUtill();
        return db.getResultSet("SELECT NAME FROM expertises");
    }

    public static ResultSet getOrderLanguages() throws IOException, SQLException {
        DBUtill db = new DBUtill();
        return db.getResultSet("SELECT name FROM language");
    }
}
