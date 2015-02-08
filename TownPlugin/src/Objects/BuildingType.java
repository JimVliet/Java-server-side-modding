package Objects;

/**
 * Created by Gebruiker on 5-2-2015.
 */
public enum  BuildingType {
    TOWNHALL, EMPTY, BUILDSPOT;
    public static BuildingType getType(String string)
    {
        for (BuildingType type : BuildingType.values())
        {
            if(type.toString().equalsIgnoreCase(string))
            {
                return type;
            }
        }
        return null;
    }
}
