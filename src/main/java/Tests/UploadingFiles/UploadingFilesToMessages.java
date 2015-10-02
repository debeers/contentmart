package Tests.UploadingFiles;

import Actions.Writer.WriterGoToMessages;
import DataProviders.MessagesDataProvider;
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




    @Test(groups={"regress 1.0"}, dataProvider= "dataproviderForMessages", dataProviderClass = MessagesDataProvider.class)
    public static void AttachFilesToMessage(Object clientLoginObject, Object orderObject, Object writerLoginObj) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;

        String path = System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\DMX.jpg";

        WriterGoToMessages.CreateOrderAddBidSendMessageWithFileToClient(driver, clientLogin, orderObj, writerLogin, path);
        Boolean fileAppear = waitForFileAppearInDialogBox(driver, path);
        Assert.assertTrue(fileAppear);

    }

}
