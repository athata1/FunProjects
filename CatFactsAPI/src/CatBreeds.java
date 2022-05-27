import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CatBreeds {
    public static void main(String[] args) throws Exception{
        URL url = new URL("https://catfact.ninja/fact?max_length=140");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("GET");
        int status = connection.getResponseCode();
        String jsonString = "";
        if (status/100 == 2) {
            InputStream responseStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
            jsonString = reader.readLine();
        }
        JSONObject obj = new JSONObject(jsonString);
        System.out.println(obj.getInt("length"));
        System.out.println(obj.getString("fact"));
        connection.disconnect();
    }
}
