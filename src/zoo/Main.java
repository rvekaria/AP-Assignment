package zoo;

import com.google.gson.JsonObject;

import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

//public class Main extends Application {
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean appIsRunning = true;
    /*@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("screen1.fxml"));
        primaryStage.setTitle("Zoo Information Management System");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }*/

    public static void main(String[] args) {
        //launch(args);
        while (appIsRunning) {
            displayMainMenuOptions();
            String menuOptionString = scanner.nextLine();

        }

    }

    private static void executeOption(String menuOption) {
        //TODO - change this to if/else statements and use .equals() to compare string values
        switch (menuOption) {
            case "1":
                addNewPen();
                break;
//            case "2": addNewAnimal();
//                break;
//            case "3": listAllZooKeepers();
//                break;
//            case "4": listAllPens();
//                break;
//            case "5": listAllAnimals();
//                break;
//            case "6": assignAnimalToPen();
//                break;
//            case "7": assignKeeperToPen();
//                break;
            case "8":
                updateWeatherDisplay();
                break;
            case "0":
                appIsRunning = false;
                break;
            default:
                System.out.println("That is not a valid option. Please enter in one of the numbered options.");
        }
    }

    private static void addNewPen() {
        System.out.println("What type of pen do you want to add? Select one of the options:");
        System.out.println("(1) Dry Pen");
        System.out.println("(2) Petting Pen");
        System.out.println("(3) Part Dry, Part Water Pen");
        System.out.println("(4) Aquarium");
        System.out.println("(5) Aviary");

    }

    private static void displayMainMenuOptions() {
        System.out.println("Select one of the following options by entering in the number:");
        System.out.println("(1) Add a new pen");
        System.out.println("(2) Add a new animal");
        System.out.println("(3) View list of zookeepers");
        System.out.println("(4) View list of pens");
        System.out.println("(5) View list of animals");
        System.out.println("(6) Assign animal to a pen");
        System.out.println("(7) Assign zookeper to a pen");
        System.out.println("(8) Update weather");
        System.out.println("(0) Exit application");
    }

    private static void updateWeatherDisplay() {
        LocalTime timeStamp = LocalTime.now();
        JsonObject weatherObject = Weather.getWeatherAsJsonObject();
        String weatherDesc = Weather.getWeatherDescription(weatherObject);
        String temp = Weather.getTemp(weatherObject);

        String weatherDisplay = "Weather: " + weatherDesc + "   Temperature: " + temp + "\nUpdated: " + timeStamp;
        System.out.println(weatherDisplay);
    }

}
