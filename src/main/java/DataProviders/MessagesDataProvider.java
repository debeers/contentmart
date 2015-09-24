package DataProviders;

import Entities.LoginObject;
import Entities.OrderObject;
import org.testng.annotations.DataProvider;

/**
 * Created by DeBeers on 22.09.2015.
 */
public class MessagesDataProvider {


    public static Object[][] dataproviderObjectsForMsg() {
        return new Object[][] {
                new Object[] {
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),
                        new LoginObject("debeers@bigmir.net", "H9CC1vxG")
                }

        };
    }

    @DataProvider(name = "dataproviderForMessages")
    public static Object[][] dataproviderForBidOnOrder(){

        return dataproviderObjectsForMsg();
    }

}
