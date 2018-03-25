package site.iway.javahelpers;

import java.text.Collator;
import java.util.Comparator;

public class PinYinComparator implements Comparator<String> {

    private Collator mCollator = Collator.getInstance(java.util.Locale.CHINA);

    @Override
    public int compare(String lhs, String rhs) {
        return mCollator.compare(lhs, rhs);
    }

}
