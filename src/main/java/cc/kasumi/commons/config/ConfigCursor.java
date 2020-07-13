package cc.kasumi.commons.config;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class ConfigCursor {

    private final FileConfig fileConfig;

    @Setter private String path;

    public ConfigCursor(FileConfig fileConfig) {
        this(fileConfig, "");
    }

    public boolean exists() {
        return this.fileConfig.getConfig().contains(this.path);
    }

    public Set<String> getKeys() {
        return this.fileConfig.getConfig().getConfigurationSection(this.path).getKeys(false);
    }

    public Set<String> getAllKeys() {
        return this.fileConfig.getConfig().getConfigurationSection(this.path).getKeys(true);
    }

    public String getString(String path) {
        return this.fileConfig.getConfig().getString(this.path + "." + path);
    }

    public int getInt(String path) {
        return this.fileConfig.getConfig().getInt(this.path + "." + path);
    }

    public long getLong(String path) {
        return this.fileConfig.getConfig().getLong(this.path + "." + path);
    }

    public double getDouble(String path) {
        return this.fileConfig.getConfig().getDouble(this.path + "." + path);
    }

    public boolean getBoolean(String path) {
        return this.fileConfig.getConfig().getBoolean(this.path + "." + path);
    }

    public List<String> getStringList(String path) {
        return this.fileConfig.getConfig().getStringList(this.path + "." + path);
    }

    public UUID getUuid(String path) {
        return UUID.fromString(this.fileConfig.getConfig().getString(this.path + "." + path));
    }

    public World getWorld(String path) {
        return Bukkit.getWorld(this.fileConfig.getConfig().getString(this.path + ".", path));
    }

    public void set(String path, Object value) {
        this.fileConfig.getConfig().set(this.path + "." + path, value);
    }

    public void save() {
        this.fileConfig.save();
    }
}
