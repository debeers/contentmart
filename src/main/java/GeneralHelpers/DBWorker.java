package GeneralHelpers;

import java.io.IOException;
import java.sql.SQLException;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;

/**
 * Created by DeBeers on 28.10.2015.
 */
public class DBWorker {


    private static final String MAX_USER_ID = "SELECT id FROM users WHERE id=(SELECT MAX(id) FROM users)";
    private static final String CREATE_NEW_USER = "INSERT INTO `users` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    // values('98765443','Ilya','Ilya-Slabiy','Slabiy','1908-08-02','debeers@bigmir.net','','0','$2a$10$8AjxEfQy.QTLqioWFqK8j.XuMgG8kXwt4tizaT.4elFlEW2jlit8O','$2a$10$8AjxEfQy.QTLqioWFqK8jB$','2015-10-27 21:37:27','1',NULL,NULL,'','0',NULL,'0','1','ilya-slabiy','individual',NULL,NULL,'copywriter','862775793760125','2015-10-28 12:26:04','213.186.202.162','0','0','not_sent','0','321',NULL);


    @SuppressWarnings("ConstantConditions")
//    public static String createMaxUserId(){
//
//        int i = Integer.parseInt(getColumn(MAX_USER_ID, "id"))+1;
//        return Integer.toString(i);
//    }

    private static String createTestUserName(){

        return "AutoBot-" + randomNumeric(4) + randomAlphabetic(3);
    }


    @SuppressWarnings("ConstantConditions")
    public static void checkForExitingUserAndDeleteIt(DBUtill dbUtill, String query, String column, String email) throws SQLException, InterruptedException, IOException {
        String result = dbUtill.getColumn(query, column);
        if (result != null ) {
            if (result.equalsIgnoreCase(email)){
                deleteCreatedUserFromDB(dbUtill, email);
            }

        } else System.out.println("No such user in DB");
    }


    public static String deleteCreatedUserFromDB(DBUtill dbUtill, String whereEmail) throws SQLException, IOException {

        String mail = randomNumeric(4) + randomAlphabetic(3) + randomNumeric(3) + "@testmail.com' ";
        dbUtill.executeUpdate(

                "UPDATE users " +
                        "SET email = '" + mail +
                        "WHERE email = '" + whereEmail + "'"
        );
        return mail;
    }

//    public static void createUserBills(String id, String name) throws SQLException, IOException {
//
//        String bill_1 = "insert into `billing`.`bills` (`id`, `bill_type`, `name`, `user_id`, `balance`) values(NULL,'GU','General user account for "+ name +"','"+ id +"','0.00')";
//        String bill_2 = "insert into `billing`.`bills` (`id`, `bill_type`, `name`, `user_id`, `balance`) values(NULL,'BU','Blocked user account for "+ name +"','"+ id +"','0.00')";
//        executeUpdate(bill_1);
//        executeUpdate(bill_2);
//    }

}
