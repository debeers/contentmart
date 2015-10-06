package Tests.UploadingFiles;

import Actions.Writer.WriterGoToMessages;
import Entities.LoginObject;
import Entities.OrderObject;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.Writer.WriterGoToMessages.waitForFileAppearInDialogBox;

/**
 * Created by ilya on 01.09.2015.
 */
public class UploadingFilesToMessages extends BaseTest {




    @Test(groups={"regress 1.0"})
    public static void AttachFilesToMessage() throws Exception {

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");

        String path = System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\DMX.jpg";

        WriterGoToMessages.CreateOrderAddBidSendMessageWithFileToClient(driver, clientLogin, orderObj, writerLogin, path);
        Boolean fileAppear = waitForFileAppearInDialogBox(driver, path);
        Assert.assertTrue(fileAppear);

    }

}
