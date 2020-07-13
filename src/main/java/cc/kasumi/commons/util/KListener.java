package cc.kasumi.commons.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class KListener implements Listener {

    public KListener(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
