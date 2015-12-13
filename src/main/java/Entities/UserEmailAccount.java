package Entities;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;

import static GeneralHelpers.Randomizers.setRandomUserNickName;

/**
 * Created by DeBeers on 01.12.2015.
 */
public class UserEmailAccount {

    public String name;
    public String email;
    public String password;
    public String home;
    public String maildir;

    private final String MAIL_CONNECTION = "\\src\\main\\java\\Randomizers\\SettingsXML\\DB_CONN_MAIL.xml";

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

    private String getUserEmailAccountEmail(String password){
        return "SELECT email FROM users WHERE clear = '" + password + "'";
    }

    private String getUserEmailAccountPassword(String email){
        return "SELECT clear FROM users WHERE email = '" + email + "'";
    }

    private String getUserEmailAccountHomeDir(String email){
        return "SELECT home FROM users WHERE email = '" + email + "'";
    }

    private String getUserEmailAccountMailDir(String email){
        return "SELECT maildir FROM users WHERE email = '" + email + "'";
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
