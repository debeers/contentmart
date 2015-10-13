package Tests.UploadingFiles;

import Actions.Client.ClientGoToCreateNewOrder;
import Entities.OrderObject;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.Client.ClientGoToCreateNewOrder.checkForFileUploadInNewOrder;

/**
 * Created by ilya on 30.08.2015.
 */
public class UploadingFilesToOrder extends BaseTest {

    @Test(groups={"regress 1.0"})
    public static void AttachFileToOrder() throws Exception {

        OrderObject order = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        String filepath = System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\DMX.jpg";

        ClientGoToCreateNewOrder.andUploadFilesToIt(driver, clientLogin, order, filepath);
        Assert.assertTrue(checkForFileUploadInNewOrder(filepath));
    }
}
