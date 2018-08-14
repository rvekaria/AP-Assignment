package zoo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;

public class Weather implements Runnable {

    private static String weather;
    private static String weatherDisplay;
    private static LocalTime timeStamp;

    public void run() {
        requestWeather();
        JsonObject weatherObject = getWeatherAsJsonObject();
        String weatherDesc = getWeatherDescription(weatherObject);
        String temp = getTemp(weatherObject);
        weatherDisplay = "Weather: " + weatherDesc + "   Temperature: " + temp + "    Updated: " + timeStamp;
    }

    public static String getWeatherDisplay() {
        return weatherDisplay;
    }

    private static String getTemp(JsonObject weatherObject) {
        JsonObject mainElement = weatherObject.get("main").getAsJsonObject();
        return mainElement.get("temp").toString();
    }

    private static String getWeatherDescription(JsonObject weatherObject) {
        JsonObject weatherElement = weatherObject.get("weather").getAsJsonArray().get(0).getAsJsonObject();
        return weatherElement.get("description").toString();
    }

    private static JsonObject getWeatherAsJsonObject() {
        Gson gson = new Gson();
        return gson.fromJson(weather, JsonObject.class);
    }

    private static void requestWeather() {
        String inputLine;
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=London,uk&units=metric&APPID=135f81f5adaf34df4a549a16d5f474fb");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            timeStamp = LocalTime.now();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((inputLine = responseReader.readLine()) != null) {
                response.append(inputLine);
            }
            responseReader.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        weather = response.toString();
    }

}
