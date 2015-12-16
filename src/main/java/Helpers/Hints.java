package Helpers;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DeBeers on 14.12.2015.
 */
public class Hints {
    public static List<String> hintSeeker(List<WebElement> hints) {
        List<String> hintsText = new ArrayList<>();
        for (WebElement hintText : hints) {
            hintsText.add(hintText.getAttribute("data-value"));
        }
        return hintsText;
    }

    public static Boolean hintComparator(List<WebElement> hints, List<String> matcher) {
        return CollectionUtils.isEqualCollection(hintSeeker(hints), matcher);
    }
}
