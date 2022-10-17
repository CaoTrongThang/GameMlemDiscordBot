package src.ctt.GameMlemBot.Logic.Commands.OsuCommandHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import src.ctt.GameMlemBot.Enum.OsuModes;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuAPIKey;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuBestScore.OsuBestScore;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuRecentScore.OsuRecentScore;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuUserData.OsuUserData;
import src.ctt.GameMlemBot.Logic.Data.OsuData.OsuModel.OsuUserData.OsuUserStatistics.OsuUserMonthlyPlayerCounts;
import src.ctt.GameMlemBot.Utils.EnviromentGet;

public class OsuRequest {
    public static final String OSU_API_URL = "https://osu.ppy.sh/api/v2";

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
        InputStream is;
        byte[] buffer;
        String jsonString;
        OsuUserData user;

        try {
            URL url = new URL(OSU_API_URL + "/users/" + nameOrID);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            setDefaultHeader(conn, "GET");

            is = conn.getInputStream();

            while (true) {
                if (is.available() > 0) {
                    buffer = new byte[is.available()];
                    buffer = is.readAllBytes();
                    break;
                }
            }

            is.close();

            jsonString = new String(buffer, StandardCharsets.UTF_8);
            Gson gson = new GsonBuilder().create();
            user = new OsuUserData();

            user = gson.fromJson(jsonString, user.getClass());

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OsuUserData getUser(String nameOrID, OsuModes mode) {
        InputStream is;
        byte[] buffer;
        String jsonString;
        OsuUserData user;

        try {
            URL url = new URL(OSU_API_URL + "/users" + "/" + nameOrID + "/" + mode.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            setDefaultHeader(conn, "GET");

            is = conn.getInputStream();

            while (true) {
                if (is.available() > 0) {
                    buffer = new byte[is.available()];
                    buffer = is.readAllBytes();
                    break;
                }
            }

            is.close();

            jsonString = new String(buffer, StandardCharsets.UTF_8);
            Gson gson = new GsonBuilder().create();

            user = new OsuUserData();

            user = gson.fromJson(jsonString, user.getClass());

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OsuRecentScore[] getOsuRecentScore(String nameOrID) {
        Gson gson = new GsonBuilder().create();
        OsuRecentScore[] score = null;
        try {
            URL url = new URL(OSU_API_URL + "/users/" + nameOrID + "/scores" + "/recent?limit=1&include_fails=1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            setDefaultHeader(conn, "GET");

            InputStream is = conn.getInputStream();
            byte[] buffer = new byte[is.available()];
            while (true) {
                if (is.available() > 0) {
                    buffer = is.readAllBytes();
                    break;
                }
            }
            String responedJson = new String(buffer, StandardCharsets.UTF_8);
            System.out.println(responedJson);
            score = gson.fromJson(responedJson, OsuRecentScore[].class);
            return score;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OsuBestScore[] getOsuBestScore(String nameOrID, int limit) {
        return null;
    }

    public float getOsuMaxBeatmapPP() {

        return 0;
    }

    public void setDefaultHeader(HttpURLConnection conn, String method) {
        if (OsuAPIKey.osuAPIKey.getExpires_in() < 60) {
            postAccessAPIKey();
        }
        try {
            conn.setRequestMethod(method);
        } catch (Exception e) {

        }
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
