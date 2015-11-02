package Tests;

import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static GeneralHelpers.GMailHelper.getActivationLinkFromTargetMessage;
import static GeneralHelpers.GMailHelper.getMessages;

/**
 * Created by DeBeers on 02.11.2015.
 */
public class CreateNewUserTest extends BaseTest{

    @Test(groups={"regress 1.0"})
    public static void CreateNewUserTest() throws InterruptedException, IOException, MessagingException {


        String activationLink = getActivationLinkFromTargetMessage(gmailCredentials);



    }
}
