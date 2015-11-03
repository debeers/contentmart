package GeneralHelpers;

/**
 * Created by DeBeers on 03.11.2015.
 */
import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;

import Entities.GmailCredentials;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.protocol.IMAPProtocol;

public class GmailListener {


    private IMAPFolder imapFolder;
    private IMAPFolder processedFolder;
    private IMAPFolder invalidFolder;
    private static final long KEEP_ALIVE_FREQ = 1000;
    private static final String CMI_MAILBOX_PROCESSED ="Processed";
    private static final String CMI_MAILBOX_INVALID ="Invalid";


    public void startService(GmailCredentials gmailCredentials){
        try {

            setup(gmailCredentials);

        } catch( MessagingException e) {

            System.out.println("Error configuring imap server:");
            System.out.println(e.toString());
            System.exit(1);
        }

        Thread keepAlive = new Thread(new Runnable(){
            public void run() {
                keepAliveRunner();
            }
        });
        keepAlive.start();

        imapFolder.addMessageCountListener(new MessageCountListener(){
            @Override
            public void messagesAdded(MessageCountEvent arg0) {

                System.out.println("New message was added.");
                Flags seen = new Flags(Flags.Flag.SEEN);
                FlagTerm unseenFlagTerm = new FlagTerm(seen, false);

                Message[] message = new Message[0];
                try {

                    message = imapFolder.getMessages();

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                for (Message mailInbox : message) {
                    try {
                        if (mailInbox.getSubject().equals("Welcome to Contentmart.in")) {
                            System.out.println("FROM:" + mailInbox.getFrom().toString());

                            Multipart mp = (Multipart) mailInbox.getContent();
                            BodyPart bp = mp.getBodyPart(0);

                            System.out.println("SENT DATE:" + mailInbox.getSentDate());
                            System.out.println("SUBJECT:" + mailInbox.getSubject());
                            System.out.println("CONTENT:" + bp.getContent());

                            String s = (String) bp.getContent();
                            System.out.println(s);
                            // return s.substring(s.indexOf(ACTIVATION_LINK_PATTERN_BEGIN), s.lastIndexOf(ACTIVATION_LINK_PATTERN_END));

                        }
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void messagesRemoved(MessageCountEvent arg0) {

            }

        });

        while (!Thread.interrupted()) {
            try {
                imapFolder.idle();
            } catch (FolderClosedException e) {
                System.out.println("The remote server closed the IMAP folder, we're going to try reconnecting.");
                startService(gmailCredentials);
            } catch (MessagingException e) {
                System.out.println("Now closing imap mailbox, due to unhandlable exception: ");
                System.out.println(e.toString());
                break;
            }
        }

        if (keepAlive.isAlive()) {
            keepAlive.interrupt();
        }

        try {
            imapFolder.close(false);
            processedFolder.close(false);
            invalidFolder.close(false);
        } catch (MessagingException e) {
            System.out.println("Error closing all the folders:");
            System.out.println(e.toString());
        }
    }


    private SearchTerm searchCondition(String keyword){

        SearchTerm searchCondition = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    if (message.getSubject().contains(keyword)) {
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




    private void setup(GmailCredentials gmailCredentials) throws MessagingException{
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props, null);
        Store store = session.getStore();

        store.connect("imap.googlemail.com", 993, gmailCredentials.getMailbox(), gmailCredentials.getPassword());
        imapFolder = (IMAPFolder) store.getFolder("INBOX");

        processedFolder = (IMAPFolder) imapFolder.getFolder(CMI_MAILBOX_PROCESSED);
        if(!processedFolder.exists())
            processedFolder.create(Folder.HOLDS_MESSAGES);

        invalidFolder = (IMAPFolder) imapFolder.getFolder(CMI_MAILBOX_INVALID);
        if(!invalidFolder.exists())
            invalidFolder.create(Folder.HOLDS_MESSAGES);

        imapFolder.open(Folder.READ_WRITE);
    }

    public void keepAliveRunner(){
        while (!Thread.interrupted()) {
            try {
                // sleep for 5 minutes
                Thread.sleep(KEEP_ALIVE_FREQ);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                imapFolder.doCommand(new IMAPFolder.ProtocolCommand() {
                    public Object doCommand(IMAPProtocol proto)
                            throws ProtocolException {
                        proto.simpleCommand("NOOP", null);
                        return null;
                    }
                });
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

}
