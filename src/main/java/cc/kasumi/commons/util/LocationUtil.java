package cc.kasumi.commons.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.concurrent.ThreadLocalRandom;

public class LocationUtil {

    public static Location[] getFaces(Location start) {
        Location[] faces = new Location[4];
        faces[0] = new Location(start.getWorld(), start.getX() + 1, start.getY(), start.getZ());
        faces[1] = new Location(start.getWorld(), start.getX() - 1, start.getY(), start.getZ());
        faces[2] = new Location(start.getWorld(), start.getX(), start.getY() + 1, start.getZ());
        faces[3] = new Location(start.getWorld(), start.getX(), start.getY() - 1, start.getZ());
        return faces;
    }

    public static String serialize(Location location) {
        if (location == null) {
            return "null";
        }

        return location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ() +
                ";" + location.getYaw() + ";" + location.getPitch();
    }

    public static Location deserialize(String source) {
        if (source == null) {
            return null;
        }

        String[] split = source.split(";");
        World world = Bukkit.getServer().getWorld(split[0]);

        if (world == null) {
            return null;
        }

        return new Location(world, Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
    }

    public static Location getRandomLocation(Location locationA, Location locationB) {
        return getRandomLocation(locationA, locationB, locationA.getWorld());
    }

    public static Location getRandomLocation(Location locationA, Location locationB, World world) {
        return getRandomLocation(locationA.getX(), locationA.getZ(), locationB.getX(), locationB.getZ(), world);
    }


    public static Location getRandomLocation(double x1, double z1, double x2, double z2, World world) {
        double maxX = Math.max(x1, x2);
        double maxZ = Math.max(z1, z2);
        double minX = Math.min(x1, x2);
        double minZ = Math.min(z1, z2);

        double randomX = NumberUtil.getRandomDouble(maxX, minX);
        double randomZ = NumberUtil.getRandomDouble(maxZ, minZ);

        int highestY = world.getHighestBlockYAt((int) Math.round(randomX), (int) Math.round(randomZ));

        return new Location(world, randomX, highestY, randomZ);
    }
}
