package cc.kasumi.commons.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {

    private static final String HOUR_FORMAT = "%02d:%02d:%02d";
    private static final String MINUTE_FORMAT = "%02d:%02d";

    private TimeUtil() {
        throw new RuntimeException("Cannot instantiate a utility class.");
    }

    public static String millisToTimer(long millis) {
        long seconds = millis / 1000L;

        if (seconds > 3600L) {
            return String.format(HOUR_FORMAT, seconds / 3600L, seconds % 3600L / 60L, seconds % 60L);
        } else {
            return String.format(MINUTE_FORMAT, seconds / 60L, seconds % 60L);
        }
    }

    /**
     * Return the amount of seconds from milliseconds.
     * Note: We explicitly use 1000.0F (float) instead of 1000L (long).
     *
     * @param millis the amount of time in milliseconds
     * @return the seconds
     */
    public static String millisToSeconds(long millis) {
        return new DecimalFormat("#0.0").format(millis / 1000.0F);
    }

    public static String dateToString(Date date, String secondaryColor) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return new SimpleDateFormat("MMM dd yyyy " + (secondaryColor == null ? "" : secondaryColor) +
                "(hh:mm aa zz)").format(date);
    }

    public static Timestamp addDuration(long duration) {
        return truncateTimestamp(new Timestamp(System.currentTimeMillis() + duration));
    }

    public static Timestamp truncateTimestamp(Timestamp timestamp) {
        if (timestamp.toLocalDateTime().getYear() > 2037) {
            timestamp.setYear(2037);
        }

        return timestamp;
    }

    public static Timestamp addDuration(Timestamp timestamp) {
        return truncateTimestamp(new Timestamp(System.currentTimeMillis() + timestamp.getTime()));
    }

    public static Timestamp fromMillis(long millis) {
        return new Timestamp(millis);
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String millisToRoundedTime(long millis) {
        millis += 1L;

        long seconds = millis / 1000L;
        long minutes = seconds / 60L;
        long hours = minutes / 60L;
        long days = hours / 24L;
        long weeks = days / 7L;
        long months = weeks / 4L;
        long years = months / 12L;

        if (years > 0) {
            return years + " year" + (years == 1 ? "" : "s");
        } else if (months > 0) {
            return months + " month" + (months == 1 ? "" : "s");
        } else if (weeks > 0) {
            return weeks + " week" + (weeks == 1 ? "" : "s");
        } else if (days > 0) {
            return days + " day" + (days == 1 ? "" : "s");
        } else if (hours > 0) {
            return hours + " hour" + (hours == 1 ? "" : "s");
        } else if (minutes > 0) {
            return minutes + " minute" + (minutes == 1 ? "" : "s");
        } else {
            return seconds + " second" + (seconds == 1 ? "" : "s");
        }
    }

    public static long parseTime(String time) {
        long totalTime = 0L;
        boolean found = false;
        Matcher matcher = Pattern.compile("\\d+\\D+").matcher(time);

        while (matcher.find()) {
            String s = matcher.group();
            Long value = Long.parseLong(s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")[0]);
            String type = s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")[1];

            switch (type) {
                case "s":
                    totalTime += value;
                    found = true;
                    break;
                case "m":
                    totalTime += value * 60;
                    found = true;
                    break;
                case "h":
                    totalTime += value * 60 * 60;
                    found = true;
                    break;
                case "d":
                    totalTime += value * 60 * 60 * 24;
                    found = true;
                    break;
                case "w":
                    totalTime += value * 60 * 60 * 24 * 7;
                    found = true;
                    break;
                case "M":
                    totalTime += value * 60 * 60 * 24 * 30;
                    found = true;
                    break;
                case "y":
                    totalTime += value * 60 * 60 * 24 * 365;
                    found = true;
                    break;
            }
        }

        return !found ? -1 : totalTime * 1000;
    }

    // Original Commons

    public static String getCooldownTimeFormatted(long time) {
        StringBuilder builder = new StringBuilder();

        long days = Math.round(TimeUnit.MILLISECONDS.toDays(time) % 365.2422D);
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
