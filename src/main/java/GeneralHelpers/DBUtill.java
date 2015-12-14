package GeneralHelpers;

import Entities.DBParams;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static GeneralHelpers.PropertiesLoader.propertyXMLoader;

/**
 * Created by DeBeers on 01.12.2015.
 */
public class DBUtill {

    public DBParams initDBConnection() throws IOException {
        Properties props =  propertyXMLoader(System.getProperty("user.dir")
                + "\\src\\main\\java\\Randomizers\\SettingsXML\\DB_CONNECTION.xml");

        return new DBParams(
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

        DBParams dbParams = initDBConnection();
        Connection dbConnection = null;
        try {
            Class.forName(dbParams.getDB_DRIVER());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(

                    dbParams.getDB_CONNECTION(),
                    dbParams.getDB_USER(),
                    dbParams.getDB_PASSWORD());

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
