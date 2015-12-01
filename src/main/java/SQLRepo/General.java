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

    public static String createNewEmail(String name, String clear, String uid, String gid){
        return  "INSERT INTO `maildb`.`users` (`id`, `clear`, `uid`, `gid`, `home`, `maildir`) " +
                "VALUES ('" + name + "@dev.contentmart.in', '" + clear + "', '"+uid+"', '"+gid+"', " +
                "'/var/imap/dev.contentmart.in/users/" + name + "', '/var/imap/dev.contentmart.in/users/" + name + "/Maildir');";
    }

}
