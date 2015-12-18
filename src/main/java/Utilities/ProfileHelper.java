package Utilities;

import PageObjects.BirthdayDateInterface;
import PageObjects.PageObjectWithImages;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by DeBeers on 22.10.2015.
 */
public class ProfileHelper { // I know that this class looks like treshholder. But it contains all methods for test user Profile. I`ll delete this class and separate all the methods for them classes when test will be done.

    public static GregorianCalendar userSelectDateOfBirth(BirthdayDateInterface page) {

        Select day   = new Select(page.selectDay());
        Select month = new Select(page.selectMonth());
        Select year  = new Select(page.selectYear());

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


    private static Boolean isImagePresent(PageObjectWithImages page) throws NoSuchFieldException {

        Boolean ImagePresent = (Boolean) ((JavascriptExecutor) page.getDriver()).executeScript
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


    public static void downloadFileFromURL(PageObjectWithImages page, File file) {

        try {
            FileUtils.copyURLToFile(page.getImageURL(), file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static File writeImageToTemp(PageObjectWithImages page) {

        try {
            File temp = File.createTempFile("uploadedImage", ".jpg");

            downloadFileFromURL(page, temp);

            System.out.println("======== Downloading done =========");
            System.out.println("File path: " + temp.getAbsolutePath());

            return temp;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getFileHash(File file) {

        try (FileInputStream fis = new FileInputStream(file)) {

            String md5 = DigestUtils.md5Hex(fis);
            fis.close();
            System.out.println("File MD5 is: " + md5);
            return md5;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Boolean compareMD5(File srcFile, File downloadedFile) {

        //noinspection ConstantConditions
        if (getFileHash(srcFile).equals(getFileHash(downloadedFile))) {
            System.out.println("Files equals, congrats!");

            return true;
        }
        System.out.println("Files not equals, go and kill yourself.");
        return false;
    }


    public static Boolean verifyUploadedImages(PageObjectWithImages page, String path)
            throws IOException, NoSuchFieldException, NoSuchMethodException {

        File scrFile = new File(path);

        if (!isImagePresent(page)) {
            System.out.println("Image not displayed.");

            return false;

        } else {
            System.out.println("Image displayed.");

            if (isImagesEquals(page, path) && compareMD5(scrFile, writeImageToTemp(page))) {

                System.out.println("Got it bitch ass! Images totally equals!) HURRAY!");
                return true;

            } else System.out.println("Images not equals baby((("); // for now all out`s will be on them places, untill we`re not finnish with Jenkins integretion. Till that time I need it for debugging.

            return false;
        }
    }
}
