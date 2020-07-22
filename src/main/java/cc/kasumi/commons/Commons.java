package cc.kasumi.commons;

import cc.kasumi.commons.listener.InventoryListener;
import cc.kasumi.commons.listener.PlayerListeners;
import cc.kasumi.commons.util.EntityHider;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import lombok.Getter;

import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Commons extends JavaPlugin {

    /* TODO:
    Clean up util package
    Finish menus
     */

    @Getter
    private static Commons instance;

    private EntityHider entityHider;
    // private MenuManager menuManager;
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        instance = this;

        // menuManager = new MenuManager(this);
        protocolManager = ProtocolLibrary.getProtocolManager();
        entityHider = new EntityHider(this, EntityHider.Policy.BLACKLIST);

        new PlayerListeners();
        new InventoryListener();
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
