package Repository;

import Entities.UserModel;
import GeneralHelpers.DBUtill;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static SQLRepo.General.getAllUserFieldsByMail;

/**
 * Created by DeBeers on 02.12.2015.
 */
public class UserModelRepo {

    public static UserModel getUserDATAFromDB(String email) throws IOException, SQLException {

        DBUtill dbUtill = new DBUtill();

        ResultSet resultSet = dbUtill.initDBConnection()
                .getDBConnection()
                .createStatement()
                .executeQuery(getAllUserFieldsByMail(email));

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
}
