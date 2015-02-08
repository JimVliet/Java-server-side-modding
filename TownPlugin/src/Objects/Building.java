package Objects;

import org.bukkit.Chunk;

/**
 * Created by Gebruiker on 1-2-2015.
 */
public class Building {
    public String townId;
    public BuildingType buildingType;
    public int buildingLevel;
    public int generateLevel;
    public Chunk location;

    public Building(Town town, BuildingType type, Chunk chunk)
    {
        this.townId = town.townID;
        this.buildingType = type;
        this.buildingLevel = 1;
        this.generateLevel = 0;
        this.location = chunk;
    }
}
