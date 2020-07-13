package cc.kasumi.commons.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackUtil {

    public static boolean isNullOrAir(ItemStack itemStack) {
        return itemStack == null || itemStack.getType() == Material.AIR;
    }
}
