package cc.kasumi.commons.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeUtil {

    public void sendPlayerToServer(JavaPlugin plugin, Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);

        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    public void kickPlayer(JavaPlugin plugin, String playerName, String reason) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("KickPlayer");
        out.writeUTF(playerName);
        out.writeUTF(reason);

        getOnlinePlayer().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    public Player getOnlinePlayer() throws IllegalArgumentException {
        if (Bukkit.getOnlinePlayers().size() < 1) {
            throw new IllegalArgumentException("There has to be atleast one player online!");
        }

        return Bukkit.getOnlinePlayers().iterator().next();
    }
}
