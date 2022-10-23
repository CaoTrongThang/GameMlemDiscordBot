package src.ctt.GameMlemBot.Enums;

public enum TimeInterval {
    USER_MAX_INACTIVITY_TIME_10(10),
    AUTO_SAVE_INTERVAL(86400),
    EXPIRE_TIME(60),
    TIME_CHECK_INTERVAL_10(10),
    TIME_CHECK_INTERVAL_100(100),
    TIME_CHECK_INTERVAL_500(500),
    TIME_CHECK_INTERVAL_1000(1000),
    TIME_CHECK_INTERVAL_2000(2000),
    TIME_CHECK_INTERVAL_3000(3000);

    private int value;

    public int getValue() {
        return value;
    }

    private TimeInterval(int time) {
        this.value = time * 1000;
    }
}
