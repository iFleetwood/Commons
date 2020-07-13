package cc.kasumi.commons.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public interface Menu {

    Map<Integer, Button> getButtons();

    Inventory getInventory(Player player);

    String getTitle();

    void onClose(Player player);
}
