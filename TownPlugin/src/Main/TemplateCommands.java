package Main;

import Objects.Template;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Gebruiker on 7-2-2015.
 */
public class TemplateCommands {
    public static boolean templateCommand(Player player, String[] args)
    {
        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("list"))
            {
                if (Main.templateList.isEmpty())
                {
                    player.sendMessage(ChatColor.BLUE + "Template: " + ChatColor.WHITE + "none");
                    return true;
                }
                for (String string : Main.templateList.keySet())
                {
                    player.sendMessage(ChatColor.BLUE + "Template: " + ChatColor.WHITE + string);
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("clearall"))
            {
                Main.templateList.clear();
                player.sendMessage(ChatColor.BLUE + "Template: " + ChatColor.WHITE + "none");
                return true;
            }
            templateHelp(player);
            return false;
        }
        if (args.length == 2)
        {
            if (args[0].equalsIgnoreCase("filegenerate"))
            {
                Template temp = Files.loadTemplate(args[1]);
                if (temp == null)
                {
                    player.sendMessage(ChatColor.RED + "Template " + ChatColor.WHITE + args[1] + ChatColor.RED + " doesn't exist");
                    return false;
                }
                player.sendMessage(ChatColor.DARK_GREEN + "Generating started!");
                if (temp.generateTemplate(ChunkGeneration.cornerNW(player), ChunkGeneration.cornerSE(player)))
                {
                    player.sendMessage(ChatColor.DARK_GREEN + "Finished generating!");
                    return true;
                }
                player.sendMessage(ChatColor.RED + "Error, couldn't generate the template");
            }
            if (args[0].equalsIgnoreCase("generate"))
            {
                if (!Main.templateList.containsKey(args[1]))
                {
                    player.sendMessage(ChatColor.RED + "Template " + ChatColor.WHITE + args[1] + ChatColor.RED + " doesn't exist");
                    return false;
                }
                player.sendMessage(ChatColor.DARK_GREEN + "Generating started!");
                if (Main.templateList.get(args[1]).generateTemplate(ChunkGeneration.cornerNW(player), ChunkGeneration.cornerSE(player)))
                {
                    player.sendMessage(ChatColor.DARK_GREEN + "Finished generating!");
                    return true;
                }
                player.sendMessage(ChatColor.RED + "Error, couldn't generate the template");
            }
            if (args[0].equalsIgnoreCase("save"))
            {
                if(!Main.templateList.containsKey(args[1]))
                {
                    player.sendMessage(ChatColor.RED + "Template " + ChatColor.WHITE + args[1] + ChatColor.RED + " doesn't exist");
                    return false;
                }
                if (Files.saveTemplate(Main.templateList.get(args[1])))
                {
                    player.sendMessage(ChatColor.DARK_GREEN + "Template " + ChatColor.WHITE + args[1] + ChatColor.DARK_GREEN + " has been saved");
                    return false;
                }
                player.sendMessage(ChatColor.RED + "Template wasn't saved");
                return false;
            }
            if (args[0].equalsIgnoreCase("load"))
            {
                Template template = Files.loadTemplate(args[1]);
                if (template == null)
                {
                    player.sendMessage(ChatColor.RED + "Template " + ChatColor.WHITE + args[1] + ChatColor.RED + " doesn't exist");
                    return false;
                }
                player.sendMessage(ChatColor.DARK_GREEN + "Template " + ChatColor.WHITE + args[1] + ChatColor.DARK_GREEN + " has been loaded");
                Main.templateList.put(args[1], template);
                return true;
            }
            if (args[0].equalsIgnoreCase("clear"))
            {
                if (!Main.templateList.containsKey(args[1]))
                {
                    player.sendMessage(ChatColor.RED + "Template " + ChatColor.WHITE + args[1] + ChatColor.RED + " doesn't exist");
                    return false;
                }
                player.sendMessage(ChatColor.DARK_GREEN + "Template " + ChatColor.WHITE + args[1] + ChatColor.DARK_GREEN +" has been cleared");
                Main.templateList.remove(args[1]);
                return true;
            }
            if (args[0].equalsIgnoreCase("delete"))
            {
                if (Files.deleteTemplate(args[1]))
                {
                    player.sendMessage(ChatColor.DARK_GREEN + "Template " + ChatColor.WHITE + args[1] + ChatColor.DARK_GREEN + " has been deleted");
                    return true;
                }
                player.sendMessage(ChatColor.RED + "This template doesn't exist");
                return false;
            }
            templateHelp(player);
            return false;
        }
        if (args.length == 3)
        {
            if (args[0].equalsIgnoreCase("rename"))
            {
                if (!Main.templateList.containsKey(args[1]))
                {
                    player.sendMessage(ChatColor.RED + "Template " + ChatColor.WHITE + args[1] + ChatColor.RED + " doesn't exist");
                    return false;
                }
                Main.templateList.get(args[1]).setTemplateName(args[2]);
                Main.templateList.put(args[2], Main.templateList.get(args[1]));
                Main.templateList.remove(args[1]);
                player.sendMessage(ChatColor.BLUE + "Template " + ChatColor.WHITE + args[1] + ChatColor.BLUE + " renamed to " + ChatColor.WHITE + args[2]);
                return true;
            }
            templateHelp(player);
            return false;
        }
        if (args.length == 4)
        {
            if(args[0].equalsIgnoreCase("create"))
            {
                int start;
                int end;
                try {
                    start = Integer.parseInt(args[2]);
                    end = Integer.parseInt(args[3]);
                }catch (Exception e)
                {
                    player.sendMessage(ChatColor.RED + "Error " + e);
                    return false;
                }
                if (Main.templateList.containsKey(args[1]))
                {
                    player.sendMessage(ChatColor.RED + "This name already exists!");
                    return false;
                }
                Main.templateList.put(args[1], Template.saveTemplateChunk(player, args[1], start, end));
                return true;
            }
        }
        templateHelp(player);
        return false;
    }
    public static void templateHelp(Player player)
    {
        player.sendMessage(ChatColor.RED + "Wrong arguments:");
        player.sendMessage(ChatColor.YELLOW + "/template create (templateName) (startY) (endY)");
        player.sendMessage(ChatColor.YELLOW + "/template filegenerate (templateName)");
        player.sendMessage(ChatColor.YELLOW + "/template save (templateName)");
        player.sendMessage(ChatColor.YELLOW + "/template load (templateName)");
        player.sendMessage(ChatColor.YELLOW + "/template clear (templateName)");
        player.sendMessage(ChatColor.YELLOW + "/template delete (templateName)");
        player.sendMessage(ChatColor.YELLOW + "/template generate (templateName)");
        player.sendMessage(ChatColor.YELLOW + "/template rename (templateName) (newname)");
        player.sendMessage(ChatColor.YELLOW + "/template clearall");
        player.sendMessage(ChatColor.YELLOW + "/template list");
    }
}
