package cc.kasumi.commons.inventory.scroller;

import cc.kasumi.commons.inventory.Button;
import cc.kasumi.commons.inventory.Menu;
import cc.kasumi.commons.inventory.scroller.button.NextPageButton;
import cc.kasumi.commons.inventory.scroller.button.PreviousPageButton;
import cc.kasumi.commons.util.ItemBuilder;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class ScrollerMenu implements Menu {

    public static final Map<UUID, ScrollerMenu> SCROLLER_MENUS = new HashMap<>();
    public static final ItemStack NEXT_PAGE = new ItemBuilder(Material.CARPET, ChatColor.GREEN + "Next Page", (byte) 5).build();
    public static final ItemStack PREVIOUS_PAGE = new ItemBuilder(Material.CARPET, ChatColor.RED + "Previous Page", (byte) 14).build();

    private final Map<Integer, Map<Integer, Button>> pages = new HashMap<>();

    private final String title;

    private final int size;

    @Setter
    private int currentPage;

    @Setter
    private boolean pageSwitch = false;

    public ScrollerMenu(String title, int size) {
        this.title = title;
        this.size = size;
        this.currentPage = 0;

        addPageButtons();
    }

    @Override
    public Map<Integer, Button> getButtons() {
        return getPage();
    }

    @Override
    public Inventory getInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, this.size, this.title.replace("%page%", "" + this.currentPage));

        getPage().forEach((integer, button) -> inventory.setItem(integer, button.getItemStack()));

        return inventory;
    }

    @Override
    public void onClose(Player player) {
        SCROLLER_MENUS.remove(player.getUniqueId());
    }

    public void addButtons(List<Button> buttons) {
        int i = 9;
        for (Button button : buttons) {
            getPage().put(i++, button);

            if (i >= this.size) {
                this.currentPage++;
                addPageButtons();

                i = 9;
            }
        }
    }

    public boolean hasPreviousPage() {
        return this.currentPage != 0;
    }

    public boolean hasNextPage() {
        return pages.size() > this.currentPage;
    }

    private Map<Integer, Button> getPage() {
        return pages.get(this.currentPage);
    }

    private void addPageButtons() {
        Map<Integer, Button> buttons = getPage();

        PreviousPageButton previousPageButton = new PreviousPageButton();
        NextPageButton nextPageButton = new NextPageButton();

        buttons.put(previousPageButton.getSlot(), previousPageButton);
        buttons.put(nextPageButton.getSlot(), nextPageButton);
    }
}
