package site.iway.javahelpers;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

public class MemCache {

    private static final ConcurrentHashMap<String, SoftReference<Object>> MAP;

    static {
        MAP = new ConcurrentHashMap<>();
    }

    public static Object put(String key, Object value) {
        SoftReference<Object> oldValue = value == null ? MAP.remove(key) : MAP.put(key, new SoftReference<>(value));
        return oldValue == null ? null : oldValue.get();
    }

    public static Object get(String key, Object defValue) {
        SoftReference<Object> value = MAP.get(key);
        return value == null ? defValue : value.get();
    }

}
