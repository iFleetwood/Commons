package cc.kasumi.commons.util;

import java.util.List;

public class StringUtil {

    public static String getFormattedString(List<String> strings) {
        StringBuilder builder = new StringBuilder();

        int i = 1;
        for (String string : strings) {
            builder.append(string);

            if (i < strings.size()) {
                builder.append(", ");
            } else {
                builder.append(".");
            }


            i++;
        }

        return builder.toString();
    }
}
