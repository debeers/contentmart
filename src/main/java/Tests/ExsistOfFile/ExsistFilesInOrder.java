package Tests.ExsistOfFile;

import Actions.Client.ClientGoToCreateNewOrder;
import DataProviders.CreateNewOrderActionsDataProvider;
import Entities.LoginObject;
import Entities.Order;
import Entities.OrderObject;
import Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import static Actions.Client.ClientGoToCreateNewOrder.checkForFileExsistInNewOrder;

/**
 * Created by DeBeers on 27.09.2015.
 */
public class ExsistFilesInOrder extends BaseTest{


    @Test(groups={"regress 1.0"}, dataProvider= "CreateNewOrderActions", dataProviderClass = CreateNewOrderActionsDataProvider.class)
    public static void ExsistFilesInOrder(Object clientLoginObject, Object orderObject) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;

        Order order = new Order();
        String filepath = System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\DMX.jpg";
        ClientGoToCreateNewOrder.andUploadFilesToIt(driver, clientLogin, orderObj, order, filepath);
        Assert.assertTrue(checkForFileExsistInNewOrder());

    }
}
