package src.ctt.GameMlemBot.Abstract;

import java.util.ArrayList;
import java.util.List;

import src.ctt.GameMlemBot.Interface.IItem;

public abstract class Monster {
    protected String name;
    protected double health;
    protected double damage;
    protected double dodge;
    protected List<IItem> itemsDrop = new ArrayList<>();

    public Monster(String name, double health, double damage, double dodge) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.dodge = dodge;
    }

    public IItem dropItem() {
        int index = (int) (Math.random() * itemsDrop.size());
        return itemsDrop.get(index);
    }
}
