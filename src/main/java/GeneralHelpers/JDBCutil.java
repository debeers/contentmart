package GeneralHelpers;

/**
 * Created by ilya on 07.09.2015.
 */
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JDBCutil {


    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/content1";
    private static final String DB_USER = "dev_contentmart";
    private static final String DB_PASSWORD = "iequ5eYeev0l";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    public static void insertRecordIntoDbUserTable(String insertTableSQL) throws SQLException {

        Connection dbConnection = null;
        Statement statement = null;


        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(insertTableSQL);
            statement.executeUpdate(insertTableSQL);

            System.out.println("Record is inserted into DBUSER table!");

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

    public static Connection getDBConnection() {

        Connection dbConnection = null;

        try {

            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }

    private static String getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return dateFormat.format(today.getTime());

    }


    public static void executePrepeared(String query) throws SQLException {

        Connection dbConnection;
        PreparedStatement statement = null;


            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement(query);



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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
