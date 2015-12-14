package GeneralHelpers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static SQLRepo.General.*;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;

/**
 * Created by DeBeers on 28.10.2015.
 */
public class DBWorker {

    @SuppressWarnings("ConstantConditions")
//    public static String createMaxUserId(){
//
//        int i = Integer.parseInt(getColumn(MAX_USER_ID, "email"))+1;
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


    public static List<String> getExpertisesList(DBUtill dbUtill) throws IOException, SQLException {

        List<String> orderExpertise = new ArrayList<>();
        ResultSet res = dbUtill.getResultSet(getOrderExpertises());
        while(res.next()){
            orderExpertise.add(res.getString("NAME"));
            System.out.println(res.getString("NAME"));
        }
        return orderExpertise;
    }


    public static List<String> getLanguageList(DBUtill dbUtill) throws IOException, SQLException {

        List<String> orderLanguages = new ArrayList<>();
        ResultSet res = dbUtill.getResultSet(getOrderLanguages());
        while(res.next()){
            orderLanguages.add(res.getString("name"));
        }
        return orderLanguages;
    }


    public static List<String> getCategoriesList(DBUtill dbUtill) throws IOException, SQLException {

        List<String> orderCategories = new ArrayList<>();
        ResultSet res = dbUtill.getResultSet(getOrderCategories());
        while(res.next()){
            orderCategories.add(res.getString("name"));
        }
        return orderCategories;
    }

    public static void setUserCurrencyToUSD(DBUtill dbUtill, String mail) throws IOException, SQLException {
         dbUtill.executeUpdate(setUserCurrencyToRupee(mail));

    }


//    public static void createUserBills(String email, String name) throws SQLException, IOException {
//
//        String bill_1 = "insert into `billing`.`bills` (`email`, `bill_type`, `name`, `user_id`, `balance`) values(NULL,'GU','General user account for "+ name +"','"+ email +"','0.00')";
//        String bill_2 = "insert into `billing`.`bills` (`email`, `bill_type`, `name`, `user_id`, `balance`) values(NULL,'BU','Blocked user account for "+ name +"','"+ email +"','0.00')";
//        executeUpdate(bill_1);
//        executeUpdate(bill_2);
//    }

}
