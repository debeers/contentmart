package GeneralHelpers;

import Entities.UserEmailAccount;

import java.io.IOException;

/**
 * Created by DeBeers on 10.12.2015.
 */
public class CreateEmailAccountUtill {

    public static String formActivationData(String userName, String password){
        return  "http://dev.contentmart.in:81/create_mail.php?mail=" + userName + "&pass=" + password;
    }

    public static UserEmailAccount createNewUserEmail(String userRole) throws Exception {

        UserEmailAccount UserEmailAccount = new UserEmailAccount().initMail(userRole);
        System.out.println("NEW USER Name ::::: " + UserEmailAccount.getName() + "  NEW USER PASSWORD ::::: " + UserEmailAccount.getPassword() + "  USER DB ID ::::: " + UserEmailAccount.getEmail());
        HTTPUtill GET = new HTTPUtill();
        GET.sendGet(formActivationData(UserEmailAccount.getName(), UserEmailAccount.getPassword()));
        return UserEmailAccount;
    }
}
