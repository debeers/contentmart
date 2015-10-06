package GeneralHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

/**
 * Created by ilya on 05.09.2015.
 */
public class Messages {


    public static String randomMessageGeneratorLength(int length){

        Random ran = new Random();
        int top = length;
        char data = ' ';
        String dat = "";

        for (int i=0; i<=top; i++) {
            data = (char)(ran.nextInt(25)+97);
            dat = data + dat;
        }

        System.out.println(dat);

        return dat;
    }


}
