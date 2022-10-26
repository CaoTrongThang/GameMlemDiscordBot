package src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemUserData;

public class UserStats {
    private double damage;

    private double health;
    private double defense;
    private float dodgeRate;

    public double getDamage() {
        return this.damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getHealth() {
        return this.health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDefense() {
        return this.defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public float getDodgeRate() {
        return this.dodgeRate;
    }

    public void setDodgeRate(float dodgeRate) {
        this.dodgeRate = dodgeRate;
    }
}
