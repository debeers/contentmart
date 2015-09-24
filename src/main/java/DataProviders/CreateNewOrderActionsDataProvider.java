package DataProviders;

import Entities.LoginObject;
import Entities.OrderObject;
import org.testng.annotations.DataProvider;

/**
 * Created by CMG_TEST on 21.09.2015.
 */
public class CreateNewOrderActionsDataProvider {






    public static Object[][] dp2() {
        return new Object[][] {
                new Object[] {
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),


                }

        };
    }



    @DataProvider(name = "CreateNewOrderActions")
    public static Object[][] getObjectData(){

        return dp2();
    }



}
