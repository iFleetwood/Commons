package cc.kasumi.commons.inventory;

import cc.kasumi.commons.inventory.scroller.ScrollerMenu;
import cc.kasumi.commons.util.KListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class MenuManager extends KListener {

    @Getter
    private List<Menu> menus = new ArrayList<>();

    public MenuManager(Plugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack itemStack = event.getCurrentItem();

        if (inventory instanceof PlayerInventory || itemStack == null || itemStack.getType() == Material.AIR) {
            return;
        }

        for (Menu menu : this.menus) {
            if (!inventory.getName().equalsIgnoreCase(menu.getTitle())) {
                continue;
            }

            event.setCancelled(true);

            for (Button button : menu.getButtons().values()) {
                if (!itemStack.equals(button.getItemStack())) {
                    continue;
                }

                button.onClicked(player);
                break;
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();

        if (inventory instanceof PlayerInventory) {
            return;
        }

        for (Menu menu : this.menus) {
            if (!inventory.getName().equalsIgnoreCase(menu.getTitle())) {
                continue;
            }

            if (menu instanceof ScrollerMenu) {
                ScrollerMenu scrollerMenu = (ScrollerMenu) menu;

                if (scrollerMenu.isPageSwitch()) {
                    continue;
                }
            }

            menu.onClose(player);
            break;
        }
    }
}
