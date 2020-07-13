package cc.kasumi.commons.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Button {

    ItemStack getItemStack();

    int getSlot();

    void onClicked(Player player);

}
