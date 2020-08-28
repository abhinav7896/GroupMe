package util;

public class RandomUtil {

    private static final String ALPHA_NUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String alphaNumeric(int size) {
        StringBuilder builder = new StringBuilder();
        while (size-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC.length());
            builder.append(ALPHA_NUMERIC.charAt(character));
        }
        return builder.toString();
    }
}
