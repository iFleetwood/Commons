package cc.kasumi.commons.util;

import java.util.concurrent.TimeUnit;

public class TimeUtil {

    public static String getTimeFormatted(long time) {
        StringBuilder builder = new StringBuilder();

        long seconds = TimeUnit.MILLISECONDS.toSeconds(time) % 60;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time) % 60;
        long hours = TimeUnit.MILLISECONDS.toHours(time) % 24;

        String hoursFormatted = hours < 10 ? "0" + hours : Long.toString(hours);

        builder.append(hours < 1  ? "" : hoursFormatted + ":");
        builder.append(minutes < 10 ? "0" + minutes : Long.toString(minutes)).append(":");
        builder.append(seconds < 10 ? "0" + seconds : Long.toString(seconds));

        return builder.toString();
    }

    public static String getMinSecFormatted(long i) {
        if (i >= 60) {
            return i > 60 ? "minutes" : "minute";
        }

        return i > 1 ? "seconds" : "second";
    }
}
