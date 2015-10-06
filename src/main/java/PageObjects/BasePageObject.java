package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by CMG_TEST on 31.08.2015.
 */
public abstract class BasePageObject {


    protected static WebDriver driver;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        this.init(driver);
    }

    protected void init(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }
}

