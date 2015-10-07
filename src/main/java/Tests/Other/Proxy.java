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
        // запуск прокси сервера                     // I need comment`s on russian for better understanding of proxy work, I`ll delete it in future not now
        ProxyServer server = new ProxyServer(4444);
        server.start();

        // получение Selenium proxy
        org.openqa.selenium.Proxy proxy = server.seleniumProxy();

        // конфигурация FirefoxDriver для использования прокси
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        WebDriver driver = new FirefoxDriver(capabilities);

        // создание HAR с меткой "yandex.ru"
        server.newHar("dev.contentmart.in");

        // открытие страницы
        driver.get("http://dev.contentmart.in");

        // получение данных HAR
        Har har = server.getHar();

        // получить информацию о браузере
        System.out.println(har.getLog().getBrowser().getName());
        System.out.println(har.getLog().getBrowser().getVersion());

        // список всех обработанных запросов
        for (HarEntry entry : har.getLog().getEntries()) {

            System.out.println(entry.getRequest().getUrl());
            // время ожидания ответа от сервера в миллисекундах
            System.out.println(entry.getTimings().getWait());
            // время чтения ответа от сервера в миллисекундах
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

        // здесь будет обработка полученных данных

        driver.quit();
        server.stop();
    }


}
