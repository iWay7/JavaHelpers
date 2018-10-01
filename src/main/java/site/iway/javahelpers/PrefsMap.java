package site.iway.javahelpers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class PrefsMap extends ConcurrentHashMap<String, Object> {

    public PrefsMap() {
        super();
    }

    public PrefsMap(int initialCapacity) {
        super(initialCapacity);
    }

    public PrefsMap(Map<? extends String, ?> m) {
        super(m);
    }

    public PrefsMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public PrefsMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
        super(initialCapacity, loadFactor, concurrencyLevel);
    }

}
