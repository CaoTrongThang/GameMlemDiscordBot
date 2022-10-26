package src.ctt.GameMlemBot.Logic.Model.GameMlemData;

public class GameMlemDealer {
    private String name = "Nhà Cái Hàng Đầu Việt Nam";
    private String description = "Châm ngôn của tôi là làm ăn có tâm không lừa đảo";

    private double totalMoney = 0;
    private double totalMoneyGot = 0;
    private double totalMoneySpent = 0;

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney += totalMoney;
    }

    public double getTotalMoneyGot() {
        return this.totalMoneyGot;
    }

    public void setTotalMoneyGot(double totalMoneyGot) {
        this.totalMoneyGot += totalMoneyGot;
    }

    public double getTotalMoneySpent() {
        return this.totalMoneySpent;
    }

    public void setTotalMoneySpent(double totalMoneySpent) {
        this.totalMoneySpent += totalMoneySpent;
    }

}
