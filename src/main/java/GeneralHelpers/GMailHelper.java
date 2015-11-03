package GeneralHelpers;
import Entities.GmailCredentials;

import java.io.IOException;
import java.util.Properties;

import javax.mail.*;

/**
 * Created by DeBeers on 02.11.2015.
 */
public class GMailHelper {


    private static final String ACTIVATION_LINK_PATTERN_BEGIN = "https://contentmart.in/registration/activate_account/";
    private static final String ACTIVATION_LINK_PATTERN_END = ">";


    public static Store getSession() throws MessagingException {

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getDefaultInstance(props, null);

        return session.getStore();
    }


    public static String getActivationLinkFromTargetMessage(GmailCredentials gmailCredentials) throws MessagingException, IOException {

        Message[] message = getMessages(gmailCredentials);
        for (Message mailInbox : message) {
            if(mailInbox.getSubject().equals("Welcome to Contentmart.in")){
                System.out.println("FROM:" + mailInbox.getFrom().toString());

                Multipart mp = (Multipart) mailInbox.getContent();
                BodyPart bp = mp.getBodyPart(0);

                System.out.println("SENT DATE:" + mailInbox.getSentDate());
                System.out.println("SUBJECT:" + mailInbox.getSubject());
                System.out.println("CONTENT:" + bp.getContent());

                String s = (String)bp.getContent();
                return s.substring(s.indexOf(ACTIVATION_LINK_PATTERN_BEGIN), s.lastIndexOf(ACTIVATION_LINK_PATTERN_END));

            }

        }
        return null;
    }


    private static Message[] getMessages(GmailCredentials gmailCredentials) {

        try {

            Store store = getSession();
            store.connect("imap.gmail.com", gmailCredentials.getMailbox(), gmailCredentials.getPassword());
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message[] message = inbox.getMessages();
            //   Address[] in = msg.getFrom();

            return message;

        } catch (Exception mex) {
            mex.printStackTrace();
        }
        return null;
    }

}
