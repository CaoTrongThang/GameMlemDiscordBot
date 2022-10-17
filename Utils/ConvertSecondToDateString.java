package src.ctt.GameMlemBot.Utils;

public class ConvertSecondToDateString {
    public String convert(long second) {

        float min = (float) second / 60;
        float hour = min / 60;
        float day = hour / 24;

        String totalDayAndHour = "";
        if (day < 1) {
            totalDayAndHour = "0 Ngày " + Integer.toString((int) (day * 60)) + " Giờ";
        } else if (day > 1) {
            int decimal = (int) day;
            float factional = day - decimal;
            totalDayAndHour = Integer.toString(decimal) + " Ngày " + Integer.toString((int) (factional * 60)) + " Giờ";
        } else {
            totalDayAndHour = " 1 Ngày 0 Giờ";
        }

        return totalDayAndHour;

    }
}
