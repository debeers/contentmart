package Entities;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;

import static Helpers.Randomizers.setRandomUserNickName;
import static Repository.UserEmailAccountRepo.getUserEmailAccountHomeDir;
import static Repository.UserEmailAccountRepo.getUserEmailAccountMailDir;

/**
 * Created by DeBeers on 01.12.2015.
 */
public class UserEmailAccount {

    public String name;
    public String email;
    public String password;
    public String home;
    public String maildir;

    public UserEmailAccount(){}

    public UserEmailAccount(String name, String email, String password, String home, String maildir) {

        this.name     = name;
        this.email    = email;
        this.password = password;
        this.home     = home;
        this.maildir  = maildir;
    }

    public UserEmailAccount initMail(String role) throws IOException {

        String name = setRandomUserNickName(role);
        UserEmailAccount UserEmailAccount = new UserEmailAccount();

        UserEmailAccount.setName(name);
        UserEmailAccount.setPassword(RandomStringUtils.randomAlphabetic(12));
        UserEmailAccount.setEmail(name + "@dev.contentmart.in");

        UserEmailAccount.setHome(
                getUserEmailAccountHomeDir(UserEmailAccount.getEmail()));
        UserEmailAccount.setMaildir(
                getUserEmailAccountMailDir(UserEmailAccount.getEmail())
        );

        return UserEmailAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMaildir() {
        return maildir;
    }

    public void setMaildir(String maildir) {
        this.maildir = maildir;
    }
}
