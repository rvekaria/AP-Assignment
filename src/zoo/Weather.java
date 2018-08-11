package zoo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {

    public static String getTemp(JsonObject weatherObject) {
        JsonObject mainElement = weatherObject.get("main").getAsJsonObject();
        return mainElement.get("temp").toString();
    }

    public static String getWeatherDescription(JsonObject weatherObject) {
        JsonObject weatherElement = weatherObject.get("weather").getAsJsonArray().get(0).getAsJsonObject();
        return weatherElement.get("description").toString();
    }

    public static JsonObject getWeatherAsJsonObject() {
        String weather = getWeather();
        Gson gson = new Gson();
        return gson.fromJson(weather, JsonObject.class);
    }

    private static String getWeather() {
        String inputLine;
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=London,uk&units=metric&APPID=135f81f5adaf34df4a549a16d5f474fb");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((inputLine = responseReader.readLine()) != null) {
                response.append(inputLine);
            }
            responseReader.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return response.toString();

    }
}
