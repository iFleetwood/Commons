package cc.kasumi.commons.listener;

import cc.kasumi.commons.Commons;
import cc.kasumi.commons.menu.ConsumerMenu;
import cc.kasumi.commons.menu.InventoryButton;
import cc.kasumi.commons.menu.Menu;
import cc.kasumi.commons.util.KListener;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryListener extends KListener {

    public InventoryListener() {
        super(Commons.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();

        if (item == null || item.getType() == Material.AIR || item.getItemMeta().getDisplayName() == null) {
            return;
        }

        if (inventory.getHolder() instanceof ConsumerMenu) {
            ConsumerMenu consumerMenu = (ConsumerMenu) inventory.getHolder();

            if (consumerMenu != null) {
                consumerMenu.getConsumer().accept(event);
            }

        } else if (inventory.getHolder() instanceof Menu) {
            Menu menu = (Menu) inventory.getHolder();

            if (menu != null) {
                for (InventoryButton button : menu.getButtons()) {
                    if (!button.getItemStack().isSimilar(event.getCurrentItem())) {
                        continue;
                    }

                    button.getHandler().click((Player) event.getWhoClicked());
                    event.setCancelled(true);
                }
            }
        }
    }
}
