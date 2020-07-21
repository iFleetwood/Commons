package cc.kasumi.commons.menu;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

import java.util.function.Consumer;

@Getter
public abstract class ConsumerMenu implements InventoryHolder {

    @Setter
    private Consumer<InventoryClickEvent> consumer;
}
