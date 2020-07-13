package cc.kasumi.commons.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtil {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String strip(String message) {
        return ChatColor.stripColor(message);
    }

    public static String translateRaw(String message, String target, String replacement) {
        return color(message.replace(target, replacement));
    }

    public static String translateRaw(String message, String target, int replacement) {
        return translateRaw(message, target , Integer.toString(replacement));
    }

    public static void sendStaffMessage(String message, String permission) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(permission)) {
                player.sendMessage(message);
            }
        }
    }

    public static String messageBuilder(int startArg, String[] args) {
        StringBuilder message = new StringBuilder();

        int length = args.length;

        for (int i = startArg; i < length; i++) {
            message.append(args[i]);

            if (i == length - 1) {
                break;
            }

            message.append(" ");
        }

        return message.toString();
    }
}
