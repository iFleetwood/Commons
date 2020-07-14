package cc.kasumi.commons.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public interface IMenu {

    Map<Integer, InventorySlot> getInventorySlots();
    Inventory open(Player player);
}
