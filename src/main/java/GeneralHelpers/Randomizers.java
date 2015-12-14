package GeneralHelpers;

import com.codeborne.selenide.Condition;
import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;


/**
 * Created by ilya on 28.08.2015.
 */
public class Randomizers {

    public static String setRandomUserNickName(String role) {
        if (role.equalsIgnoreCase("writer")) {
            return "WriterBOT-" + randomNumeric(4) + randomAlphabetic(3);
        } else return "ClientBOT-" + randomNumeric(4) + randomAlphabetic(3);
    }

}
