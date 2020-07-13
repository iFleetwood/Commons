package cc.kasumi.commons.inventory.scroller.button;

import cc.kasumi.commons.inventory.Button;
import cc.kasumi.commons.inventory.scroller.ScrollerMenu;

import lombok.Getter;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PreviousPageButton implements Button {

    @Getter
    private ItemStack itemStack;

    public PreviousPageButton() {
        this.itemStack = ScrollerMenu.PREVIOUS_PAGE;
    }

    @Override
    public int getSlot() {
        return 0;
    }

    @Override
    public void onClicked(Player player) {
        ScrollerMenu menu = ScrollerMenu.SCROLLER_MENUS.get(player.getUniqueId());

        if (!menu.hasPreviousPage()) {
            return;
        }

        menu.setPageSwitch(true);
        menu.setCurrentPage(menu.getCurrentPage() - 1);

        player.closeInventory();
        player.openInventory(menu.getInventory(player));

        menu.setPageSwitch(false);
    }
}
