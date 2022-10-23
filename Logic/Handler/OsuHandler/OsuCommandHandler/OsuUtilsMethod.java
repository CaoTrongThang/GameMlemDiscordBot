package src.ctt.GameMlemBot.Logic.Handler.OsuHandler.OsuCommandHandler;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import src.ctt.GameMlemBot.Logic.Model.OsuData.OsuModel.OsuBeatmapData.OsuBeatmapData;
import src.ctt.GameMlemBot.Logic.Model.OsuData.OsuModel.OsuRecentScore.OsuRecentScore;
import src.ctt.GameMlemBot.Utils.FilePath;

public class OsuUtilsMethod {
    public long roll(long maxRoll) {
        return (long) (Math.random() * maxRoll);
    }

    public float getRecentCompletePercent(OsuRecentScore score) {
        float totalHitObject = score.getStatistics().getCount_300() + score.getStatistics().getCount_100()
                + score.getStatistics().getCount_50() + score.getStatistics().getCount_miss();

        return ((totalHitObject * 100) / getTotalObjectInBeatmap(score));
    }

    public float getTotalObjectInBeatmap(OsuRecentScore score) {
        int totalObject = score.getBeatmap().getCount_circles() + score.getBeatmap().getCount_sliders()
                + score.getBeatmap().getCount_spinners();
        return totalObject;
    }
}
