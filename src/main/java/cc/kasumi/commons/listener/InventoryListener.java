package cc.kasumi.commons.listener;

import cc.kasumi.commons.menu.ConsumerMenu;
import cc.kasumi.commons.menu.InventoryButton;
import cc.kasumi.commons.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

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
                }
            }
        }
    }
}
