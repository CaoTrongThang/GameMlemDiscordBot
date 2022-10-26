package src.ctt.GameMlemBot.Interface;

import src.ctt.GameMlemBot.Enums.ItemTypes;

public interface IItem {
    public ItemTypes getType();

    public String getThumbnail();

    public String getIcon();

    public double getDamage();

    public double getHealth();

    public double getDodgeRate();

    public double getDefense();
}
