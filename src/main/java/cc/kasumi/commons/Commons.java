package cc.kasumi.commons;

import cc.kasumi.commons.inventory.MenuManager;
import cc.kasumi.commons.listener.PlayerListeners;
import cc.kasumi.commons.util.EntityHider;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import lombok.Getter;

import menu.MenuHandler;
import menu.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Commons extends JavaPlugin {

    @Getter
    private static Commons instance;

    private EntityHider entityHider;
    private MenuManager menuManager;
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        instance = this;

        menuManager = new MenuManager(this);
        protocolManager = ProtocolLibrary.getProtocolManager();
        entityHider = new EntityHider(this, EntityHider.Policy.BLACKLIST);

        new PlayerListeners();
        new MenuListener();
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
