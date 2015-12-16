package Repository;

import Helpers.DBUtill;

/**
 * Created by DeBeers on 16.12.2015.
 */
public class UserEmailAccountRepo {

    public static String getUserEmailAccountEmail(String password){
        DBUtill db = new DBUtill();
        return db.getColumn( "SELECT email FROM users WHERE clear = '" + password + "'", "email"
        );
    }

    public static String getUserEmailAccountPassword(String email){
        DBUtill db = new DBUtill();
        return db.getColumn( "SELECT clear FROM users WHERE email = '" + email + "'", "clear"
        );
    }

    public static String getUserEmailAccountHomeDir(String email){
        DBUtill db = new DBUtill();
        return db.getColumn( "SELECT home FROM users WHERE email = '" + email + "'", "home"
        );
    }

    public static String getUserEmailAccountMailDir(String email){
        DBUtill db = new DBUtill();
        return db.getColumn( "SELECT maildir FROM users WHERE email = '" + email + "'", "maildir"
        );
    }
}
