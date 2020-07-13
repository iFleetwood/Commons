package cc.kasumi.commons.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class PlayerInv {

    private ItemStack[] contents, armorContents;

    public ItemStack getHelmet() {
        return this.armorContents[3];
    }

    public ItemStack getChestPlate() {
        return this.armorContents[2];
    }

    public ItemStack getLeggings() {
        return this.armorContents[1];
    }

    public ItemStack getBoots() {
        return this.armorContents[0];
    }

    public ItemStack getSword() {
        return this.contents[0];
    }
}
