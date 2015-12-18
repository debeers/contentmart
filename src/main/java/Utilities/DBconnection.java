package Utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by DeBeers on 18.12.2015.
 */
public class DBconnection {

    public Connection connection;
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public DBconnection(){}

    public Connection initDBConnection(Properties properties) throws SQLException, IOException, ClassNotFoundException {

     //   Class.forName(properties.getProperty("DB_DRIVER"));

        connection = DriverManager.getConnection(
                properties.getProperty("DB_CONNECTION"),
                properties.getProperty("DB_USER"),
                properties.getProperty("DB_PASSWORD")
        );

        System.out.println("xml found=======================================");
        return connection;
    }
}
