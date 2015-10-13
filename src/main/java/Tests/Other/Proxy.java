package Tests.Other;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.ProxyServer;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by CMG_TEST on 29.09.2015.
 */
public class Proxy {

    @Test
    public void testMobProxyServer() throws Exception {

        ProxyServer server = new ProxyServer(4444);
        server.start();

        org.openqa.selenium.Proxy proxy = server.seleniumProxy();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        WebDriver driver = new FirefoxDriver(capabilities);

        server.newHar("dev.contentmart.in");
        driver.get("http://dev.contentmart.in");

        Har har = server.getHar();

        System.out.println(har.getLog().getBrowser().getName());
        System.out.println(har.getLog().getBrowser().getVersion());


        for (HarEntry entry : har.getLog().getEntries()) {

            System.out.println(entry.getRequest().getUrl());
            System.out.println(entry.getTimings().getWait());
            System.out.println(entry.getTimings().getReceive());
        }


        File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\HAResults\\"
                + har.getLog().getBrowser().getName() + ".har");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        try {
            har.writeTo(fos);
        } finally {
            fos.close();
        }

        driver.quit();
        server.stop();
    }
}
