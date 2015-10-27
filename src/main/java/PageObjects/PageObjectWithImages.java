package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URL;

/**
 * Created by DeBeers on 21.10.2015.
 */
public interface PageObjectWithImages {

    public WebDriver getDriver();

    public URL getImageURL();

    public int getImgHolderHeigh();

    public int getImgHolderWidth();

    public WebElement imgSrcElement();
}
