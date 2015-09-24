package DataProviders;

import Entities.LoginObject;
import Entities.OrderObject;
import org.testng.annotations.DataProvider;


/**
 * Created by ilya on 27.08.2015.
 */
public class AcceptWriterTextDataProvider {


    public static Object[][] dataproviderObjectsWithText() {
        return new Object[][] {
                new Object[] {
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),
                        new LoginObject("debeers@bigmir.net", "H9CC1vxG"),
                        new String("hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))")

                }

        };
    }



    @DataProvider(name = "AcceptWriterText")
    public static Object[][] ActionsWithOrderDataWithText(){
        return dataproviderObjectsWithText();
    }


}





































