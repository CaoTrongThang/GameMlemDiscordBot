package src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData;

import src.ctt.GameMlemBot.Interface.IItem;

public class UserEquiptments {
    private IItem weapon;
    private IItem helmet;
    private IItem armor;
    private IItem leggings;
    private IItem boots;

    public IItem getWeapon() {
        return this.weapon;
    }

    public void setWeapon(IItem weapon) {
        this.weapon = weapon;
    }

    public IItem getHelmet() {
        return this.helmet;
    }

    public void setHelmet(IItem helmet) {
        this.helmet = helmet;
    }

    public IItem getArmor() {
        return this.armor;
    }

    public void setArmor(IItem armor) {
        this.armor = armor;
    }

    public IItem getLeggings() {
        return this.leggings;
    }

    public void setLeggings(IItem leggings) {
        this.leggings = leggings;
    }

    public IItem getBoots() {
        return this.boots;
    }

    public void setBoots(IItem boots) {
        this.boots = boots;
    }

}
