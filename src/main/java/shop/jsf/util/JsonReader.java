package shop.jsf.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class JsonReader {

    public static String readJsonFromUrl(String address) throws IOException {
        URL url = new URL(address);
        URLConnection conn = url.openConnection();
        BufferedReader buffer = new BufferedReader(
                new InputStreamReader(
                        conn.getInputStream(),
                        Charset.defaultCharset()));
        return bufferToString(buffer);
    }

    private static String bufferToString(BufferedReader buffer) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = buffer.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
