package GeneralHelpers;

/**
 * Created by ilya on 07.09.2015.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCutil {





    public static void getJDBC(String query, String column){




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
        try{
            c = DriverManager.getConnection(url, user, password);
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getString(column));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{

            try {
                if(c != null)
                    c.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }




}
