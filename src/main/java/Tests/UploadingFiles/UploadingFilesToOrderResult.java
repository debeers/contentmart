package Tests.UploadingFiles;

import Actions.Writer.WriterGoToOrderWorkflow;
import DataProviders.WriterUploadingFilesForSendResult;
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

    @Test(groups={"regress 1.0"}, dataProvider= "UploadingFilesToSendResult", dataProviderClass = WriterUploadingFilesForSendResult.class)
    public static void UploadingFilesToOrderResult(Object clientLoginObject, Object orderObject, Object writerLoginObj, Object text) throws InterruptedException {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;
        LoginObject writerLogin = (LoginObject) writerLoginObj;
        String writerText = text.toString();

        String filepath = System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\BigByte.doc";
        OrderInfoAndActions orderInfoAndActions = WriterGoToOrderWorkflow.uploadFilesAndSendResultToTheClient(driver, clientLogin, orderObj, writerLogin, writerText, filepath);
        String filename = GeneralHelpers.getFileName(filepath);

        assertTrue($(orderInfoAndActions.waitForUploadingFilesToOrder(filename)).isDisplayed());
        orderInfoAndActions.clickOnTheSendCompletedOrderButton(driver);

    }


}
