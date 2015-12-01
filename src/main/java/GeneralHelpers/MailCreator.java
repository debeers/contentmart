package GeneralHelpers;

import Entities.Mail;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.sql.SQLException;

import static SQLRepo.General.createNewEmail;

/**
 * Created by DeBeers on 01.12.2015.
 */
public class MailCreator {

    public static int uid_gid_generator() {
        return Integer.parseInt(RandomStringUtils.random(10));
    }

    public static String createNewUserMail(Mail mail, DBUtill dbUtill) throws IOException, SQLException {

        dbUtill.executeUpdate(
                createNewEmail(
                        mail.getName(),
                        mail.getClear(),
                        Integer.toString(mail.getUid()),
                        Integer.toString(mail.getGid())
                )
        );
        return mail.getId();
    }

}
