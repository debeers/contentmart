package SQLRepo;

/**
 * Created by DeBeers on 26.11.2015.
 */
public class General {

    public static String checkUserExsistanceByMail(String mail){
        return  "SELECT * FROM users WHERE email = '" + mail + "'";
    }

    public static String checkUserID(String mail){
        return  "SELECT id FROM users WHERE email = '" + mail + "'";
    }

}
