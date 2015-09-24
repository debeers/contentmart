package DataProviders;

import Entities.LoginObject;
import org.testng.annotations.DataProvider;


public class LoginDataProvider {



    @DataProvider(name="ClientLoginData", parallel = true)
    public static Object[][] positiveLoginData() {
        return new Object[][]{new Object[]{
                new LoginObject("debeers1989@gmail.com", "roottoor")
        }};

    }


    @DataProvider(name="WriterLoginData", parallel = true)
    public static Object[][] WriterLoginData(){
        return new Object[][]{new Object[]{
                new LoginObject("debeers@bigmir.net", "H9CC1vxG")
        }};
    }


}









