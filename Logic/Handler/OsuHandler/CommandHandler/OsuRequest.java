package src.ctt.GameMlemBot.Logic.Handler.OsuHandler.CommandHandler;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import src.ctt.GameMlemBot.Enums.OsuModes;
import src.ctt.GameMlemBot.Enums.OsuMods;
import src.ctt.GameMlemBot.Logic.Model.OsuData.OsuAPIKey;
import src.ctt.GameMlemBot.Logic.Model.OsuData.OsuModel.OsuBeatmapCalculateData.OsuBeatmapCalculatedData;
import src.ctt.GameMlemBot.Logic.Model.OsuData.OsuModel.OsuBeatmapData.OsuBeatmapData;
import src.ctt.GameMlemBot.Logic.Model.OsuData.OsuModel.OsuBestScore.OsuBestScore;
import src.ctt.GameMlemBot.Logic.Model.OsuData.OsuModel.OsuRecentScore.OsuRecentScore;
import src.ctt.GameMlemBot.Logic.Model.OsuData.OsuModel.OsuUserData.OsuUserData;
import src.ctt.GameMlemBot.Logic.Model.OsuData.OsuModel.OsuUserData.OsuUserStatistics.OsuUserMonthlyPlayerCounts;
import src.ctt.GameMlemBot.Utils.EnviromentGet;
import src.ctt.GameMlemBot.Utils.FilePath;

public class OsuRequest {
    public static final String OSU_API_URL = "https://osu.ppy.sh/api/v2";
    public static final String OSU_BEATMAP_CALCULATOR_URL = "http://localhost:5000/api/calcMax";
    public static final String OSU_USER_RECENT_PP_URL = "http://localhost:5000/api/calcCur";
    public static final String OSU_BEATMAP_FILE = "https://osu.ppy.sh/osu/";
    public static final int MAX_READ_TIMEOUT = 10000;

    public static void postAccessAPIKey() {
        try {
            URL url = new URL("https://osu.ppy.sh/oauth/token");
            String jsonData = "{\"client_id\": " + EnviromentGet.OSU_CLIENT_ID()
                    + ",\"client_secret\":" + "\"" + EnviromentGet.OSU_CLIENT_SECRET() + "\""
                    + ",\"grant_type\": \"client_credentials\""
                    + ",\"scope\": \"public\"}";

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Length", Integer.toString(jsonData.length()));
            conn.setUseCaches(false);

            OutputStream dos = conn.getOutputStream();
            dos.write(jsonData.getBytes());
            InputStream din = conn.getInputStream();

            byte[] buffer = new byte[30000];
            String data = "";
            while (true) {
                if (din.available() > 0) {
                    buffer = din.readAllBytes();
                } else {
                    break;
                }
            }
            data = new String(buffer, StandardCharsets.US_ASCII);

            Gson gson = new GsonBuilder().create();
            OsuAPIKey.osuAPIKey = gson.fromJson(data, OsuAPIKey.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public OsuUserData getUser(String nameOrID) {
        URL url;
        HttpURLConnection conn;
        byte[] buffer;
        String jsonResponse;
        Gson gson = new GsonBuilder().create();
        InputStream is;
        OsuUserData user;

        try {
            url = new URL(OSU_API_URL + "/users/" + nameOrID);
            conn = (HttpURLConnection) url.openConnection();

            setDefaultHeader(conn, "GET");

            is = conn.getInputStream();

            while (true) {
                if (is.available() > 0) {
                    buffer = new byte[is.available()];
                    buffer = is.readAllBytes();
                    break;
                } else if (conn.getReadTimeout() > MAX_READ_TIMEOUT) {
                    return null;
                }
            }

            is.close();

            jsonResponse = new String(buffer, StandardCharsets.UTF_8);
            user = new OsuUserData();

            user = gson.fromJson(jsonResponse, user.getClass());

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OsuUserData getUser(String nameOrID, OsuModes mode) {
        URL url;
        HttpURLConnection conn;
        byte[] buffer;
        String jsonResponse;
        Gson gson = new GsonBuilder().create();
        InputStream is;
        OsuUserData user;

        try {
            url = new URL(OSU_API_URL + "/users" + "/" + nameOrID + "/" + mode.toString());
            conn = (HttpURLConnection) url.openConnection();

            setDefaultHeader(conn, "GET");

            is = conn.getInputStream();

            while (true) {
                if (is.available() > 0) {
                    buffer = new byte[is.available()];
                    buffer = is.readAllBytes();
                    break;
                } else if (conn.getReadTimeout() > MAX_READ_TIMEOUT) {
                    return null;
                }
            }
            is.close();

            jsonResponse = new String(buffer, StandardCharsets.UTF_8);

            user = new OsuUserData();

            user = gson.fromJson(jsonResponse, user.getClass());

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OsuRecentScore getOsuRecentScore(String nameOrID, OsuModes mode) {
        Gson gson = new GsonBuilder().create();
        OsuRecentScore[] scores = null;
        URL url;
        short retryTimes = 0;
        short limit = 10;
        final byte LIMIT_INCREASE_RATE = 10;
        boolean isSame = true;

        try {
            while (isSame) {
                url = new URL(
                        OSU_API_URL + "/users/" + nameOrID + "/scores" + "/recent?limit=" + limit + "&include_fails=1"
                                + "&mode="
                                + mode.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                setDefaultHeader(conn, "GET");

                InputStream is = conn.getInputStream();
                byte[] buffer = new byte[is.available()];
                while (true) {
                    if (is.available() > 0) {
                        buffer = is.readAllBytes();
                        break;
                    } else if (conn.getReadTimeout() > MAX_READ_TIMEOUT) {
                        return null;
                    }
                }
                String jsonResponse = new String(buffer, StandardCharsets.UTF_8);
                scores = gson.fromJson(jsonResponse, OsuRecentScore[].class);
                if (buffer.length == 2) {
                    return null;
                }
                for (int index = retryTimes; index < scores.length;) {

                    if (retryTimes == scores.length - 2
                            && scores[retryTimes].getBeatmap().getId() == scores[retryTimes + 1].getBeatmap().getId()) {
                        retryTimes++;
                        limit += LIMIT_INCREASE_RATE;
                        break;
                    }

                    if (index != scores.length - 1
                            && scores[retryTimes].getBeatmap().getId() == scores[retryTimes + 1].getBeatmap().getId()) {
                        retryTimes++;
                    }

                    if (scores[retryTimes].getBeatmap().getId() != scores[retryTimes + 1].getBeatmap().getId()) {
                        isSame = false;
                        break;
                    }
                }
            }
            scores[0].setCustomRetry(retryTimes);
            return scores[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OsuBeatmapData getBeatmapData(long beatmapID) {
        URL url;
        HttpURLConnection conn;
        byte[] buffer;
        String jsonResponse;
        Gson gson = new GsonBuilder().create();
        InputStream is;

        OsuBeatmapData beatmap;

        try {
            url = new URL(OSU_API_URL + "/beatmaps" + "/" + beatmapID);
            conn = (HttpURLConnection) url.openConnection();
            setDefaultHeader(conn, "GET");

            is = conn.getInputStream();
            while (true) {
                if (is.available() > 0) {
                    buffer = is.readAllBytes();
                    break;
                } else if (conn.getReadTimeout() > MAX_READ_TIMEOUT) {
                    return null;
                }
            }
            jsonResponse = new String(buffer, StandardCharsets.UTF_8);
            beatmap = gson.fromJson(jsonResponse, OsuBeatmapData.class);
            return beatmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // TODO
    public OsuBestScore[] getOsuBestScore(String nameOrID, int limit) {
        URL url;
        HttpURLConnection conn;
        byte[] buffer;
        String jsonResponse;
        Gson gson = new GsonBuilder().create();
        InputStream is;
        return null;
    }

    public OsuBeatmapCalculatedData getOsuBeatmapCalculateData(long mapID, String[] mods, boolean multiAcc) {
        return null;
    }

    public OsuBeatmapCalculatedData getOsuBeatmapCalculateData(long mapID, String[] mods, float acc) {
        OsuBeatmapCalculatedData beatmapCalculateData[];
        URL getBeatmapFile;
        URL beatmapCalculate;
        OsuBeatmapData beatmap;
        HttpURLConnection conn;
        byte[] buffer;
        String jsonResponse;
        Gson gson = new GsonBuilder().create();
        InputStream is;
        long beatmapName = System.currentTimeMillis();

        try {
            getBeatmapFile = new URL(
                    OSU_BEATMAP_FILE + mapID);

            conn = (HttpURLConnection) getBeatmapFile.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(MAX_READ_TIMEOUT);

            is = conn.getInputStream();

            while (true) {
                if (is.available() > 0) {
                    buffer = is.readAllBytes();
                    break;
                } else if (conn.getReadTimeout() > MAX_READ_TIMEOUT) {
                    return null;
                }
            }
            is.close();

            jsonResponse = new String(buffer, StandardCharsets.UTF_8);
            File file = new File(FilePath.OSU_BEATMAP_FILE_DIR + "\\" + beatmapName + ".osu");
            Files.write(file.toPath(), buffer);

            beatmap = getBeatmapData(mapID);

            beatmapCalculate = new URL(OSU_BEATMAP_CALCULATOR_URL
                    + "?mods=" + OsuMods.AP.getTotalModsBitwise(mods)
                    + "&bID=" + beatmapName
                    + "&maxCombo=" + beatmap.getMax_combo()
                    + "&acc=" + acc * 100);
            conn = (HttpURLConnection) beatmapCalculate.openConnection();
            is = conn.getInputStream();
            while (true) {
                if (is.available() > 0) {
                    buffer = is.readAllBytes();
                    break;
                } else if (conn.getReadTimeout() > MAX_READ_TIMEOUT) {
                    return null;
                }
            }
            jsonResponse = new String(buffer, StandardCharsets.UTF_8);
            beatmapCalculateData = gson.fromJson(jsonResponse, OsuBeatmapCalculatedData[].class);

            Files.delete(file.toPath());

            return beatmapCalculateData[0];

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OsuBeatmapCalculatedData getUserRecentPP(OsuRecentScore score) {
        OsuBeatmapCalculatedData beatmapCalculateData[];
        URL getBeatmapFile;
        URL beatmapCalculate;
        OsuBeatmapData beatmap;
        HttpURLConnection conn;
        byte[] buffer;
        String jsonResponse;
        Gson gson = new GsonBuilder().create();
        InputStream is;
        long beatmapName = System.currentTimeMillis();

        try {
            getBeatmapFile = new URL(
                    OSU_BEATMAP_FILE + score.getBeatmap().getId());

            conn = (HttpURLConnection) getBeatmapFile.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(MAX_READ_TIMEOUT);

            is = conn.getInputStream();

            while (true) {
                if (is.available() > 0) {
                    buffer = is.readAllBytes();
                    break;
                } else if (conn.getReadTimeout() > MAX_READ_TIMEOUT) {
                    return null;
                }
            }
            is.close();

            jsonResponse = new String(buffer, StandardCharsets.UTF_8);
            File file = new File(FilePath.OSU_BEATMAP_FILE_DIR + "\\" + beatmapName + ".osu");
            Files.write(file.toPath(), buffer);

            beatmap = getBeatmapData(score.getBeatmap().getId());

            beatmapCalculate = new URL(OSU_USER_RECENT_PP_URL
                    + "?mods=" + OsuMods.AP.getTotalModsBitwise(score.getMods())
                    + "&bID=" + beatmapName
                    + "&acc=" + score.getAccuracy() * 100
                    + "&mode=" + OsuModes.valueOf(score.getMode()).getID()
                    + "&n300=" + score.getStatistics().getCount_300()
                    + "&n100=" + score.getStatistics().getCount_100()
                    + "&n50=" + score.getStatistics().getCount_50()
                    + "&nMisses=" + score.getStatistics().getCount_miss()
                    + "&nKatu=" + score.getStatistics().getCount_katu()
                    + "&maxCombo=" + score.getMax_combo());
            conn = (HttpURLConnection) beatmapCalculate.openConnection();
            is = conn.getInputStream();
            while (true) {
                if (is.available() > 0) {
                    buffer = is.readAllBytes();
                    break;
                } else if (conn.getReadTimeout() > MAX_READ_TIMEOUT) {
                    return null;
                }
            }
            jsonResponse = new String(buffer, StandardCharsets.UTF_8);
            beatmapCalculateData = gson.fromJson(jsonResponse, OsuBeatmapCalculatedData[].class);

            Files.delete(file.toPath());

            return beatmapCalculateData[0];

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDefaultHeader(HttpURLConnection conn, String method) {
        if (OsuAPIKey.osuAPIKey.getExpires_in() < 60) {
            postAccessAPIKey();
        }
        try {
            conn.setRequestMethod(method);
        } catch (Exception e) {

        }
        conn.setReadTimeout(10000);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + OsuAPIKey.osuAPIKey.getAccess_token());
    }

    /**
     * 
     * @param monthly_playcounts
     * @return the png link of the graph
     */
    public String getGraphLine(OsuUserData user) {
        if (user == null) {
            return null;
        }
        OsuUserMonthlyPlayerCounts[] monthly_playcounts = user.getMothly_playcounts();

        String label = "";
        String data = "";
        StringBuilder lableBuilder = new StringBuilder(label);
        StringBuilder dataBuilder = new StringBuilder(data);
        if (monthly_playcounts.length > 5) {
            for (int x = monthly_playcounts.length - 5; x < monthly_playcounts.length; x++) {
                if (x == monthly_playcounts.length - 1) {
                    lableBuilder.append("\"" + monthly_playcounts[x].getStart_date() + "\"");
                    dataBuilder.append(monthly_playcounts[x].getCount());
                } else {
                    lableBuilder.append("\"" + monthly_playcounts[x].getStart_date() + "\"" + ",");
                    dataBuilder.append(monthly_playcounts[x].getCount() + ",");
                }
            }
        } else {
            for (int x = 0; x < monthly_playcounts.length; x++) {
                if (x == monthly_playcounts.length - 1) {
                    lableBuilder.append("\"" + monthly_playcounts[x].getStart_date() + "\"");
                    dataBuilder.append(monthly_playcounts[x].getCount());
                } else {
                    lableBuilder.append("\"" + monthly_playcounts[x].getStart_date() + "\"" + ",");
                    dataBuilder.append(monthly_playcounts[x].getCount() + ",");
                }
            }
        }

        lableBuilder.insert(0, "[").insert(lableBuilder.length(), "]");
        dataBuilder.insert(0, "[").insert(dataBuilder.length(), "]");

        try {
            URL url = new URL("https://quickchart.io/chart/create");
            String json = "{\"backgroundColor\": \"transparent\""
                    + ",\"borderColor\": \"#FF0000\""
                    + ",\"colors\": \"#FFFFFF\""
                    + ",\"version\": \"2\""
                    + ",\"width\": 600"
                    + ",\"height\": 200"
                    + ",\"format\": \"png\""
                    + ",\"chart\": "
                    + "{\"type\": \"line\""
                    + ",\"data\": "
                    + "{\"labels\": "
                    + lableBuilder.toString()
                    + ",\"datasets\": "
                    + "[{\"label\": " + "\"" + user.getUsername() + "\""
                    + ",\"data\": "
                    + dataBuilder.toString()
                    + "}]}}}";

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setUseCaches(false);

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            InputStream is = conn.getInputStream();

            byte[] buffer = new byte[1000];
            while (true) {
                if (is.available() > 0) {
                    buffer = is.readAllBytes();
                    break;
                } else if (conn.getReadTimeout() > MAX_READ_TIMEOUT) {
                    return null;
                }
            }
            String jsonRespone = new String(buffer, StandardCharsets.US_ASCII);

            return jsonRespone.substring(23, jsonRespone.length() - 2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
