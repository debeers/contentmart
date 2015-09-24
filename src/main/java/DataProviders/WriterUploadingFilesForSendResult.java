package DataProviders;

import Entities.LoginObject;
import Entities.OrderObject;
import org.testng.annotations.DataProvider;

/**
 * Created by CMG_TEST on 23.09.2015.
 */
public class WriterUploadingFilesForSendResult {



    public static Object[][] dataproviderObjects() {
        return new Object[][] {
                new Object[] {
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),
                        new LoginObject("debeers@bigmir.net", "H9CC1vxG"),
                        new String("hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))")

                }

        };
    }



    @DataProvider(name = "UploadingFilesToSendResult")
    public static Object[][] ActionsWithOrderDataWithText(){
        return dataproviderObjects();
    }





}
