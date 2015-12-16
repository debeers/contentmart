package Helpers;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static Repository.UserModelRepo.getUserTimezoneNameByMail;
import static Repository.UserModelRepo.getUserTimezoneViewNameByMail;

/**
 * Created by DeBeers on 03.12.2015.
 */
public class DateTimeUtils {

    public static String getEtalonTimezone(String timezone){

        DateTimeZone tz = DateTimeZone.forTimeZone(TimeZone.getTimeZone(timezone));
        Long instant = DateTime.now().getMillis();
        String name = tz.getName(instant);
        System.out.println(name);
        return"(UTC " + name + ")";
    }

    public static String getUserTimezoneName(String email){
         return getUserTimezoneNameByMail(email);
    }

    public static String getUserTimezoneViewName(String email){
        DBUtill dbUtill = new DBUtill();
        return dbUtill.getColumn(getUserTimezoneViewNameByMail(email), "view_name");
    }

    public static String getSystemDate() {
        return new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
    }

    public static String getSystemTime_24() {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public static String getSystemTime_AM_PM() {
        return new SimpleDateFormat("hh:mm a").format(Calendar.getInstance().getTime());
    }

    public static String getDay() {

        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_MONTH);
        if (i == 30 || i == 31) {
            i = 2;
        } else if (i <= 29) {
            i += 1;
        }
        return Integer.toString(i);
    }

    public static String getTimestamp(){
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }
}
