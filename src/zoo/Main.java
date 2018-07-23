package zoo;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

//public class Main extends Application {
public class Main {

    /*@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("screen1.fxml"));
        primaryStage.setTitle("Zoo Information Management System");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }*/

    public static void main(String[] args) {
        //launch(args);

        String weatherDesc = Weather.getWeatherDescription();

        String temp = Weather.getTemp();


    }

    private static void displayMainMenuOptions(){
        System.out.println("Select one of the following options by entering in the number:");
        System.out.println("(1) Add a new pen");
        System.out.println("(2) Add a new animal");
        System.out.println("(3) View list of zookeepers");
        System.out.println("(4) View list of pens");
        System.out.println("(5) View list of animals");
        System.out.println("(6) Assign animal to a pen");
        System.out.println("(7) Assign zookeper to a pen");
    }


}
