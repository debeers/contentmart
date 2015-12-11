package GeneralHelpers;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 * Created by DeBeers on 08.12.2015.
 */
public class SMTPService {

    public String username = "";
    public String password = "";
    public String to       = "";
    public String from     = "";
    public String host     = "";
    public String port     = "";
    public String subject  = "";
    public String text     = "";

    public SMTPService(){}

    public SMTPService(String username, String password, String to, String from,
                       String host, String port, String subject, String text) throws MessagingException {

        this.username   = username;
        this.password   = password;
        this.to         = to;
        this.from       = from;
        this.host       = host;
        this.port       = port;
        this.subject    = subject;
        this.text       = text;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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

    public Properties setProps() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.ssl.trust", "*");
        return props;
    }

    public Session getSession() {

        return Session.getInstance(setProps(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }


    public Session getDefSess(){
        return Session.getDefaultInstance(setProps(), null);
    }

    public Message createMessage() throws MessagingException {

        Message message = new MimeMessage(getSession());
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(text);

        return message;
    }

    public void sendMessageViaSMTP() throws MessagingException {
        Transport.send(createMessage());
        System.out.println("Sent message successfully....");
    }

    public void sendMessageViaSMTP2() throws MessagingException {
        Transport t = getSession().getTransport("smtp");
        createMessage().addRecipient(Message.RecipientType.TO,
                new InternetAddress(to));System.out.println("go to send");
        Message message = createMessage();

        try {
            t.connect(username, password);
            System.out.println("connect///==");
            t.send(message);
            System.out.println("Sent message successfully....");

        } catch (Exception e) {
            System.out.println("something goes wrong....");
        } finally {
            t.close();
            System.out.println("connection close....");
        }
    }
}











