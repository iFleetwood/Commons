package cc.kasumi.commons.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class PlayerUtil {

    public static boolean isPlayerOnline(Player player) {
        return player != null && player.isOnline();
    }

    public static void healPlayer(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExhaustion(0.0F);
    }

    public static void feedPlayer(Player player) {
        player.setFoodLevel(20);
        player.setExhaustion(0.0F);
    }

    public static void resetPlayer(Player player) {
        Collection<PotionEffect> effects = player.getActivePotionEffects();
        PlayerInventory inventory = player.getInventory();

        effects.forEach(effects::remove);
        inventory.clear();
        inventory.setArmorContents(new ItemStack[4]);

        player.setExp(0.0F);
        player.setExhaustion(0.0F);
        player.setTotalExperience(0);
        player.setFoodLevel(20);
        player.setHealth(20D);
        player.setFireTicks(1);
    }

    public static int getPlayerPing(Player player) {
        try {
            // Building the version of the server in such a form we can use it
            // in NMS code.
            String bukkitVersion = Bukkit.getServer().getClass().getPackage()
                    .getName().substring(23);
            // Getting craftplayer
            Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit."
                    + bukkitVersion + ".entity.CraftPlayer");
            // Invoking method getHandle() for the player
            Object handle = craftPlayer.getMethod("getHandle").invoke(player);
            // Getting field "ping" that holds player's ping obviously
            // Returning the ping
            return (Integer) handle.getClass().getDeclaredField("ping").get(handle);

        } catch (ClassNotFoundException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException
                | NoSuchFieldException e) {
            // Handle exceptions however you like, i chose to return value of
            // -1; since player's ping can't be -1.
            return -1;
        }
    }
}
