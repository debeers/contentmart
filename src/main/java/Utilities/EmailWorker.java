package Utilities;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DeBeers on 18.12.2015.
 */
public class EmailWorker {


    public  static String getActivationLinkFromRegistrationLetter(Message message) throws MessagingException, IOException {

        Multipart multipart = (Multipart)message.getContent();
        for (int i=0; i<multipart.getCount(); i++){

            BodyPart bodyPart = multipart.getBodyPart(i);
            String s = (String)bodyPart.getContent();

            Pattern p = Pattern.compile("<a[^>]*href=\"(http[s]?:[^\"]*)\".*Activate<\\/a>");
            Matcher m = p.matcher(s);

            if(m.find()){
                String url = m.group(1);
                System.out.println("Regexp : " + url);
                return url;
            }
        }
        return null;
    }
}
