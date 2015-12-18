package Repository;

import Entities.UserModel;
import Utilities.DBUtill;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Utilities.Randomizers.createRandomUserEmail;

/**
 * Created by DeBeers on 02.12.2015.
 */
public class UserModelRepo {

    public static UserModel getUserDATAFromDB(String email) throws IOException, SQLException {

        ResultSet resultSet = getAllUserFieldsByMail(email);

        UserModel userModel = new UserModel();

        if (resultSet.next()) {

            userModel.setIsUserActive(resultSet.getBoolean("is_user_active"));
            userModel.setIsUserBlocked(resultSet.getBoolean("is_user_blocked"));
            userModel.setIsUserDeleted(resultSet.getBoolean("is_user_deleted"));
            userModel.setUserAddress(resultSet.getString("address"));
            userModel.setUserBiography(resultSet.getString("biography"));
            userModel.setUserBirthday(resultSet.getString("birthday"));
            userModel.setUserCity(resultSet.getInt("location_city"));
            userModel.setUserCompletedOrders(resultSet.getInt("completed_orders"));
            userModel.setUserCountry(resultSet.getInt("location_country"));
            userModel.setUserCurrency(resultSet.getInt("currency_id"));
            userModel.setUserEmail(resultSet.getString("email"));
            userModel.setUserFirstName(resultSet.getString("first_name"));
            userModel.setUserId(resultSet.getInt("id"));
            userModel.setUserIncreaseProfileEmailSent(resultSet.getString("increase_profile_email_sent"));
            userModel.setUserIsServiceTaxPayer(resultSet.getBoolean("is_service_tax_payer"));
            userModel.setUserLastIP(resultSet.getString("last_ip"));
            userModel.setUserLastName(resultSet.getString("last_name"));
            userModel.setUserLastVisit(resultSet.getString("last_visit"));
            userModel.setUserNickName(resultSet.getString("nick_name"));
            userModel.setUserNoCommission(resultSet.getBoolean("no_commission"));
            userModel.setUserPan(resultSet.getString("pan"));
            userModel.setUserPassword(resultSet.getString("password"));
            userModel.setUserPasswordSalt(resultSet.getString("password_salt"));
            userModel.setUserPerson(resultSet.getString("person"));
            userModel.setUserPhone(resultSet.getString("phone"));
            userModel.setUserRegion(resultSet.getInt("location_region"));
            userModel.setUserRegisterDate(resultSet.getString("register_date"));
            userModel.setUserRole(resultSet.getString("user_role"));
            userModel.setUserServiceTax(resultSet.getString("service_tax_number"));
            userModel.setUserSocialId(resultSet.getString("social_id"));
            userModel.setUserTimeZone(resultSet.getInt("timezone_id"));
            userModel.setUserTransliteName(resultSet.getString("translite_name"));
            userModel.setUserWarnedCount(resultSet.getInt("warned_count"));
            userModel.setUserZip(resultSet.getString("zip"));

            return userModel;
        }
        return null;
    }

    public static ResultSet getAllUserFieldsByMail(String mail) throws IOException, SQLException {
        DBUtill db = new DBUtill();
        return db.getResultSet("SELECT * FROM users WHERE email = '" + mail + "'");
    }

    public static String getUserTimezoneOffsetByMail(String mail){
        DBUtill db = new DBUtill();
        return db.getColumn(
                "SELECT \n" +
                "\tt.utc_offset\n" +
                "FROM \n" +
                "\tusers u\n" +
                "JOIN\n" +
                "\ttimezone t\n" +
                "\tON t.email = u.timezone_id\n" +
                "WHERE \n" +
                "\tu.email = '" + mail + "'", "utc_offset"
        );
    }

    public static String getUserTimezoneViewNameByMail(String mail){
        DBUtill db = new DBUtill();
        return db.getColumn("SELECT \n" +
                "\tt.view_name\n" +
                "FROM \n" +
                "\tusers u\n" +
                "JOIN\n" +
                "\ttimezone t\n" +
                "\tON t.email = u.timezone_id\n" +
                "WHERE \n" +
                "\tu.email = '" + mail + "'", "name");
    }

    public static String getUserTimezoneNameByMail(String mail){
        DBUtill db = new DBUtill();
        return db.getColumn(
                "SELECT t.name AS name FROM users u JOIN timezone t ON t.id = u.timezone_id WHERE u.email = '" + mail + "'", "name"
        );
    }

    public static String getUserCurrencyID(String mail){
        DBUtill db = new DBUtill();
        return db.getColumn( "SELECT currency_id FROM users WHERE email ='" + mail + "'", "currency_id"
        );
    }

    public static String setUserCurrencyToRupee(String mail){
        return "UPDATE users SET currency_id = NULL WHERE email ='" + mail + "'";
    }

    public static String deleteCreatedUserFromDB(DBUtill dbUtill, String whereEmail) throws SQLException, IOException {

        String mail = createRandomUserEmail();
        dbUtill.executeUpdate(
                "UPDATE users " +
                        "SET email = '" + mail +
                        "WHERE email = '" + whereEmail + "'"
        );
        return mail;
    }

    public static String checkUserID(String mail){
        DBUtill db = new DBUtill();
        return db.getColumn(
                "SELECT id FROM users WHERE email = '" + mail + "'", "id"
        );
    }
}
