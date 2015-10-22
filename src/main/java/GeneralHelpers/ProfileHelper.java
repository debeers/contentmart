package GeneralHelpers;

import PageObjects.BirthdayDateInterface;
import PageObjects.PageObjectWithImages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by DeBeers on 22.10.2015.
 */
public class ProfileHelper {


    public static GregorianCalendar userSelectDateOfBirth(BirthdayDateInterface page) {

        Select day = new Select(page.selectDay());
        Select month = new Select(page.selectMonth());
        Select year = new Select(page.selectYear());

        day.selectByIndex(new Random().nextInt(day.getOptions().size()));
        month.selectByIndex(new Random().nextInt(month.getOptions().size()));
        year.selectByIndex(new Random().nextInt(year.getOptions().size()));


        GregorianCalendar birthDaySet = new GregorianCalendar(

                Integer.parseInt(year.getFirstSelectedOption().getText()),
                Integer.parseInt(month.getFirstSelectedOption().getAttribute("value")),
                Integer.parseInt(day.getFirstSelectedOption().getAttribute("value"))
        );

        return birthDaySet;
    }


    private static GregorianCalendar getCurrentDate() {

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        GregorianCalendar checkDay = new GregorianCalendar(

                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        return checkDay;
    }


    private static int calcYears(GregorianCalendar birthDaySet) {

        GregorianCalendar checkDay = getCurrentDate();

        int years = checkDay.get(GregorianCalendar.YEAR) - birthDaySet.get(GregorianCalendar.YEAR);
        int checkMonth = checkDay.get(GregorianCalendar.MONTH);
        int birthMonth = birthDaySet.get(GregorianCalendar.MONTH);

        if (checkMonth < birthMonth) {
            years--;

        } else if (checkMonth == birthMonth
                && checkDay.get(GregorianCalendar.DAY_OF_MONTH) < birthDaySet.get(GregorianCalendar.DAY_OF_MONTH)) {

            years--;
        }
        return years;
    }


    public static Boolean userCheckForOld(GregorianCalendar birthDaySet, BirthdayDateInterface page) {

        if (calcYears(birthDaySet) == Integer.parseInt(page.getUserYearsOld())) {

            System.out.println("Writer age is: " + calcYears(birthDaySet) + " years, TEST OK");
            return true;

        } else
            System.out.println("Writer age is not correct, please check it manually");
        return false;
    }


    private static Boolean isImagePresent(WebDriver driver, PageObjectWithImages page) throws NoSuchFieldException {

        Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript
                ("return arguments[0].complete && typeof arguments[0].naturalWidth != " +
                        "\"undefined\" && arguments[0].naturalWidth > 0", page != null ? page.imgSrcElement() : null);
        if (ImagePresent) {
            return true;
        }
        return false;
    }


    private static Boolean isImagesEquals(PageObjectWithImages page,
                                          String path) throws IOException, NoSuchMethodException {

        BufferedImage readImage;
        readImage = ImageIO.read(new File(path));

        int srcHeigh = readImage.getHeight();
        int srcWidth = readImage.getWidth();
        int imgHeigh = page.getImgHolderHeigh();
        int imgWidth = page.getImgHolderWidth();


        System.out.println("Src Image heigh = " + srcHeigh + "\n"
                + "Src Image width = " + srcWidth);

        if ((srcHeigh == imgHeigh) && (srcWidth == imgWidth)) {
            System.out.println("Uploaded Img heigh = " + imgHeigh + "\n" +
                    "Uploaded Img width = " + imgWidth);

            return true;

        } else {

            System.out.println("File broken or not uploaded correctly...");
            return false;
        }

    }


    public static Boolean verifyUploadedImages(WebDriver driver, PageObjectWithImages page,
                                               String path) throws IOException, NoSuchFieldException, NoSuchMethodException {

        if (!isImagePresent(driver, page)) {
            System.out.println("Image not displayed.");

            return false;

        } else {
            System.out.println("Image displayed.");

            if (isImagesEquals(page, path)) {
                System.out.println("Got it bitch ass! Images totally equals!) HURRAY!");

                return true;

            } else
                System.out.println("Images not equals baby(((");

            return false;
        }

    }

}
