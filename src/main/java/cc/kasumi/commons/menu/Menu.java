package cc.kasumi.commons.menu;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Menu implements IMenu {

    private Map<Integer, InventorySlot> inventorySlots = new HashMap<>();

    private final UUID uuid;
    private String title;
    private int size;

    /*
    When the player opens the menu update all the items!
     */

    public Menu(UUID uuid, String title, int size) {
        this.uuid = uuid;
        this.title = title;
        this.size = size;

        MenuHandler.addMenu(this);
    }

    public Menu(UUID uuid, String title, int size, boolean scrollerMenu) {
        this.uuid = uuid;
        this.title = title;
        this.size = size;

        if (!scrollerMenu) {
            MenuHandler.addMenu(this);
        }
    }

    @Override
    public Inventory open(Player player) {
        Inventory inventory = Bukkit.createInventory(null, getSize(), getTitle());

        for (InventorySlot inventorySlot : getInventorySlots().values()) {
            inventory.setItem(inventorySlot.getSlot(), inventorySlot.getItemStack());
        }

        player.openInventory(inventory);
        player.updateInventory();

        return inventory;
    }
}
