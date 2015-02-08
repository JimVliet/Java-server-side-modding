package Objects;

/**
 * Created by Gebruiker on 8-2-2015.
 */
public enum TownType {
    CITY, TOWN, OUTPOST;
    public static TownType getType(String string)
    {
        for (TownType type : TownType.values())
        {
            if(type.toString().equalsIgnoreCase(string))
            {
                return type;
            }
        }
        return null;
    }
}
