package site.iway.javahelpers;

public class CharHelper {

    public static boolean isASCII(char c) {
        return c < 128;
    }

    public static boolean isASCIINumbers(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isASCIILetters(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static boolean isASCIIPunctuations(char c) {
        return isASCIIVisible(c) && !isASCIINumbers(c) && !isASCIILetters(c);
    }

    public static boolean isASCIIVisible(char c) {
        return c > ' ' && c < 127;
    }

    public static boolean isASCIIInvisible(char c) {
        return c <= ' ' || c == 127;
    }

}
