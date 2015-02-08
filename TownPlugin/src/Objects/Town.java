package Objects;

import Main.Main;
import org.bukkit.entity.Player;
import java.util.*;

/**
 * Created by Gebruiker on 1-2-2015.
 */

public class Town
{
    public static List<Building> buildingList = new ArrayList<>();
    public UUID ownerId;
    public String townName;
    public int townLevel;
    public final String townID;

    public Town(Player player, String newTownName)
    {
        this.ownerId = player.getUniqueId();
        this.townName = newTownName;
        this.townLevel = 1;
        this.townID = UUID.randomUUID().toString();
    }
    public static Town getTown(Player player)
    {
        if (Main.townList.get(player.getUniqueId()) == null)
        {
            return null;
        }
        return Main.townList.get(player.getUniqueId());
    }
}