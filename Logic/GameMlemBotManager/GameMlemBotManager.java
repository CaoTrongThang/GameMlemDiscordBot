package src.ctt.GameMlemBot.Logic.GameMlemBotManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.ApplicationInfo.Flag;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import src.ctt.GameMlemBot.Logic.Events.OnBotShutDown;
import src.ctt.GameMlemBot.Logic.Events.OnButtonInteraction;
import src.ctt.GameMlemBot.Logic.Events.OnCommandAutoComplete;
import src.ctt.GameMlemBot.Logic.Events.OnMessageRecieve;
import src.ctt.GameMlemBot.Logic.Events.OnSlashCommand;
import src.ctt.GameMlemBot.Logic.Events.OnUserUpdateOnlineStatus;
import src.ctt.GameMlemBot.Logic.Model.GameMlemData.GameMlemGuild;

public class GameMlemBotManager {
    public static JDA jda;
    public static List<Guild> guilds;
    public static final String BOT_ACTIVITY = "Game Mlem";
    public static final String DISCORD_BOT_STARTED = "Game Mlem Discord Bot is started!";
    public static Collection<SlashCommandData> slashCommands = new ArrayList<>();

    static {
        Message.suppressContentIntentWarning();
    }

    public static void connect(String token) {
        guilds = new ArrayList<>();
        jda = JDABuilder.createDefault(token)
                .addEventListeners(new OnBotShutDown())
                .addEventListeners(new OnButtonInteraction())
                .addEventListeners(new OnSlashCommand())
                .addEventListeners(new OnMessageRecieve())
                .addEventListeners(new OnUserUpdateOnlineStatus())
                .addEventListeners(new OnCommandAutoComplete())
                .enableIntents(GatewayIntent.GUILD_PRESENCES)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setMemberCachePolicy(MemberCachePolicy.ONLINE)
                .setChunkingFilter(ChunkingFilter.ALL)
                .setActivity(Activity.playing(BOT_ACTIVITY))
                .enableCache(CacheFlag.ONLINE_STATUS)
                .build();

        try {
            jda.awaitReady();
        } catch (Exception e) {

        }
        guilds = jda.getGuilds();

        for (Guild guild : guilds) {
            GameMlemGuildManager.gameMlemGuidlds.add(new GameMlemGuild(guild));
        }

        addSlashCommand();

        System.out.println(DISCORD_BOT_STARTED);
    }

    public static void addSlashCommand() {
        for (Guild guild : guilds) {
            guild.updateCommands().addCommands(slashCommands).queue();
        }
    }

    public static void disconnect() {
        jda.shutdownNow();
    }

}
