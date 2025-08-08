package cc.kasumi.commons.util;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class EntityUtil {

    public static void killAllMobs(World world) {
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Player) {
                continue;
            }

            entity.remove();
        }
    }
}
