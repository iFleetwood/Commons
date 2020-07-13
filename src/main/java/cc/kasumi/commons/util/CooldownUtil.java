package cc.kasumi.commons.util;

import java.util.concurrent.TimeUnit;

public class CooldownUtil {

    public static String getCooldownTimeFormatted(long time) {
        StringBuilder builder = new StringBuilder();

        long days = TimeUnit.MILLISECONDS.toDays(time) % 365;
        long hours = TimeUnit.MILLISECONDS.toHours(time) % 24;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time) % 60;

        String daysFormatted = days > 0 ? days + addS(days, " day") + " "  : "";
        String hoursFormatted = hours > 0 ? hours + addS(hours, " hour") + " " : "";
        String minutesFormatted = minutes > 0 ? minutes + addS(minutes, " minute") + " " : "";
        String secondsFormatted = seconds > 0 ? seconds + addS(seconds, " second") : "1 second";

        builder.append(daysFormatted);
        builder.append(hoursFormatted);
        builder.append(minutesFormatted);
        builder.append(secondsFormatted);

        return builder.toString();
    }

    private static String addS(long time, String string) {
        return time == 1 ? string : string + "s";
    }
}
