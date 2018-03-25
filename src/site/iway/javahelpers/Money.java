package site.iway.javahelpers;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Money implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final long MAX_CENTS = +999999999999L;
    public static final long DEF_CENTS = 0L;
    public static final long MIN_CENTS = -999999999999L;

    public static final Money ZERO = new Money(DEF_CENTS);
    public static final Money MAX = new Money(MAX_CENTS);
    public static final Money MIN = new Money(MIN_CENTS);

    private volatile long mCents;

    private void setFromLong(long cents) {
        if (cents > MAX_CENTS)
            throw new RuntimeException("value is too big !");
        if (cents < MIN_CENTS)
            throw new RuntimeException("value is too small !");
        mCents = cents;
    }

    private void setFromDouble(double value) {
        if (value < 0d)
            setFromLong((long) (value * 100 - 0.5d));
        if (value > 0d)
            setFromLong((long) (value * 100 + 0.5d));
    }

    public Money(long cents) {
        setFromLong(cents);
    }

    public Money(double value) {
        setFromDouble(value);
    }

    public Money(String value) {
        if (StringHelper.nullOrEmpty(value)) {
            value = "0.0";
        }
        setFromDouble(Double.parseDouble(value));
    }

    public Money() {
        mCents = DEF_CENTS;
    }

    public long cents() {
        return mCents;
    }

    public Money plus(Money money) {
        return new Money(mCents + money.mCents);
    }

    public Money minus(Money money) {
        return new Money(mCents - money.mCents);
    }

    public Money multiply(Money money) {
        return new Money(mCents * money.mCents);
    }

    public Money divide(Money money) {
        return new Money(mCents / money.mCents);
    }

    public Money reverse() {
        return new Money(-mCents);
    }

    public boolean biggerThan(Money money) {
        return mCents > money.mCents;
    }

    public boolean smallerThan(Money money) {
        return mCents < money.mCents;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Money) {
            return mCents == ((Money) o).mCents;
        }
        return false;
    }

    public boolean isZero() {
        return mCents == 0L;
    }

    public boolean isMax() {
        return mCents == MAX_CENTS;
    }

    public boolean isMin() {
        return mCents == MIN_CENTS;
    }

    private static final NumberFormat sNormalFormatter;
    private static final NumberFormat sStandardFormatter;
    private static final NumberFormat sCurrencyFormatter;

    static {
        sNormalFormatter = NumberFormat.getNumberInstance(Locale.CHINA);
        sNormalFormatter.setMinimumFractionDigits(2);
        sNormalFormatter.setMaximumFractionDigits(2);
        sNormalFormatter.setGroupingUsed(false);

        sStandardFormatter = NumberFormat.getNumberInstance(Locale.CHINA);
        sStandardFormatter.setMaximumIntegerDigits(12);
        sStandardFormatter.setMinimumIntegerDigits(12);
        sStandardFormatter.setParseIntegerOnly(true);
        sStandardFormatter.setGroupingUsed(false);

        sCurrencyFormatter = NumberFormat.getNumberInstance(Locale.CHINA);
        sCurrencyFormatter.setMinimumFractionDigits(2);
        sCurrencyFormatter.setMaximumFractionDigits(2);
        sCurrencyFormatter.setGroupingUsed(true);
    }

    public double toDoubleFormat() {
        double doubleFormat = mCents;
        return doubleFormat / 100;
    }

    public String toNormalFormat() {
        return sNormalFormatter.format(toDoubleFormat());
    }

    public String toStandardFormat() {
        return sStandardFormatter.format(mCents);
    }

    public String toCurrencyFormat() {
        return sCurrencyFormatter.format(toDoubleFormat());
    }

    private String getBooleanString(int qian, int bai, int shi, int ge) {
        char[] chars = new char[4];
        chars[0] = qian == 0 ? '0' : '1';
        chars[1] = bai == 0 ? '0' : '1';
        chars[2] = shi == 0 ? '0' : '1';
        chars[3] = ge == 0 ? '0' : '1';
        return new String(chars);
    }

    private String getBooleanString(String yi, String wan, String yuan) {
        char[] chars = new char[3];
        chars[0] = yi.equals("零") ? '0' : '1';
        chars[1] = wan.equals("零") ? '0' : wan.length() > 1 && wan.charAt(1) == '仟' ? '2' : '1';
        chars[2] = yuan.equals("零") ? '0' : wan.length() > 1 && wan.charAt(1) == '仟' ? '2' : '1';
        return new String(chars);
    }

    private String getBooleanString(String number1, int jiao, int fen) {
        char[] chars = new char[3];
        chars[0] = number1.equals("零") ? '0' : '1';
        chars[1] = jiao == 0 ? '0' : '1';
        chars[2] = fen == 0 ? '0' : '1';
        return new String(chars);
    }

    private String getNumber(int qian, int bai, int shi, int ge) {
        String booleanString = getBooleanString(qian, bai, shi, ge);
        char[] digits = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
        if (booleanString.equals("0000"))
            return "零";
        if (booleanString.equals("0001"))
            return digits[ge] + "";
        if (booleanString.equals("0010"))
            return digits[shi] + "拾";
        if (booleanString.equals("0011"))
            return digits[shi] + "拾" + digits[ge];
        if (booleanString.equals("0100"))
            return digits[bai] + "佰";
        if (booleanString.equals("0101"))
            return digits[bai] + "佰" + "零" + digits[ge];
        if (booleanString.equals("0110"))
            return digits[bai] + "佰" + digits[shi] + "拾";
        if (booleanString.equals("0111"))
            return digits[bai] + "佰" + digits[shi] + "拾" + digits[ge];
        if (booleanString.equals("1000"))
            return digits[qian] + "仟";
        if (booleanString.equals("1001"))
            return digits[qian] + "仟" + "零" + digits[ge];
        if (booleanString.equals("1010"))
            return digits[qian] + "仟" + "零" + digits[shi] + "拾";
        if (booleanString.equals("1011"))
            return digits[qian] + "仟" + "零" + digits[shi] + "拾" + digits[ge];
        if (booleanString.equals("1100"))
            return digits[qian] + "仟" + digits[bai] + "佰";
        if (booleanString.equals("1101"))
            return digits[qian] + "仟" + digits[bai] + "佰" + "零" + digits[ge];
        if (booleanString.equals("1110"))
            return digits[qian] + "仟" + digits[bai] + "佰" + digits[shi] + "拾";
        if (booleanString.equals("1111"))
            return digits[qian] + "仟" + digits[bai] + "佰" + digits[shi] + "拾" + digits[ge];
        return null;
    }

    private String getNumber1(String yi, String wan, String yuan) {
        String booleanString = getBooleanString(yi, wan, yuan);
        if (booleanString.equals("000"))
            return yuan;
        if (booleanString.equals("001"))
            return yuan;
        if (booleanString.equals("002"))
            return yuan;
        if (booleanString.equals("010"))
            return wan + "万";
        if (booleanString.equals("011"))
            return wan + "万" + "零" + yuan;
        if (booleanString.equals("012"))
            return wan + "万" + yuan;
        if (booleanString.equals("020"))
            return wan + "万";
        if (booleanString.equals("021"))
            return wan + "万" + "零" + yuan;
        if (booleanString.equals("022"))
            return wan + "万" + yuan;
        if (booleanString.equals("100"))
            return yi + "亿";
        if (booleanString.equals("101"))
            return yi + "亿" + "零" + yuan;
        if (booleanString.equals("102"))
            return yi + "亿" + "零" + yuan;
        if (booleanString.equals("110"))
            return yi + "亿" + "零" + wan + "万";
        if (booleanString.equals("111"))
            return yi + "亿" + "零" + wan + "万" + "零" + yuan;
        if (booleanString.equals("112"))
            return yi + "亿" + "零" + wan + "万" + yuan;
        if (booleanString.equals("120"))
            return yi + "亿" + wan + "万";
        if (booleanString.equals("121"))
            return yi + "亿" + wan + "万" + "零" + yuan;
        if (booleanString.equals("122"))
            return yi + "亿" + wan + "万" + yuan;
        return null;
    }

    private String getNumber2(String number1, int jiao, int fen) {
        String booleanString = getBooleanString(number1, jiao, fen);
        char[] digits = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
        if (booleanString.equals("000"))
            return number1 + "元整";
        if (booleanString.equals("001"))
            return digits[fen] + "分";
        if (booleanString.equals("010"))
            return digits[jiao] + "角";
        if (booleanString.equals("011"))
            return digits[jiao] + "角" + digits[fen] + "分";
        if (booleanString.equals("100"))
            return number1 + "元整";
        if (booleanString.equals("101"))
            return number1 + "元" + "零" + digits[fen] + "分";
        if (booleanString.equals("110"))
            return number1 + "元" + digits[jiao] + "角";
        if (booleanString.equals("111"))
            return number1 + "元" + digits[jiao] + "角" + digits[fen] + "分";
        return null;
    }

    public String toChineseFormat() {
        long absCents = Math.abs(mCents);
        String standardFormat = sStandardFormatter.format(absCents);
        char[] array = standardFormat.toCharArray();
        String yi = getNumber(0, 0, array[0] - '0', array[1] - '0');
        String wan = getNumber(array[2] - '0', array[3] - '0', array[4] - '0', array[5] - '0');
        String yuan = getNumber(array[6] - '0', array[7] - '0', array[8] - '0', array[9] - '0');
        String number1 = getNumber1(yi, wan, yuan);
        String number2 = getNumber2(number1, array[10] - '0', array[11] - '0');
        if (mCents < 0)
            return "负" + number2;
        else
            return number2;
    }

}
