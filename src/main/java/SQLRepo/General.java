package SQLRepo;

/**
 * Created by DeBeers on 26.11.2015.
 */
public class General {

    public static String checkUserExsistanceByMail(String mail){
        return  "SELECT * FROM users WHERE email = '" + mail + "'";
    }

    public static String checkUserID(String mail){
        return  "SELECT email FROM users WHERE email = '" + mail + "'";
    }

    public static String createNewEmail(String name, String clear){
        return  "INSERT INTO `maildb`.`users` (`email`, `clear`, `uid`, `gid`, `home`, `maildir`) " +
                "VALUES ('" + name + "@dev.contentmart.in', '" + clear + "', '93', '93', " +
                "'/var/imap/dev.contentmart.in/users/" + name + "', '/var/imap/dev.contentmart.in/users/" + name + "/Maildir');";
    }

    public static String getAllUserFieldsByMail(String mail){
        return "SELECT * FROM users WHERE email = '" + mail + "'";
    }

    public static String getUserTimezoneOffsetByMail(String mail){
        return "SELECT \n" +
                "\tt.utc_offset\n" +
                "FROM \n" +
                "\tusers u\n" +
                "JOIN\n" +
                "\ttimezone t\n" +
                "\tON t.email = u.timezone_id\n" +
                "WHERE \n" +
                "\tu.email = '" + mail + "'";
    }

    public static String getUserTimezoneViewNameByMail(String mail){
        return "SELECT \n" +
                "\tt.view_name\n" +
                "FROM \n" +
                "\tusers u\n" +
                "JOIN\n" +
                "\ttimezone t\n" +
                "\tON t.email = u.timezone_id\n" +
                "WHERE \n" +
                "\tu.email = '" + mail + "'";
    }

    public static String getUserTimezoneNameByMail(String mail){
        return "SELECT t.name FROM users u JOIN timezone t ON t.id = u.timezone_id WHERE u.email = '" + mail + "'";
    }

    public static String getLastUpdatedCurrencyRate() {
        return "SELECT VALUE\n" +
                "FROM currency_rate\n" +
                "WHERE email=(SELECT MAX(email) FROM currency_rate)";

    }

    public static String getOrderCategories() {
        return "SELECT NAME FROM writing_category";
    }

    public static String getOrderExpertises() {
        return "SELECT NAME FROM expertises";
    }

    public static String getOrderLanguages() {
        return "SELECT name FROM language";
    }

    public static String getUserCurrencyID(String mail){
        return "SELECT currency_id FROM `users` WHERE email ='" + mail + "'";
    }

    public static String setUserCurrencyToRupee(String mail){
        return "UPDATE users SET currency_id = NULL WHERE email ='" + mail + "'";
    }

}
