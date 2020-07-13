package cc.kasumi.commons.cuboid;

import cc.kasumi.commons.util.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
public class CuboidSelection {

    public static final ItemStack SELECTION_AXE = new ItemBuilder(Material.IRON_AXE, ChatColor.GOLD + "Selection Axe").build();

    private int x, y, z;
    private String world;

    public CuboidSelection(Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }
}
