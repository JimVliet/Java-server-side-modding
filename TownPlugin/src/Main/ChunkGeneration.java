package Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
public class ChunkGeneration{
    public static Location cornerNW(Player player)
    {
        int x = player.getLocation().getChunk().getX()*16;
        int z = player.getLocation().getChunk().getZ()*16;
        return Bukkit.getServer().getWorld("world").getBlockAt(x, 0, z).getLocation();
    }
    public static Location cornerSW(Player player)
    {
        int x = player.getLocation().getChunk().getX()*16;
        int z = player.getLocation().getChunk().getZ()*16;
        return Bukkit.getServer().getWorld("world").getBlockAt(x, 0, z+15).getLocation();
    }
    public static Location cornerSE(Player player)
    {
        int x = player.getLocation().getChunk().getX()*16;
        int z = player.getLocation().getChunk().getZ()*16;
        return Bukkit.getServer().getWorld("world").getBlockAt(x+15, 0, z+15).getLocation();
    }
    public static Location cornerNE(Player player)
    {
        int x = player.getLocation().getChunk().getX()*16;
        int z = player.getLocation().getChunk().getZ()*16;
        return Bukkit.getServer().getWorld("world").getBlockAt(x+15, 0, z).getLocation();
    }
    public static String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 45) {
            return "West";
        } else if (45 <= rotation && rotation < 135) {
            return "North";
        } else if (135 <= rotation && rotation < 225) {
            return "East";
        } else if (225 <= rotation && rotation < 315) {
            return "South";
        } else if (315 <= rotation && rotation < 360) {
            return "West";
        } else {
            return "Error";
        }
    }
}