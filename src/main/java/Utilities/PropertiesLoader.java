package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by DeBeers on 26.11.2015.
 */
public class PropertiesLoader {

    public static Properties propertyXMLoader(String path) throws FileNotFoundException, IOException {
        Properties props = new Properties();
        props.loadFromXML(new FileInputStream(path));
        return props;
    }
}
