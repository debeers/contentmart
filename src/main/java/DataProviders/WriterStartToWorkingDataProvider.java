package DataProviders;

import Entities.LoginObject;
import Entities.OrderObject;
import org.testng.annotations.DataProvider;

/**
 * Created by CMG_TEST on 22.09.2015.
 */
public class WriterStartToWorkingDataProvider {




    public static Object[][] dataproviderObjectsForStartToWorking() {
        return new Object[][] {
                new Object[] {
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),
                        new LoginObject("debeers@bigmir.net", "H9CC1vxG"),
                        new String("* Warning - Project owner might cancel the order if it's not delivered before the deadline! Submit in time!"),

                }

        };
    }

    @DataProvider(name = "dataproviderObjectsForStartWorking")
    public static Object[][] dataproviderObjectsForStartWorking(){

        return dataproviderObjectsForStartToWorking();
    }

}
