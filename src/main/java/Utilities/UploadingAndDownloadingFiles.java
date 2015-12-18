package Utilities;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 14.12.2015.
 */
public class UploadingAndDownloadingFiles {


    public static void uploadFileToHidenInput(WebDriver driver, String filepath) throws InterruptedException {

        String xPath = ".//*[@id='fileupload']";
        JSWorker.jsDeleteClasses(xPath, driver);
        $(By.xpath(xPath)).shouldBe(Condition.visible).sendKeys(filepath);
    }

    public static String getFileName(String path) {

        String resultStr = path.substring(path.indexOf("resources") + 10, path.lastIndexOf('.'));
        System.out.println("You will upload file with name: " + resultStr);
        return resultStr;
    }

    public static boolean isFileExists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean isFileUploaded(String filePath, List<WebElement> elementList) {
        String fileName = getFileName(filePath);

        for (WebElement el : elementList) {

            if (el.getText().contains(fileName)) {

                System.out.println("File successfully found! " + el);
                return true;

            } else {

                System.out.println("File not found!!!");
                return false;
            }
        }
        return true;
    }

    public static void uploadFilesByRobot(WebElement element, String filepath) throws AWTException, InterruptedException {

        $(element).shouldBe(Condition.visible).click();

        StringSelection ss = new StringSelection(filepath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
