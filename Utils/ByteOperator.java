package src.ctt.GameMlemBot.Utils;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.ExecutionException;

public class ByteOperator {
    public static byte[] readByteFromFile(String filePath) {
        try {
            File file = new File(filePath);
            return Files.readAllBytes(file.toPath());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeByteToFile(byte[] buffer, String path) {
        try {
            File file = new File(path);
            Files.write(file.toPath(), buffer);
        } catch (Exception e) {

        }
    }

    public static String getStringFromByte(byte[] buffer) {
        return new String(buffer, StandardCharsets.UTF_8);
    }
}
