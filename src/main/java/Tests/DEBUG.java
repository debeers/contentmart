package Tests;

import Actions.RegistrationAndLogin;
import Entities.LoginObject;
import Entities.OrderObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by ilya on 28.08.2015.
 */
public class DEBUG extends BaseTest{



    @Parameters({"clientLogin", "clientPassword", "writerLogin", "writerPassword"})
    @Test(groups={"regress 1.0"}, dataProvider= "debug", dataProviderClass = AcceptWriterTextDataProvider.class)
    public static void NewOrder(Object clientLoginObject, Object orderObject) throws InterruptedException, IOException {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject order = (OrderObject) orderObject;

        RegistrationAndLogin.loginAs(driver, clientLogin);
        System.out.println(order.getDesc() + order.getWordsReq());


//        OrderInfoAndActions order = ClientGoToCreateNewOrder___OLD.andPublish(driver, clientLogin, orderObject);
//        LoginPage.logOut(driver);
//        WriterGoToLogin.loginAsWriter(driver, writerLogin);




    }



}
