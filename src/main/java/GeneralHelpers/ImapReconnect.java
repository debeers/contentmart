package GeneralHelpers;

/**
 * Created by DeBeers on 03.11.2015.
 */
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;

import Entities.GmailCredentials;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.protocol.IMAPProtocol;


public class ImapReconnect {


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

            public void messagesAdded(MessageCountEvent arg0) {
                System.out.println("New message was added.");
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
