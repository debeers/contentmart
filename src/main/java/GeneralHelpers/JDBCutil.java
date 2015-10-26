package GeneralHelpers;

/**
 * Created by ilya on 07.09.2015.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JDBCutil {


    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/MyDataBaseName";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    protected String insertTableSQL = "INSERT INTO DBUSER"
            + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
            + "(1,'mkyong','system', " + "to_date('"
            + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";


    private static void insertRecordIntoDbUserTable(String insertTableSQL) throws SQLException {

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

    private static Connection getDBConnection() {

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


    public static void getJDBC(String query, String column) {


        String user = "root";
        String password = "toor";
        String url = "jdbc:mysql://localhost:3306/bitnami_wordpress";
        String driver = "oracle.jdbc.driver.OracleDriver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        Connection c = null;
        try {
            c = DriverManager.getConnection(url, user, password);
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString(column));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (c != null)
                    c.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

}
