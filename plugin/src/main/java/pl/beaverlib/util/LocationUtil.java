package pl.beaverlib.util;

import org.bukkit.Location;
import org.bukkit.World;
import pl.beaverlib.BeaverLib;

import javax.annotation.Nullable;

public abstract class LocationUtil {

    private final static BeaverLib plugin = BeaverLib.getInstance();

    @Nullable
    public static Location parseLocation(String locationString) {
        String[] split = locationString.split(" ");
        if(split.length != 4) {
            plugin.getLogger().severe("Location " + locationString + " is wrong!");
            return null;
        }
        int x, y, z;
        try {
            x = Integer.parseInt(split[0]);
            y = Integer.parseInt(split[1]);
            z = Integer.parseInt(split[2]);
        } catch(NumberFormatException e) {
            plugin.getLogger().severe("Location " + locationString + " is wrong!");
            return null;
        }
        World w = plugin.getServer().getWorld(split[3]);
        if(w == null) {
            plugin.getLogger().severe("Location " + locationString + " is wrong!");
            return null;
        }
        return new Location(w, x, y, z);
    }

    public static String locationBuilder(Location location) {
        return "§b"
                + (int) location.getX() + "§5, §b"
                + (int) location.getY() + "§5, §b"
                + (int) location.getZ()
                + ((location.getWorld() != null) ? " §5(§b" + location.getWorld().getName() + "§5)" : "");
    }

}
