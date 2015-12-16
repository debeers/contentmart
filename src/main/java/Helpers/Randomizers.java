package Helpers;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;


/**
 * Created by ilya on 28.08.2015.
 */
public class Randomizers {

    public static String setRandomUserNickName(String role) {
        if (role.equalsIgnoreCase("writer")) {
            return "WriterBOT-" + DateTimeUtils.getTimestamp();
        } else return "ClientBOT-" + DateTimeUtils.getTimestamp();
    }

    public static String randomID() {

        int id = (int) (Math.random() * 1000);
        String str = String.valueOf(id);

        return str;
    }

    public static String createRandomUserEmail(){
        return  DateTimeUtils.getTimestamp() + "@testmail.com' ";
    }

    public static String createNewOrderName(){
        return "Automation order ID: " + DateTimeUtils.getTimestamp();
    }

    public static String randomTextGeneratorLength(int length) {

        Random ran = new Random();
        int top = length;
        char data = ' ';
        String dat = "";

        for (int i = 0; i <= top; i++) {
            data = (char) (ran.nextInt(25) + 97);
            dat = data + dat;
        }
        System.out.println(dat);

        return dat;
    }
}
