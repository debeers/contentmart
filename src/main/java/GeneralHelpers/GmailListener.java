package GeneralHelpers;

import Entities.GmailCredentials;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.util.BASE64DecoderStream;

import javax.mail.*;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by DeBeers on 06.11.2015.
 */
public class GmailListener {

    private IMAPFolder imapFolder;
    private static final String ACTIVATION_LINK_PATTERN_BEGIN = "https://dev.contentmart.in/registration/activate_account/";
    private static final String ACTIVATION_LINK_PATTERN_END = ">";
    public  Message message;


    public Message startListening(GmailCredentials gmailCredentials, String subject, String fromEmail, Boolean isSeen, int timeToWait) throws Exception {

        IMAPFolder inbox = setup(gmailCredentials);
        inbox.open(Folder.READ_WRITE);
        long startTime = TimeUnit.SECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);

        while(startTime - TimeUnit.SECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS) < timeToWait){
            System.out.println("listening idle...");
            inbox.idle(true);
            if (getTargetMessage(inbox, subject, fromEmail, isSeen) != null){

                message = getTargetMessage(inbox, subject, fromEmail, isSeen);
                writeEnvelope(message);

                return message;
            }
        }

        inbox.close(true);
        return null;
    }


    private Message getTargetMessage(IMAPFolder inbox, String subject, String fromEmail, Boolean isSeen) throws MessagingException {

        Message[] message = inbox.getMessages();
        for (Message targetMessage : message){
            if (targetMessage != null && targetMessage.match(searchConditions(subject, fromEmail, isSeen))){

                return targetMessage;
            }
        }
        return null;
    }


    private  IMAPFolder setup(GmailCredentials gmailCredentials) throws MessagingException{

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props, null);
        Store store = session.getStore();

        store.connect("imap.googlemail.com", 993, gmailCredentials.getMailbox(), gmailCredentials.getPassword());
        return imapFolder = (IMAPFolder) store.getFolder("INBOX");

    }


    private static SearchTerm searchConditions(String subject, String fromEmail, Boolean isSeen) {

        Flags seen = new Flags(Flags.Flag.SEEN);
        FlagTerm isSeenFlag = new FlagTerm(seen, isSeen);

        SearchTerm searchTerm = new AndTerm(isSeenFlag, searchSubject(subject));
        SearchTerm searchTermCombined = new AndTerm(searchTerm, searchFrom(fromEmail));

        return searchTermCombined;
    }


    private static SearchTerm searchSubject(String subject) {

        SearchTerm searchCondition = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    if (message.getSubject().contains(subject)) {
                        return true;
                    }
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        };
        return searchCondition;
    }


    private static SearchTerm searchFrom(String fromEmail) {

        SearchTerm searchCondition = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    Address[] fromAddress = message.getFrom();
                    if (fromAddress != null && fromAddress.length > 0) {
                        if (fromAddress[0].toString().contains(fromEmail)) {
                            return true;
                        }
                    }
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }

                return false;
            }
        };
        return searchCondition;
    }


    public  void writeEnvelope(Message message) throws Exception {
        System.out.println("This is the message envelope");
        System.out.println("---------------------------");
        Address[] adress;

        // FROM
        if ((adress = message.getFrom()) != null) {
            for (int j = 0; j < adress.length; j++)
                System.out.println("FROM: " + adress[j].toString());
        }

        // TO
        if ((adress = message.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < adress.length; j++)
                System.out.println("TO: " + adress[j].toString());
        }

        // SUBJECT
        if (message.getSubject() != null)
            System.out.println("SUBJECT: " + message.getSubject());

    }


    public  void writePart(Part messagePart) throws Exception {
        if (messagePart instanceof Message)

            writeEnvelope((Message) messagePart);

        System.out.println("----------------------------");
        System.out.println("CONTENT-TYPE: " + messagePart.getContentType());

        if (messagePart.isMimeType("text/plain")) {
            System.out.println("This is plain text");
            System.out.println("---------------------------");
            System.out.println((String) messagePart.getContent());
        }
        //check if the content has attachment
        else if (messagePart.isMimeType("multipart/*")) {
            System.out.println("This is a Multipart");
            System.out.println("---------------------------");
            Multipart mp = (Multipart) messagePart.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
                writePart(mp.getBodyPart(i));
        }

        else if (messagePart.isMimeType("message/rfc822")) {
            System.out.println("This is a Nested Message");
            System.out.println("---------------------------");
            writePart((Part) messagePart.getContent());
        }

        else if (messagePart.getContentType().contains("image/")) {
            System.out.println("content type" + messagePart.getContentType());
            File f = new File("image" + new Date().getTime() + ".jpg");
            DataOutputStream output = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(f)));
            BASE64DecoderStream test =
                    (BASE64DecoderStream) messagePart
                            .getContent();
            byte[] buffer = new byte[2048];
            int bytesRead;
            while ((bytesRead = test.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
        else {
            Object messagePartContent = messagePart.getContent();
            if (messagePartContent instanceof String) {
                System.out.println("This is a string");
                System.out.println("---------------------------");
                System.out.println((String) messagePartContent);
            }
            else if (messagePartContent instanceof InputStream) {
                System.out.println("This is just an input stream");
                System.out.println("---------------------------");
                InputStream inputStream = (InputStream) messagePartContent;
                int c;
                while ((c = inputStream.read()) != -1)
                    System.out.write(c);
            }
            else {
                System.out.println("This is an unknown type");
                System.out.println("---------------------------");
                System.out.println(messagePartContent.toString());
            }
        }

    }


    public  static String getActivationLinkFromTargetMessage(Message message) throws MessagingException, IOException {

        String res = "";

        Multipart multipart = (Multipart)message.getContent();
        for (int i=0; i<multipart.getCount(); i++){

            BodyPart bodyPart = multipart.getBodyPart(i);
            String s = (String)bodyPart.getContent();
            if(s.contains(ACTIVATION_LINK_PATTERN_BEGIN)){
                res = s;
                return res.substring(res.indexOf(ACTIVATION_LINK_PATTERN_BEGIN), res.lastIndexOf(ACTIVATION_LINK_PATTERN_END));
            }
        }

        return null;

    }

}