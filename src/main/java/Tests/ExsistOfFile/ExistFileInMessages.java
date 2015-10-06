package Tests.ExsistOfFile;

import Actions.Writer.WriterGoToMessages;
import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.GeneralHelpers;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by CMG_TEST on 02.09.2015.
 */
public class ExistFileInMessages extends BaseTest{




    @Test(groups={"regress 1.0"})
    public static void DownloadFilesToMessage() throws Exception {

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");

        String path = System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\DMX.jpg";

        String href =  WriterGoToMessages.CreateOrderAddBidSendMessageWithFileToClient(driver, clientLogin, order, writerLogin, path);
        Assert.assertTrue(GeneralHelpers.isFileExists(href));

    }
}
