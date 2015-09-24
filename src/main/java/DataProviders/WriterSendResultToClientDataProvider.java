package DataProviders;

import Entities.LoginObject;
import Entities.OrderObject;
import org.testng.annotations.DataProvider;

/**
 * Created by CMG_TEST on 22.09.2015.
 */
public class WriterSendResultToClientDataProvider {



    public static Object[][] dataproviderObjectsForSendResult() {
        return new Object[][] {
                new Object[] {
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),
                        new LoginObject("debeers@bigmir.net", "H9CC1vxG"),
                        new String("hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))"),
                        new String("Your result has been delivered to the order owner successfully! You will be notified as and when your result is accepted/rejected.")

                }

        };
    }

    @DataProvider(name = "dataproviderForSendResultTest")
    public static Object[][] dataproviderForSendResult(){

        return dataproviderObjectsForSendResult();
    }
}
