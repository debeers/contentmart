package PageObjects;

import org.openqa.selenium.WebDriver;

/**
 * Created by DeBeers on 27.10.2015.
 */
public interface BasePageObjectInterface {

    public WebDriver getDriver();

    default public String getName(String name){
        return name;
    }
}
