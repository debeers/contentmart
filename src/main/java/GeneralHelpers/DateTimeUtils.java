package GeneralHelpers;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

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
}
