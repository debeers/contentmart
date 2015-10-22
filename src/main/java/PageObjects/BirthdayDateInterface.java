package PageObjects;

import org.openqa.selenium.WebElement;

/**
 * Created by DeBeers on 22.10.2015.
 */
public interface BirthdayDateInterface {

    public String getUserYearsOld();
    public WebElement selectDay();
    public WebElement selectYear();
    public WebElement selectMonth();
}
