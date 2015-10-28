package GeneralHelpers;

import org.apache.commons.lang.RandomStringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static GeneralHelpers.JDBCutil.executeQuery;
import static GeneralHelpers.JDBCutil.getDBConnection;
import static GeneralHelpers.JDBCutil.insertRecordIntoDbUserTable;
import static org.apache.commons.lang.RandomStringUtils.*;

/**
 * Created by DeBeers on 28.10.2015.
 */
public class DBWorker {


    private static final String MAX_USER_ID = "SELECT id FROM users WHERE id=(SELECT MAX(id) FROM users)";
    private static final String CREATE_NEW_USER = "INSERT INTO `users` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    // values('98765443','Ilya','Ilya-Slabiy','Slabiy','1908-08-02','debeers@bigmir.net','','0','$2a$10$8AjxEfQy.QTLqioWFqK8j.XuMgG8kXwt4tizaT.4elFlEW2jlit8O','$2a$10$8AjxEfQy.QTLqioWFqK8jB$','2015-10-27 21:37:27','1',NULL,NULL,'','0',NULL,'0','1','ilya-slabiy','individual',NULL,NULL,'copywriter','862775793760125','2015-10-28 12:26:04','213.186.202.162','0','0','not_sent','0','321',NULL);



    @SuppressWarnings("ConstantConditions")
    public static String createMaxUserId(){

        int i = Integer.parseInt(executeQuery(MAX_USER_ID, "id"))+1;
        return Integer.toString(i);
    }

    private static String createTestUserName(){

        return "AutoBot-" + randomNumeric(4) + randomAlphabetic(3);
    }

    private static String setUserNickName(String role){

        if(role.equalsIgnoreCase("copywriter")){
        return "WriterBOT-" + randomNumeric(4) + randomAlphabetic(3);
        }else return "ClientBOT-" + randomNumeric(4) + randomAlphabetic(3);
    }

    public static void createUserBills(String id, String name) throws SQLException {

        String bill_1 = "insert into `billing`.`bills` (`id`, `bill_type`, `name`, `user_id`, `balance`) values(NULL,'GU','General user account for "+ name +"','"+ id +"','0.00')";
        String bill_2 = "insert into `billing`.`bills` (`id`, `bill_type`, `name`, `user_id`, `balance`) values(NULL,'BU','Blocked user account for "+ name +"','"+ id +"','0.00')";
        insertRecordIntoDbUserTable(bill_1);
        insertRecordIntoDbUserTable(bill_2);
    }



    public static void createNewUserFromDB1(String query) throws SQLException {

        Connection dbConnection;
        PreparedStatement preparedStatement;
        Statement statement;

        dbConnection = getDBConnection();
        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setInt(1, Integer.parseInt(executeQuery(MAX_USER_ID, "id"))+1);



    }


    public static void createNewUserFromDB(String role, String name) throws SQLException {

                String insertTableSQL = "insert into `users` " +
                 "(`id`, `first_name`, `nick_name`, `last_name`, `birthday`, `email`, `phone`, `is_user_deleted`, `password`, `password_salt`, `register_date`, " +
                        "`location_country`, `location_region`, `location_city`, `address`, `zip`, `biography`, `is_user_blocked`, `is_user_active`, `translite_name`, " +
                        "`person`, `pan`, `service_tax_number`, `user_role`, `social_id`, `last_visit`, `last_ip`, `no_commission`, `completed_orders`, `increase_profile_email_sent`, " +
                        "`warned_count`, `timezone_id`, `currency_id`) " +
                 "values" +
                 "('98765432','"+ name +"','WriterWR','SuperWriter','1966-02-13','debeers@bigmir.net','+91-7607059383','0','$2a$10$YdQcM3RmhCDJbatSoBgFGuL/qRIRY38rH9o8zpxFTuQqjreOz44fS'," +
                        "'$2a$10$YdQcM3RmhCDJbatSoBgFGw$','2015-08-11 06:45:10','28','751','76651','spttbiougis','555555','Dererer rerere rerere','0','1','','individual','TESTT8477T',NULL," +
                        "'"+ role +"','','2015-10-26 14:48:57','213.186.202.162','0','193','sent','0','397','1')";

        insertRecordIntoDbUserTable(insertTableSQL);
    }


}
