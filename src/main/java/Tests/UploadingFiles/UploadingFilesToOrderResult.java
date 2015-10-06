package Tests.UploadingFiles;

import Actions.Writer.WriterGoToEndResultToClient;
import Entities.LoginObject;
import Entities.OrderObject;
import GeneralHelpers.GeneralHelpers;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

/**
 * Created by CMG_TEST on 23.09.2015.
 */
public class UploadingFilesToOrderResult extends BaseTest{

    @Test(groups={"regress 1.0"})
    public static void UploadingFilesToOrderResult() throws InterruptedException {

        LoginObject clientLogin = new LoginObject("debeers1989@gmail.com", "roottoor");
        OrderObject orderObj = new OrderObject("Automation test order ID:", "New automation test order description", "15", "1");
        LoginObject writerLogin = new LoginObject("debeers@bigmir.net", "H9CC1vxG");
        String writerText = "hello world, java is super cool but really hard languege! TestNG is my favourite framework! Peace!))) Skype, icq, telephone number";

        String filepath = System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\BigByte.doc";
        OrderInfoAndActions orderInfoAndActions = WriterGoToEndResultToClient.uploadFilesAndSendResultToTheClient(driver, clientLogin, orderObj, writerLogin, writerText, filepath);
        String filename = GeneralHelpers.getFileName(filepath);

        assertTrue($(orderInfoAndActions.waitForUploadingFilesToOrder(filename)).isDisplayed());
        orderInfoAndActions.clickOnTheSendCompletedOrderButton(driver);

    }


}
