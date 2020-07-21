package cc.kasumi.commons.menu;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Menu implements InventoryHolder {

    @Setter
    private List<InventoryButton> buttons;

    private int size;
    private String title;

    public Menu(int size, String title) {
        this.size = size;
        this.title = title;
        this.buttons = new ArrayList<>();
    }

    public Menu(int size, String title, List<InventoryButton> buttons) {
        this(size, title);
        this.buttons = buttons;
    }

    public Inventory open(Player player) {
        Inventory inventory = getInventory();

        player.openInventory(inventory);
        player.updateInventory();

        return inventory;
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, size, title);

        for (InventoryButton button : buttons) {
            inventory.setItem(button.getIndex(), button.getItemStack());
        }

        return inventory;
    }
}
