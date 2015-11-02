package Tests.UploadingFiles;

import Actions.Writer.WriterGoToMessages;
import Entities.OrderObject;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.Writer.WriterGoToMessages.waitForFileAppearInDialogBox;

/**
 * Created by ilya on 01.09.2015.
 */
public class UploadingFilesToMessages extends BaseTest {


    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public static void AttachFilesToMessage() throws Exception {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String path = System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\DMX.jpg";

        WriterGoToMessages.CreateOrderAddBidSendMessageWithFileToClient(driver, clientLogin, order, writerLogin, path);
        Boolean fileAppear = waitForFileAppearInDialogBox(path);
        Assert.assertTrue(fileAppear);
    }
}
