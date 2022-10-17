package src.ctt.GameMlemBot.Utils;

public class EnviromentGet {
    public static String DISCORD_BOT_TOKEN() {
        return System.getenv("DISCORD_TOKEN");
    }

    public static int OSU_CLIENT_ID() {
        return Integer.parseInt(System.getenv("OSU_CLIENT_ID"));
    }

    public static String OSU_CLIENT_SECRET() {
        return System.getenv("OSU_CLIENT_SECRET");
    }
}
