package Tests;


import Entities.LoginObject;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import ru.stqa.selenium.factory.WebDriverFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {


    public static WebDriver driver;
    public static WebDriverWait wait;
    public static String baseUrl;
    public static StringBuffer verificationErrors = new StringBuffer();
    public static LoginObject clientLogin;
    public static LoginObject writerLogin;

    @Parameters({"URL", "clientLoginParam", "clientPasswordParam", "writerLoginParam", "writerPasswordParam"})
    @BeforeClass(alwaysRun = true)
    public void setUp(String URL, String clientLoginParam, String clientPasswordParam, String writerLoginParam, String writerPasswordParam) {

        clientLogin = new LoginObject(clientLoginParam, clientPasswordParam);
        writerLogin = new LoginObject(writerLoginParam, writerPasswordParam);

        baseUrl = URL;

        String path = System.getProperty("user.dir") + "\\src\\main\\java\\Downloaded_Files";
        File downloadDir = new File(path);
        FirefoxProfile fProfile = new FirefoxProfile();
        fProfile.setAcceptUntrustedCertificates(true);
        fProfile.setPreference("browser.download.dir", downloadDir.getAbsolutePath());
        fProfile.setPreference("browser.download.folderList", 2);
        fProfile.setPreference("browser.download.manager.showWhenStarting", false);
        fProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        fProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain");

        DesiredCapabilities dc = DesiredCapabilities.firefox();
        dc.setJavascriptEnabled(true);
        dc.setCapability(FirefoxDriver.PROFILE, fProfile);


        driver = WebDriverFactory.getDriver(dc);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver); // Selenide WebDriverRunner for my custom driver

    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {

        if (driver.getTitle() != "ContentMart") {
            driver.get("https://contentmart.in/exit");
        }

        driver.manage().deleteAllCookies(); //try incognito
        Thread.sleep(5000);
        driver.quit();

        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }


}













