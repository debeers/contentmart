package Tests.UploadingFiles;

import Actions.Client.ClientGoToCreateNewOrder;
import DataProviders.CreateNewOrderActionsDataProvider;
import Entities.LoginObject;
import Entities.OrderObject;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.Client.ClientGoToCreateNewOrder.checkForFileUploadInNewOrder;

/**
 * Created by ilya on 30.08.2015.
 */
public class UploadingFilesToOrder extends BaseTest {

    @Test(groups={"regress 1.0"}, dataProvider= "CreateNewOrderActions", dataProviderClass = CreateNewOrderActionsDataProvider.class)
    public static void AttachFileToOrder(Object clientLoginObject, Object orderObject) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;


        String filepath = System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\DMX.jpg";
        ClientGoToCreateNewOrder.andUploadFilesToIt(driver, clientLogin, orderObj, filepath);

        Boolean uploadedFile = checkForFileUploadInNewOrder(filepath);
        Assert.assertTrue(uploadedFile);

    }

}
