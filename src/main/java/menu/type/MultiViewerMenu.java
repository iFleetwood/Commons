package menu.type;

import cc.kasumi.commons.util.PlayerUtil;
import lombok.Getter;
import menu.InventorySlot;
import menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Getter
public class MultiViewerMenu extends Menu {

    private final Set<UUID> viewers = new HashSet<>();

    public MultiViewerMenu(UUID uuid, String title, int size) {
        super(uuid, title, size);
    }

    @Override
    public Inventory open(Player player) {
        viewers.add(player.getUniqueId());

        return super.open(player);
    }

    public void update(Map<Integer, InventorySlot> newInventorySlots) {
        for (UUID uuid : viewers) {
            Player player = Bukkit.getPlayer(uuid);

            if (!PlayerUtil.isPlayerOnline(player)) {
                return;
            }

            updateInventoryView(player.getOpenInventory(), newInventorySlots);
            player.updateInventory();
        }

        setInventorySlots(newInventorySlots);
    }

    private void updateInventoryView(InventoryView inventoryView, Map<Integer, InventorySlot> newInventorySlots) {
        for (InventorySlot oldInventorySlot : getInventorySlots().values()) {
            int slot = oldInventorySlot.getSlot();

            if (!newInventorySlots.containsKey(slot)) {
                inventoryView.setItem(slot, null);
                Bukkit.broadcastMessage("executed 2");
            }
        }

        for (InventorySlot newInventorySlot : newInventorySlots.values()) {
            inventoryView.setItem(newInventorySlot.getSlot(), newInventorySlot.getItemStack());
        }
    }
}
