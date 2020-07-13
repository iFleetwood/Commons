package cc.kasumi.commons.util;

import java.util.concurrent.ThreadLocalRandom;

public class NumberUtil {

    public static int getRandomInt(int max) {
        return ThreadLocalRandom.current().nextInt(max);
    }

    public static int getRandomInt(int max, int min) {
        return ThreadLocalRandom.current().nextInt(max - min) + min;
    }

    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
