package Tests.ExsistOfFile;

import Actions.Writer.WriterGoToMessages;
import DataProviders.ActionsWithOrdersDataProvider;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import GeneralHelpers.GeneralHelpers;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by CMG_TEST on 02.09.2015.
 */
public class ExistFileInMessages extends BaseTest{




    @Test(groups={"regress 1.0"}, dataProvider= "SetAsWinnerDataProvider", dataProviderClass = ActionsWithOrdersDataProvider.class)
    public static void DownloadFilesToMessage(Object clientLoginObject, Object orderObject, Object writerLoginObj) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;

        Order order = new Order();
        String path = System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\DMX.jpg";

        String href =  WriterGoToMessages.CreateOrderAddBidSendMessageWithFileToClient(driver, clientLogin, orderObj, writerLogin, order, path);
        Assert.assertTrue(GeneralHelpers.isFileExists(href));

    }
}
