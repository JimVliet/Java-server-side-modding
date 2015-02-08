package Objects;

import org.bukkit.Material;

import java.io.Serializable;

/**
 * Created by Gebruiker on 5-2-2015.
 */
public class AllBlockData implements Serializable{
    private Material mat;
    private Byte data;

    public AllBlockData(Material material, Byte data)
    {
        this.mat = material;
        this.data = data;
    }
    public Material getMaterial()
    {
        return this.mat;
    }
    public Byte getData()
    {
        return this.data;
    }

    private static final long serialVersionUID = 1;
}
