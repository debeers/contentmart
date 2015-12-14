package Entities;

/**
 * Created by DeBeers on 09.12.2015.
 */
public class Email {

    public Email(){}

    public Email(String from, String subject, String text) {
        this.from = from;
        this.subject = subject;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String from    = "";
    public String subject = "";
    public String text    = "";

}
