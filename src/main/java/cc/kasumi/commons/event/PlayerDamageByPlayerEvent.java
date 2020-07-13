package cc.kasumi.commons.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@Getter
public class PlayerDamageByPlayerEvent extends Event implements Cancellable {

    private final static HandlerList handlers = new HandlerList();

    private final Player player, damager;
    private EntityDamageByEntityEvent oldEvent;

    @Setter
    private boolean cancelled = false;

    public PlayerDamageByPlayerEvent(EntityDamageByEntityEvent event) {
        this.player = (Player) event.getEntity();
        this.damager = (Player) event.getDamager();

        this.oldEvent = event;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
