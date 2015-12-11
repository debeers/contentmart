package GeneralHelpers;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static GeneralHelpers.PropertiesLoader.propertyXMLoader;
import static SQLRepo.General.getOrderCategories;

/**
 * Created by DeBeers on 01.12.2015.
 */
public class DBUtill {

    private String DB_USER;
    private String DB_DRIVER;
    private String DB_PASSWORD;
    private String DB_CONNECTION;

    public DBUtill(String DB_USER, String DB_DRIVER, String DB_PASSWORD, String DB_CONNECTION) {

        this.DB_USER = DB_USER;
        this.DB_DRIVER = DB_DRIVER;
        this.DB_PASSWORD = DB_PASSWORD;
        this.DB_CONNECTION = DB_CONNECTION;
    }

    public DBUtill(){};

    public String getDB_USER() {
        return DB_USER;
    }

    public void setDB_USER(String DB_USER) {
        this.DB_USER = DB_USER;
    }

    public String getDB_DRIVER() {
        return DB_DRIVER;
    }

    public void setDB_DRIVER(String DB_DRIVER) {
        this.DB_DRIVER = DB_DRIVER;
    }

    public String getDB_PASSWORD() {
        return DB_PASSWORD;
    }

    public void setDB_PASSWORD(String DB_PASSWORD) {
        this.DB_PASSWORD = DB_PASSWORD;
    }

    public String getDB_CONNECTION() {
        return DB_CONNECTION;
    }

    public void setDB_CONNECTION(String DB_CONNECTION) {
        this.DB_CONNECTION = DB_CONNECTION;
    }

    public DBUtill initDB() throws IOException {
        Properties props =  propertyXMLoader(System.getProperty("user.dir") + "\\src\\main\\java\\GeneralHelpers\\SettingsXML\\DB_CONNECTION.xml");

        return new DBUtill(
                props.getProperty("DB_USER"),
                props.getProperty("DB_DRIVER"),
                props.getProperty("DB_PASSWORD"),
                props.getProperty("DB_CONNECTION")
        );
    }

    public ResultSet getResultSet(String sqlQuery) throws IOException, SQLException {

        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        return resultSet;
    }


    public void executeUpdate(String insertTableSQL) throws SQLException, IOException {

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


    public Connection getDBConnection() throws IOException {

        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(

                    DB_CONNECTION,
                    DB_USER,
                    DB_PASSWORD);

            return dbConnection;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }


    public String getColumn(String query, String column) {

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
