package cc.kasumi.commons.menu.button;

import cc.kasumi.commons.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EmptyButton extends Button {

    private final ItemStack itemStack;

    public EmptyButton(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return itemStack;
    }
}
