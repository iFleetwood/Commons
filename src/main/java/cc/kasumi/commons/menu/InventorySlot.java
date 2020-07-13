package cc.kasumi.commons.menu;

import cc.kasumi.commons.util.ClickHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
@AllArgsConstructor
public class InventorySlot {

    private int slot;
    private ItemStack itemStack;
    private ClickHandler clickHandler;
}
