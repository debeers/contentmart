package GeneralHelpers;

/**
 * Created by CMG_TEST on 26.08.2015.
 */
public class CreateNewOrderHelper {

    public static String randomID() {

        int id = (int) (Math.random() * 1000);
        String str = String.valueOf(id);
        return str;
    }

    public static String getDay() {

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int i = calendar.get(calendar.DAY_OF_MONTH);
        if (i == 30 || i == 31) {
            i = 2;
        } else if (i <= 29) {
            i += 1;
        }
        String day = Integer.toString(i);
        System.out.println(day);

        return day;
    }

}
