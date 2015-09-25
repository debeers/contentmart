package DataProviders;

import Entities.LoginObject;
import Entities.OrderObject;
import org.testng.annotations.DataProvider;

/**
 * Created by CMG_TEST on 24.09.2015.
 */
public class StopWordsDataProvider {




    public static Object[][] dp2() {
        return new Object[][] {
                new Object[] {
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "skype, icq, call me, my email", "15", "1"),


                }

        };
    }



    @DataProvider(name = "StopWordsInNewOrderComposing")
    public static Object[][] getObjectData(){

        return dp2();
    }









    public static Object[][] dataproviderObjectsForStopWordsInLeaveAnOffer() {
        return new Object[][] {
                new Object[] {
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),
                        new LoginObject("debeers@bigmir.net", "H9CC1vxG"),
                        new String("Hello master! I`m your Jinni! Now you`re have 3 wishes! But I`m very bussy for now, please call me for a 1000 year or write me on my email."),
                        new String("You may not communicate with users directly or provide your email, Skype or phone number. Learn why not to work outside ContentMart.")
                }

        };
    }

    @DataProvider(name = "StopWordsInLeaveAnOffer")
    public static Object[][] StopWordsInLeaveAnOffer(){

        return dataproviderObjectsForStopWordsInLeaveAnOffer();
    }





}
