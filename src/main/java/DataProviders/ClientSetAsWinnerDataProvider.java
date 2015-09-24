package DataProviders;

import Entities.LoginObject;
import Entities.OrderObject;
import org.testng.annotations.DataProvider;

/**
 * Created by CMG_TEST on 22.09.2015.
 */
public class ClientSetAsWinnerDataProvider {


    @DataProvider(name = "SetAsWinnerDataProvider")
    public static Object[][] ActionsWithOrderData() {

        return dataproviderObjectsSetAsWinner();
    }

    public static Object[][] dataproviderObjectsSetAsWinner() {
        return new Object[][]{
                new Object[]{
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),
                        new LoginObject("debeers@bigmir.net", "H9CC1vxG"),
                }

        };
    }






    public static Object[][] dataproviderObjectsAcceptWriterText() {
        return new Object[][]{
                new Object[]{
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),
                        new LoginObject("debeers@bigmir.net", "H9CC1vxG"),
                        new String("hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))")

                }

        };
    }

    @DataProvider(name = "AcceptWriterText")
    public static Object[][] ActionsWithOrderDataAcceptWritersText() {
        return dataproviderObjectsAcceptWriterText();
    }







    public static Object[][] dataproviderObjectsForDeclineText() {
        return new Object[][]{
                new Object[]{
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),
                        new LoginObject("debeers@bigmir.net", "H9CC1vxG"),
                        new String("hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))"),
                        new String("Nothing pearsonal, just test!")
                }

        };
    }

    @DataProvider(name = "dataproviderObjectsForDeclineText")
    public static Object[][] dataproviderObjectsForDecline() {

        return dataproviderObjectsForDeclineText();
    }









    public static Object[][] dataproviderObjectsForBid() {
        return new Object[][]{
                new Object[]{
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),
                        new LoginObject("debeers@bigmir.net", "H9CC1vxG"),
                        new String("Thanks! Your proposal has been sent to the order owner.")
                }

        };
    }

    @DataProvider(name = "dataproviderForBidOnOrder")
    public static Object[][] dataproviderForBidOnOrder() {

        return dataproviderObjectsForBid();
    }









    public static Object[][] dataproviderObjectsForSendResult() {
        return new Object[][]{
                new Object[]{
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),
                        new LoginObject("debeers@bigmir.net", "H9CC1vxG"),
                        new String("hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!)))"),
                        new String("Your result has been delivered to the order owner successfully! You will be notified as and when your result is accepted/rejected.")

                }

        };
    }

    @DataProvider(name = "dataproviderForSendResultTest")
    public static Object[][] dataproviderForSendResult() {

        return dataproviderObjectsForSendResult();
    }










    public static Object[][] dataproviderObjectsForStartToWorking() {
        return new Object[][]{
                new Object[]{
                        new LoginObject("debeers1989@gmail.com", "roottoor"),
                        new OrderObject("Automation test order ID:", "New automation test order description", "15", "1"),
                        new LoginObject("debeers@bigmir.net", "H9CC1vxG"),
                        new String("* Warning - Project owner might cancel the order if it's not delivered before the deadline! Submit in time!"),

                }

        };
    }

    @DataProvider(name = "dataproviderObjectsForStartWorking")
    public static Object[][] dataproviderObjectsForStartWorking() {

        return dataproviderObjectsForStartToWorking();
    }
}
