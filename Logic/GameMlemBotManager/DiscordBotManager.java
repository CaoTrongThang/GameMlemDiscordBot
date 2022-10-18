package src.ctt.GameMlemBot.Logic.GameMlemBotManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import src.ctt.GameMlemBot.Logic.Events.OnMessageRecieve;
import src.ctt.GameMlemBot.Logic.Events.OnSlashCommand;
import src.ctt.GameMlemBot.Logic.Handler.BaseCommands;
import src.ctt.GameMlemBot.Logic.Handler.BrawlhallaHandler.BrawlhallaCommandHandler.BrawlhallaSubCommands;
import src.ctt.GameMlemBot.Logic.Handler.OsuHandler.OsuCommandHandler.OsuSubCommands;

public class DiscordBotManager {
    public static JDA jda;
    public static List<Guild> guilds;
    public static final String BOT_ACTIVITY = "Game Mlem";
    public static final String DISCORD_BOT_STARTED = "Game Mlem Discord Bot is started!";
    static {
        Message.suppressContentIntentWarning();
    }

    public static void connect(String token) {
        guilds = new ArrayList<>();
        jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.playing(BOT_ACTIVITY))
                .build();
        try {
            jda.awaitReady();
        } catch (Exception e) {

        }

        guilds = jda.getGuilds();

        jda.addEventListener(new OnSlashCommand());
        jda.addEventListener(new OnMessageRecieve());

        addSlashCommand();

        System.out.println(DISCORD_BOT_STARTED);
    }

    public static void addEvent(ListenerAdapter event) {
        jda.addEventListener(event);
    }

    public static void addSlashCommand() {
        Collection<SlashCommandData> commands = new ArrayList<>();

        for (int r = 0; r < BaseCommands.BASE_COMMANDS.length; r++) {
            if (BaseCommands.BASE_COMMANDS[r][0].equalsIgnoreCase("gamemlem")) {

            } else if (BaseCommands.BASE_COMMANDS[r][0].equalsIgnoreCase("osu")) {
                commands.add(Commands.slash(BaseCommands.BASE_COMMANDS[r][0], BaseCommands.BASE_COMMANDS[r][1])
                        .addSubcommands(OsuSubCommands.OSU_SUB_COMMANDS));
            } else if (BaseCommands.BASE_COMMANDS[r][0].equalsIgnoreCase("brawlhalla")) {
                commands.add(Commands.slash(BaseCommands.BASE_COMMANDS[r][0], BaseCommands.BASE_COMMANDS[r][1])
                        .addSubcommands(BrawlhallaSubCommands.BRAWLHALL_SUB_COMMANDS));
            }
        }
        for (Guild guild : guilds) {
            guild.updateCommands().addCommands(commands).queue();
        }
    }

    public static void disconnect() {
        jda.shutdownNow();
    }

}
