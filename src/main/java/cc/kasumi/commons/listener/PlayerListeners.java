package cc.kasumi.commons.listener;

import cc.kasumi.commons.Commons;
import cc.kasumi.commons.event.PlayerBlockMoveEvent;
import cc.kasumi.commons.event.PlayerDamageByPlayerEvent;
import cc.kasumi.commons.util.KListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class PlayerListeners extends KListener {

    public PlayerListeners() {
        super(Commons.getInstance());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();
        Block fromBlock = from.getBlock();
        Block toBlock = to.getBlock();

        if (fromBlock.equals(toBlock)) {
            return;
        }

        PlayerBlockMoveEvent playerBlockMoveEvent = new PlayerBlockMoveEvent(event, fromBlock, toBlock);
        Bukkit.getPluginManager().callEvent(playerBlockMoveEvent);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        if (!(entity instanceof Player) || (!(damager instanceof Player))) {
            return;
        }

        PlayerDamageByPlayerEvent playerDamageByPlayerEvent = new PlayerDamageByPlayerEvent(event);
        Bukkit.getPluginManager().callEvent(playerDamageByPlayerEvent);
    }
}
