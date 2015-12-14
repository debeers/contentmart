package GeneralHelpers;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static SQLRepo.General.getUserTimezoneNameByMail;
import static SQLRepo.General.getUserTimezoneViewNameByMail;

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

    public static String getUserTimezoneName(DBUtill dbUtill, String email){

        return dbUtill.getColumn(getUserTimezoneNameByMail(email), "name");
    }

    public static String getUserTimezoneViewName(DBUtill dbUtill, String email){

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
}
