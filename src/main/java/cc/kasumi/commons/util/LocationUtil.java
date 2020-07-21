package cc.kasumi.commons.util;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.concurrent.ThreadLocalRandom;

public class LocationUtil {

    public static Location getRandomLocation(Location locationA, Location locationB) {
        double maxX = Math.max(locationA.getX(), locationB.getX());
        double maxZ = Math.max(locationA.getZ(), locationB.getZ());
        double minX = Math.min(locationA.getX(), locationB.getX());
        double minZ = Math.min(locationA.getZ(), locationB.getZ());

        double randomX = getRandomDouble(maxX, minX);
        double randomZ = getRandomDouble(maxZ, minZ);

        World world = locationA.getWorld();
        int highestY = world.getHighestBlockYAt((int) Math.round(randomX), (int) Math.round(randomZ));

        return new Location(locationA.getWorld(), randomX, highestY, randomZ);
    }

    private static double getRandomDouble(double max, double min) {
        return ThreadLocalRandom.current().nextDouble(Math.abs(max - min)) + min;
    }
}
