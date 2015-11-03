package Entities;

/**
 * Created by DeBeers on 03.11.2015.
 */
public class GmailCredentials {


    private final String mailbox;
    private final String password;

    public GmailCredentials(String mailbox, String password) {
        this.mailbox = mailbox;
        this.password = password;
    }

    public String getMailbox() {

        return mailbox;
    }

    public String getPassword() {

        return password;
    }
}
