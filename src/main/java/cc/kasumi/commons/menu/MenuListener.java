package cc.kasumi.commons.menu;

import cc.kasumi.commons.Commons;
import cc.kasumi.commons.util.ItemStackUtil;
import cc.kasumi.commons.util.KListener;
import cc.kasumi.commons.menu.type.MultiViewerMenu;
import cc.kasumi.commons.menu.type.ScrollerMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Map;
import java.util.UUID;

public class MenuListener extends KListener {

    public MenuListener() {
        super(Commons.getInstance());
    }

    private void click(InventoryClickEvent event, Map<Integer, InventorySlot> inventorySlots) {
        for (InventorySlot inventorySlot : inventorySlots.values()) {
            if (!event.getCurrentItem().isSimilar(inventorySlot.getItemStack())) continue;

            event.setResult(Event.Result.DENY);
            event.setCancelled(true);
            inventorySlot.getClickHandler().click((Player) event.getWhoClicked());
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        UUID playerUUID = player.getUniqueId();

        if (event.getCursor() == null || ItemStackUtil.isNullOrAir(event.getCurrentItem())) {
            return;
        }
        Map<UUID, ScrollerMenu> scrollerMenus = MenuHandler.getScrollerMenus();

        // Making it far more efficient!
        if (scrollerMenus.containsKey(playerUUID)) {
            click(event, scrollerMenus.get(playerUUID).getInventorySlots());

            return;
        }

        for (Menu menu : MenuHandler.getMenus().values()) {
            click(event, menu.getInventorySlots());

            return;
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        Map<UUID, Menu> menus = MenuHandler.getMenus();
        Map<UUID, ScrollerMenu> scrollerMenus = MenuHandler.getScrollerMenus();

        menus.remove(playerUUID);

        // Checking if the page is switching, if it isn't delete user from scroller cc.kasumi.commons.menu
        if (scrollerMenus.containsKey(playerUUID)) {
            if (!scrollerMenus.get(playerUUID).isSwitchingPage()) {
                scrollerMenus.remove(playerUUID);
            }
        }

        // If player is closing down a multi viewer cc.kasumi.commons.menu, remove the player from viewers
        for (Menu menu : menus.values()) {
            if (!(menu instanceof MultiViewerMenu)) continue;
            MultiViewerMenu multiViewerMenu = (MultiViewerMenu) menu;

            multiViewerMenu.getViewers().remove(playerUUID);
            return;
        }
    }
}
