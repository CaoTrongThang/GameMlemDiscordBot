package src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGameDataManager.OverOrLower;

import src.ctt.GameMlemBot.Language.GameMlemEmbeds.GameMlemEmbed;

public class DicesData {
    private int DICE_ONE;
    private int DICE_TWO;
    private int DICE_THREE;

    private String DICE_ONE_SYMBOL = "";
    private String DICE_TWO_SYMBOL = "";
    private String DICE_THREE_SYMBOL = "";

    public int getTotalDices;

    public DicesData(int diceOne, int diceTwo, int diceThree) {
        this.DICE_ONE = diceOne;
        this.DICE_TWO = diceTwo;
        this.DICE_THREE = diceThree;

        getTotalDices = DICE_ONE + DICE_TWO + DICE_THREE;

        switch (diceOne) {
            case 1:
                DICE_ONE_SYMBOL = GameMlemEmbed.DICE1;
                break;
            case 2:
                DICE_ONE_SYMBOL = GameMlemEmbed.DICE2;
                break;
            case 3:
                DICE_ONE_SYMBOL = GameMlemEmbed.DICE3;
                break;
            case 4:
                DICE_ONE_SYMBOL = GameMlemEmbed.DICE4;
                break;
            case 5:
                DICE_ONE_SYMBOL = GameMlemEmbed.DICE5;
                break;
            case 6:
                DICE_ONE_SYMBOL = GameMlemEmbed.DICE6;
                break;
        }

        switch (diceTwo) {
            case 1:
                DICE_TWO_SYMBOL = GameMlemEmbed.DICE1;
                break;
            case 2:
                DICE_TWO_SYMBOL = GameMlemEmbed.DICE2;
                break;
            case 3:
                DICE_TWO_SYMBOL = GameMlemEmbed.DICE3;
                break;
            case 4:
                DICE_TWO_SYMBOL = GameMlemEmbed.DICE4;
                break;
            case 5:
                DICE_TWO_SYMBOL = GameMlemEmbed.DICE5;
                break;
            case 6:
                DICE_TWO_SYMBOL = GameMlemEmbed.DICE6;
                break;
        }

        switch (diceThree) {
            case 1:
                DICE_THREE_SYMBOL = GameMlemEmbed.DICE1;
                break;
            case 2:
                DICE_THREE_SYMBOL = GameMlemEmbed.DICE2;
                break;
            case 3:
                DICE_THREE_SYMBOL = GameMlemEmbed.DICE3;
                break;
            case 4:
                DICE_THREE_SYMBOL = GameMlemEmbed.DICE4;
                break;
            case 5:
                DICE_THREE_SYMBOL = GameMlemEmbed.DICE5;
                break;
            case 6:
                DICE_THREE_SYMBOL = GameMlemEmbed.DICE6;
                break;
        }
    }

    public String getDICE_ONE_SYMBOL() {
        return this.DICE_ONE_SYMBOL;
    }

    public void setDICE_ONE_SYMBOL(String DICE_ONE_SYMBOL) {
        this.DICE_ONE_SYMBOL = DICE_ONE_SYMBOL;
    }

    public String getDICE_TWO_SYMBOL() {
        return this.DICE_TWO_SYMBOL;
    }

    public void setDICE_TWO_SYMBOL(String DICE_TWO_SYMBOL) {
        this.DICE_TWO_SYMBOL = DICE_TWO_SYMBOL;
    }

    public String getDICE_THREE_SYMBOL() {
        return this.DICE_THREE_SYMBOL;
    }

    public void setDICE_THREE_SYMBOL(String DICE_THREE_SYMBOL) {
        this.DICE_THREE_SYMBOL = DICE_THREE_SYMBOL;
    }

    public int getDICE_ONE() {
        return this.DICE_ONE;
    }

    public void setDICE_ONE(int DICE_ONE) {
        this.DICE_ONE = DICE_ONE;
    }

    public int getDICE_TWO() {
        return this.DICE_TWO;
    }

    public void setDICE_TWO(int DICE_TWO) {
        this.DICE_TWO = DICE_TWO;
    }

    public int getDICE_THREE() {
        return this.DICE_THREE;
    }

    public void setDICE_THREE(int DICE_THREE) {
        this.DICE_THREE = DICE_THREE;
    }

}
