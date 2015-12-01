package Tests;

import org.testng.annotations.Test;

import static GeneralHelpers.GeneralHelpers.getSystemTime_AM_PM;

/**
 * Created by DeBeers on 01.12.2015.
 */
public class Debug {


    @Test(groups={"Fast_And_Furious_Smoke_1.0"})
    public void Debug() throws Exception {


        System.out.println(getSystemTime_AM_PM().substring(6, 8));
        Thread.sleep(30000);
    }

}
