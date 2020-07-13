package cc.kasumi.commons.inventory.scroller.button;

import cc.kasumi.commons.inventory.Button;
import cc.kasumi.commons.inventory.scroller.ScrollerMenu;

import lombok.Getter;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NextPageButton implements Button {

    @Getter
    private ItemStack itemStack;

    public NextPageButton() {
        this.itemStack = ScrollerMenu.NEXT_PAGE;
    }

    @Override
    public int getSlot() {
        return 8;
    }

    @Override
    public void onClicked(Player player) {
        ScrollerMenu menu = ScrollerMenu.SCROLLER_MENUS.get(player.getUniqueId());

        if (!menu.hasNextPage()) {
            return;
        }

        menu.setPageSwitch(true);
        menu.setCurrentPage(menu.getCurrentPage() + 1);

        player.closeInventory();
        player.openInventory(menu.getInventory(player));

        menu.setPageSwitch(false);
    }
}
