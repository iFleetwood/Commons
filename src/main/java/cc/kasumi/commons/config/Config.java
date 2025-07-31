package cc.kasumi.commons.config;

import org.bukkit.World;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface Config {

    boolean exists();

    Set<String> getKeys();

    Set<String> getAllKeys();

    String getString(String path);

    int getInt(String path);

    long getLong(String path);

    double getDouble(String path);

    boolean getBoolean(String path);

    List<String> getStringList(String path);

    UUID getUuid(String path);

    World getWorld(String path);

    void set(String path, Object value);
}
