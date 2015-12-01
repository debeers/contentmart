package Tests;

import GeneralHelpers.DBUtill;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static GeneralHelpers.PropertiesLoader.propertyXMLoader;

/**
 * Created by DeBeers on 01.12.2015.
 */
public class debug {


    @Test(groups = {"Fast_And_Furious_Smoke_1.0"})
    public void debug() throws InterruptedException, IOException, SQLException {

        String createNewUserQuery = "SELECT email FROM users WHERE email = 'clint.for.number@yandex.com'";

        DBUtill dbUtill = new DBUtill();
        dbUtill = dbUtill.onCreate("\\src\\main\\java\\GeneralHelpers\\SettingsXML\\DB_CONNECTION.xml");

        System.out.println(dbUtill.getColumn(createNewUserQuery, "email"));


    }
}
