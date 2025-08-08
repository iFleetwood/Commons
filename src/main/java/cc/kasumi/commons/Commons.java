package cc.kasumi.commons;

import cc.kasumi.commons.menu.MenuListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Commons extends JavaPlugin {

    @Getter
    private static Commons instance;

    @Override
    public void onEnable() {
        instance = this;

        registerManagers();
        registerListeners();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void registerManagers() {

    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new MenuListener(), this);
    }
}