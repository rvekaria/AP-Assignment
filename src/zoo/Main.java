package zoo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean appIsRunning = true;
    private static String weatherDisplay;
    private static String unassignedAnimalsWarning;
    private static String unassignedPensWarning;

    public static void main(String[] args) {
        unassignedAnimalsWarning = "[WARNING] The following animals are not assigned to pens! Press 6 to assign animals to a pen.";
        unassignedPensWarning = "[WARNING] The following pens have no assigned keeper! Press 7 to assign animals to a pen.";
        //TODO - the pens warning may not be needed as keepers are autoassigned to a pen upon pen creation.
        ArrayList<Animal> unassignedAnimals;
        Controller.loadKeepers();
        Controller.loadPens();
        Controller.loadAnimals();
        weatherDisplay = Controller.updateWeatherDisplay();

        while (appIsRunning) {
            System.out.print(weatherDisplay);
            displayMainMenuOptions();
            unassignedAnimals = Controller.getAnimalsWithoutPens();
            if (unassignedAnimals.size() !=0) {
                System.out.println(unassignedAnimalsWarning);
                Controller.printUnassignedAnimals();
            }
//            System.out.println(unassignedPensWarning);

            String menuOption = scanner.nextLine();
            executeOption(menuOption);
        }

    }

    private static void displayMainMenuOptions() {
        System.out.println();
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
        System.out.print("> ");
    }

    private static void executeOption(String menuOption) {
        if (menuOption.equals("1")) {
            Controller.addNewPen(scanner);
        } else if (menuOption.equals("2")) {
            Controller.addNewAnimal(scanner);
        } else if (menuOption.equals("3")) {
            Controller.displayKeeperInfo();
        } else if (menuOption.equals("4")) {
            Controller.displayPenInfo();
        } else if (menuOption.equals("5")) {
            Controller.displayAnimalInfo();
        } else if (menuOption.equals("6")) {
            Controller.assignAnimalToPen(scanner);
        } else if (menuOption.equals("7")) {
            Controller.assignKeeperToPen(scanner);
        } else if (menuOption.equals("8")) {
            weatherDisplay = Controller.updateWeatherDisplay();
        } else if (menuOption.equals("0")) {
            appIsRunning = false;
        } else {
            System.out.println("That is not a valid option. Please enter in one of the numbered options.");
        }

    }

}
