package cc.kasumi.commons.util;

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
        player.setSaturation(10);
        player.setExhaustion(0.0F);
    }

    public static void feedPlayer(Player player) {
        player.setFoodLevel(20);
        player.setSaturation(10);
        player.setExhaustion(0.0F);
    }

    public static void clearInventory(Player player) {
        PlayerInventory inventory = player.getInventory();

        inventory.clear();
        inventory.setArmorContents(new ItemStack[4]);
    }

    public static void resetPlayer(Player player) {
        Collection<PotionEffect> effects = player.getActivePotionEffects();
        PlayerInventory inventory = player.getInventory();

        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }

        effects.clear();

        inventory.clear();
        inventory.setArmorContents(new ItemStack[4]);

        player.setExp(0.0F);
        player.setExhaustion(0.0F);
        player.setLevel(0);
        player.setTotalExperience(0);
        player.setFoodLevel(20);
        player.setHealth(20D);
        player.setFireTicks(0);
    }
}
