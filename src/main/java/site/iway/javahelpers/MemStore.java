package site.iway.javahelpers;

import java.util.concurrent.ConcurrentHashMap;

public class MemStore {

    private static final ConcurrentHashMap<String, Object> MAP;

    static {
        MAP = new ConcurrentHashMap<>();
    }

    public static Object put(String key, Object value) {
        return value == null ? MAP.remove(key) : MAP.put(key, value);
    }

    public static Object get(String key, Object defValue) {
        Object value = MAP.get(key);
        return value == null ? defValue : value;
    }

}
