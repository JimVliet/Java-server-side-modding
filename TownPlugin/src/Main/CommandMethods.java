package Main;

import Objects.Building;
import Objects.Town;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.util.regex.Pattern;

/**
 * Created by Gebruiker on 2-2-2015.
 */
public class CommandMethods {

    public static boolean createNewTown(Player sender, String[] args)
    {
        String townName = args[0];

        if (townName.length() > 16)
        {
            sender.sendMessage( ChatColor.RED + "You can't have a name longer than 16 characters");
            return false;
        }

        if(Pattern.compile("[^a-zA-Z]").matcher(townName).find())
        {
            sender.sendMessage( ChatColor.RED + "The name needs to be alphanumeric");
            return false;
        }

        if (Town.getTown(sender) == null)
        {
            sender.sendMessage(ChatColor.RED + "You already have a town!");
            return false;
        }

        Town town = new Town(sender, townName);
        Main.townList.put((sender).getUniqueId(), town);

        sender.sendMessage(ChatColor.YELLOW + "Town created successfully");
        sendInfo(sender);

        return true;
    }

    public static boolean showMyTown(Player sender)
    {
        if (Town.getTown(sender) == null)
        {
            sender.sendMessage(ChatColor.RED + "You don't have a town!");
            return false;
        }
        sendInfo(sender);

        return true;
    }
    public static void sendInfo(Player sender)
    {
        sender.sendMessage(ChatColor.BLUE + "TownName: " + ChatColor.WHITE + Town.getTown(sender).townName);
        sender.sendMessage(ChatColor.BLUE + "TownLevel: " + ChatColor.WHITE + Town.getTown(sender).townLevel);
        sender.sendMessage(ChatColor.BLUE + "OwnerID: " + ChatColor.WHITE + Town.getTown(sender).ownerId.toString());
        sender.sendMessage(ChatColor.BLUE + "TownID: " + ChatColor.WHITE + Town.getTown(sender).townID);

        if (Town.getTown(sender).buildingList.isEmpty())
        {
            sender.sendMessage(ChatColor.BLUE + "Buildings: " + ChatColor.WHITE + "None");
            return;
        }
        for (Building building : Town.getTown(sender).buildingList)
        {
            sender.sendMessage(ChatColor.BLUE + "Building: " + ChatColor.WHITE + building.buildingType);
            sender.sendMessage(ChatColor.BLUE + " Level: " + ChatColor.WHITE + building.buildingLevel);
            sender.sendMessage(ChatColor.BLUE + " Number: " + ChatColor.WHITE + Town.getTown(sender).buildingList.indexOf(building));
        }
    }
}