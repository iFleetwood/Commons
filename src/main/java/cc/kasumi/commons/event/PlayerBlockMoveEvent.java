package cc.kasumi.commons.event;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;

@Getter
public class PlayerBlockMoveEvent extends Event implements Cancellable {

    private final static HandlerList handlers = new HandlerList();

    private final Player player;
    private final Block fromBlock, toBlock;

    @Setter
    private boolean cancelled = false;

    public PlayerBlockMoveEvent(PlayerMoveEvent event, Block fromBlock, Block toBlock) {
        this.player = event.getPlayer();
        this.fromBlock = fromBlock;
        this.toBlock = toBlock;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
