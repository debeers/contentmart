package Entities;

import java.util.HashMap;

/**
 * Created by DeBeers on 15.12.2015.
 */
public class Registry {

    public static Object get(String key) {
        return storage.get(key);
    }

    public static void set(String key, Object object){
        storage.put(key, object);
    }

    private static HashMap<String, Object> storage = new HashMap<>();

}
