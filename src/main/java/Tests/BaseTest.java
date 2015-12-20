package Tests;

import Entities.GmailCredentials;
import Entities.LoginObject;
import Registry.Registry;
import Utilities.DBconnection;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.selenium.factory.WebDriverFactory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static Utilities.PropertiesLoader.propertyXMLoader;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static String baseUrl;
    public static StringBuffer verificationErrors = new StringBuffer();
    public static LoginObject clientLogin;
    public static LoginObject writerLogin;
    public static GmailCredentials gmailCredentials;
    public static java.sql.Connection jdbcConnection;


//    @BeforeSuite(alwaysRun = true)
//    public void preCondition() throws IOException, ClassNotFoundException, SQLException {
//
//                Properties props =  propertyXMLoader(System.getProperty("user.dir") +
//                "/src/main/java/Utilities/SettingsXML/DB_CONNECTION.xml");
//
//      //  "\\src\\main\\java\\Helpers\\SettingsXML\\DB_CONNECTION.xml"); For Mustdie
//        jdbcConnection = new DBconnection().initDBConnection(props);
//        Registry.set("dbConnection", jdbcConnection);
//    }

    @Parameters({"URL", "clientLoginParam", "clientPasswordParam", "writerLoginParam", "writerPasswordParam", "mailbox", "mailboxPassword"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String URL, String clientLoginParam, String clientPasswordParam, String writerLoginParam,
                      String writerPasswordParam, String mailbox, String mailboxPassword) throws ClassNotFoundException, IOException, SQLException {

        String TestClassName = this.getClass().getName();
        System.out.println(TestClassName);

        clientLogin      = new LoginObject(clientLoginParam, clientPasswordParam);
        writerLogin      = new LoginObject(writerLoginParam, writerPasswordParam);
        gmailCredentials = new GmailCredentials(mailbox, mailboxPassword);

        baseUrl = URL;

//
//        // Setup firefox binary to start in Xvfb
//        String Xport = System.getProperty(
//                "lmportal.xvfb.id", ":98");
//        final File firefoxPath = new File(System.getProperty(
//                "lmportal.deploy.firefox.path", "/usr/bin/firefox"));
//        FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
//        firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);

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
      //  driver = new FirefoxDriver(firefoxBinary, null);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver); // Selenide WebDriverRunner for my custom driver
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {

        if (driver.getTitle() != "ContentMart") {
            driver.get("https://contentmart.in/exit");
        }

        driver.manage().deleteAllCookies();
        driver.quit();

        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }
}
