package cc.kasumi.commons.menu.type;

import cc.kasumi.commons.menu.IMenu;
import cc.kasumi.commons.util.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
import cc.kasumi.commons.menu.InventorySlot;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

@Getter
public class ScrollerMenu implements IMenu {

    private final List<ScrollerPage> pages = new ArrayList<>();

    private final InventorySlot backwardsButton;
    private final InventorySlot forwardButton;
    private final UUID uuid;
    private final String title;
    private final int size;
    private int currentPage = 0;

    @Setter
    private boolean switchingPage = false;

    public ScrollerMenu(UUID uuid, String title, int size) {
        this.uuid = uuid;
        this.title = title;
        this.size = size;

        backwardsButton = new InventorySlot(0,
                new ItemBuilder(Material.CARPET, ChatColor.GREEN + "Previous page", (byte) 14).build(), player ->
        {
            if (hasPreviousPage()) {
                switchPage(PageSwitch.BACKWARDS, player);
            }
        });

        forwardButton = new InventorySlot(8,
                new ItemBuilder(Material.CARPET, ChatColor.GREEN + "Next page", (byte) 5).build(), player ->
        {
            if (hasNextPage()) {
                switchPage(PageSwitch.FORWARD, player);
            }
        });
    }

    public ScrollerMenu(UUID uuid, String title, int size, Map<Integer, InventorySlot> inventorySlots) {
        this(uuid, title, size);

        setInventorySlots(inventorySlots);
    }

    public void setInventorySlots(Map<Integer, InventorySlot> inventorySlots) {
        pages.add(new ScrollerPage(uuid, title, size));

        int pageCounter = 0;
        int i = 9;
        for (InventorySlot inventorySlot : inventorySlots.values()) {
            ScrollerPage page = pages.get(pageCounter);
            Map<Integer, InventorySlot> pageInventorySlots = page.getInventorySlots();
            inventorySlot.setSlot(i);

            pageInventorySlots.put(i++, inventorySlot);
            if (page.isFull()) {
                pageCounter++;
                pages.add(new ScrollerPage(uuid, title, size));

                i = 9;
            }
        }

        for (int p = 0; p < pages.size(); p++) {
            Map<Integer, InventorySlot> pageInventorySlots = pages.get(p).getInventorySlots();

            if (p > 0) {
                pageInventorySlots.put(backwardsButton.getSlot(), backwardsButton);
            }

            if (p < pages.size() - 1) {
                pageInventorySlots.put(forwardButton.getSlot(), forwardButton);
            }
        }
    }

    public boolean hasPreviousPage() {
        return currentPage > 0;
    }

    public boolean hasNextPage() {
        return currentPage < pages.size() - 1;
    }

    public ScrollerPage getCurrentPage() {
        return pages.get(currentPage);
    }

    public void switchPage(PageSwitch pageSwitch, Player player) {
        switchingPage = true;

        if (pageSwitch == PageSwitch.BACKWARDS) {
            currentPage--;
        } else if (pageSwitch == PageSwitch.FORWARD) {
            currentPage++;
        }

        getCurrentPage().open(player);

        switchingPage = false;
    }

    @Override
    public Map<Integer, InventorySlot> getInventorySlots() {
        return pages.get(currentPage).getInventorySlots();
    }

    @Override
    public Inventory open(Player player) {
        return pages.get(currentPage).open(player);
    }

    public enum PageSwitch {
        FORWARD,
        BACKWARDS
    }
}
