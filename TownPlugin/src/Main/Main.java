package Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import Objects.Town;

import java.io.File;
import java.util.*;
import Objects.Template;

public class Main extends JavaPlugin{
    public static Map<UUID, Town> townList = new HashMap<>();
    public static Main plugin;
    public static Map<String, Template> templateList = new HashMap<>();
    public static File folder;

    @Override
    public void onEnable()
    {
        plugin = this;
        System.out.println("Plugin is working");
        getLogger().info("OnEnable is working");

        folder = new File(plugin.getDataFolder() + "/");
        if (!folder.exists())
        {
            if (!folder.mkdir())
            {
                System.out.println("Couldn't create directory!");
                Bukkit.getServer().shutdown();
            }
        }
    }
    @Override
    public void onDisable()
    {
        getLogger().info("OnDisable is working");
        System.out.println("Plugin is being disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            System.out.println("Error, you need to be a player!");
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("createtown"))
        {
            if(args.length == 0)
            {
                sender.sendMessage(ChatColor.RED + "Too few arguments:");
                sender.sendMessage(ChatColor.YELLOW + "/createtown (townname)");
                return false;
            }

            if (args.length > 1)
            {
                sender.sendMessage(ChatColor.RED + "Too many arguments:");
                sender.sendMessage(ChatColor.YELLOW + "/createtown (townname)");
                return false;
            }

            return CommandMethods.createNewTown((Player)sender, args);

        }
        else if (cmd.getName().equalsIgnoreCase("mytown"))
        {

            if (args.length > 0)
            {
                sender.sendMessage(ChatColor.RED + "Too many arguments:");
                sender.sendMessage(ChatColor.YELLOW + "/mytown");
                return false;
            }

            return CommandMethods.showMyTown((Player)sender);
        }
        else if (cmd.getName().equalsIgnoreCase("template"))
        {
            if (args.length == 0)
            {
                TemplateCommands.templateHelp((Player)sender);
                return false;
            }
            return TemplateCommands.templateCommand((Player) sender, args);
        }
        return false;
    }

}
