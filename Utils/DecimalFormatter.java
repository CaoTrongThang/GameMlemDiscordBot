package src.ctt.GameMlemBot.Utils;

import java.text.DecimalFormat;

import src.ctt.GameMlemBot.Language.DefaultEmbed;

public class DecimalFormatter {
    public String formatVND(double money) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");

        return formatter.format(money);
    }
}
