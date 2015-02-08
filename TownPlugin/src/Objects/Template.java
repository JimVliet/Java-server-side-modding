package Objects;

import Main.ChunkGeneration;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Template implements Serializable{
    private List<List<AllBlockData>> template = new ArrayList<>();
    private String templateName;
    private int startHeight;

    private Template(List<List<AllBlockData>> dataList, String name, int start)
    {
        this.template = dataList;
        this.templateName = name;
        this.startHeight = start;
    }
    public String getTemplateName()
    {
        return this.templateName;
    }
    public void setTemplateName(String string)
    {
        this.templateName = string;
    }

    private static final long serialVersionUID = 1;

    public static Template saveTemplateChunk(Player player, String name, int start, int end)
    {
        List<List<AllBlockData>> newTemplate = new ArrayList<>();
        Location first = ChunkGeneration.cornerNW(player);
        Location second = ChunkGeneration.cornerSE(player);
        for(int y = start; y <= end; y++)
        {
            List<AllBlockData> list = new ArrayList<>();
            for(int z = first.getBlockZ(); z <= second.getBlockZ(); z++)
            {
                for(int x = first.getBlockX(); x <= second.getBlockX(); x++)
                {
                    Block current = first.getWorld().getBlockAt(x, y, z);
                    list.add(new AllBlockData(current.getType(), current.getData()));
                }
            }
            newTemplate.add(list);
        }
        player.sendMessage(ChatColor.DARK_GREEN + "Template " + name + " has been temporarily created");
        return new Template(newTemplate, name, start);
    }
    public boolean generateTemplate(final Location first, final Location second)
    {
        final Template newTemplate = this;
        final int start = this.startHeight;
        for (final List<AllBlockData> layer : newTemplate.template)
        {
            Bukkit.getScheduler().runTaskLaterAsynchronously(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    int y = start + newTemplate.template.indexOf(layer);
                    int blockCount = 0;
                    for(int z = first.getBlockZ(); z <= second.getBlockZ(); z++)
                    {
                        for(int x = first.getBlockX(); x <= second.getBlockX(); x++)
                        {
                            Block block = first.getWorld().getBlockAt(x, y, z);
                            if (layer.get(blockCount) == null)
                            {
                                block.setType(Material.AIR);
                                blockCount++;
                                continue;
                            }
                            AllBlockData data = layer.get(blockCount);
                            block.setType(data.getMaterial());
                            try {
                                block.setData(data.getData(), false);
                            }catch (Exception e)
                            {
                                System.out.println(e);
                                System.out.println("Error 2");
                            }
                            blockCount++;
                        }
                    }
                }
            }, 1L);
        }
        return true;
    }
}
