package src.ctt.GameMlemBot.Utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ConvertTimeToDateString {
    public String convertToDayAndHour(long second) {

        float min = (float) second / 60;
        float hour = min / 60;
        float day = hour / 24;

        String totalDayAndHour = "";
        if (day < 1) {
            totalDayAndHour = "0 Ngày " + Integer.toString((int) (day * 24)) + " Giờ";
        } else if (day > 1) {
            int decimal = (int) day;
            float factional = day - decimal;
            totalDayAndHour = Integer.toString(decimal) + " Ngày " + Integer.toString((int) (factional * 60)) + " Giờ";
        } else {
            totalDayAndHour = "1 Ngày 0 Giờ";
        }

        return totalDayAndHour;

    }

    public String convertToHourAndMin(long second) {

        double min = (float) second / 60;
        double hour = min / 60;

        double hourEven = Math.floor(hour);
        double hourFactional = hour - hourEven;

        String totalHourAndMin = "";

        totalHourAndMin = (int) hourEven + " Giờ " + (int) (hourFactional * 10) * 6 + " Phút";

        return totalHourAndMin;

    }

    public String getCurrentDayMonthYear() {
        return Instant
                .ofEpochMilli(System.currentTimeMillis())
                .atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String covertMiliToDayMonthYear(long miliseconds) {
        return Instant
                .ofEpochMilli(miliseconds)
                .atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
