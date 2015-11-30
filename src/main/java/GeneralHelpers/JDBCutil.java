package GeneralHelpers;

/**
 * Created by ilya on 07.09.2015.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import static GeneralHelpers.PropertiesLoader.propertyXMLoader;

public class JDBCutil {

    private static Properties props() throws IOException {
        Properties props =  propertyXMLoader(System.getProperty("user.dir") +
                "\\src\\main\\java\\GeneralHelpers\\SettingsXML\\MainSettings.xmll");
        return props;
    }

    public static void executeUpdateDbUserTable(String insertTableSQL) throws SQLException, IOException {

        Connection dbConnection = null;
        Statement statement = null;


        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(insertTableSQL);
            statement.executeUpdate(insertTableSQL);

            System.out.println("Record updated!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

    }

    public static Connection getDBConnection() throws IOException {

        Connection dbConnection = null;

        try {

            Class.forName(props().getProperty("DB_DRIVER"));

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            dbConnection = DriverManager.getConnection(

                    props().getProperty("DB_CONNECTION"),
                    props().getProperty("DB_USER"),
                    props().getProperty("DB_PASSWORD"));

            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }


    public static String executeQuery(String query, String column) {

        Connection dbConnection;
        Statement statement;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString(column));
                return rs.getString(column);
            }
            dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
