package Entities;

import GeneralHelpers.MailCreator;
import org.apache.commons.lang3.RandomStringUtils;

import static GeneralHelpers.GeneralHelpers.setRandomUserNickName;
import static GeneralHelpers.MailCreator.uid_gid_generator;

/**
 * Created by DeBeers on 01.12.2015.
 */
public class Mail {

    private final String HOME_DIR = "/var/imap/dev.contentmart.in/users/";
    private final String MAIL_DIR = "/var/imap/dev.contentmart.in/users/";

    private int    uid;
    private int    gid;
    private String name;
    private String id;
    private String clear;
    private String home;
    private String maildir;

    public Mail(){}

    public Mail(int uid, int gid, String name, String id, String clear, String home, String maildir) {
        this.uid = uid;
        this.gid = gid;
        this.name = name;
        this.id = id;
        this.clear = clear;
        this.home = home;
        this.maildir = maildir;
    }

    public Mail initMail(String role) {

        String name = setRandomUserNickName(role);
        Mail mail   = new Mail();

        mail.setUid(uid_gid_generator());
        mail.setGid(uid_gid_generator());
        mail.setName(name);
        mail.setId(name + "@dev.contentmart.in");
        mail.setClear(RandomStringUtils.randomAlphabetic(18));
        mail.setHome(HOME_DIR + getName());
        mail.setMaildir(MAIL_DIR + name + "/Maildir");

        return mail;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClear() {
        return clear;
    }

    public void setClear(String clear) {
        this.clear = clear;
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
