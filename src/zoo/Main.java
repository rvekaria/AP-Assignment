package zoo;

import com.google.gson.JsonObject;
import com.sun.tools.classfile.Synthetic_attribute;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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
        loadKeepers();
        loadPens();
        loadAnimals();
        while (appIsRunning) {
            displayMainMenuOptions();
            String menuOption = scanner.nextLine();
            executeOption(menuOption);
        }

    }

    private static void loadKeepers() {
        File file = new File("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/zooKeeperData/keeperData.json");
        if (file.length() != 0) {
            ZooKeeper.listOfAllZooKeepers = ZooKeeper.instantiateKeepersFromJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/zooKeeperData/keeperData.json");
        } else {
            ZooKeeper hardip = createZooKeeper(Pen.PenType.DRY, Pen.PenType.AVIARY, "Hardip");
            ZooKeeper alex = createZooKeeper(Pen.PenType.AQUARIUM, Pen.PenType.PARTDRYWATER, "Alex");
            ZooKeeper farhad = createZooKeeper(Pen.PenType.AVIARY, Pen.PenType.AQUARIUM, "Farhad");
            ZooKeeper alan = createZooKeeper(Pen.PenType.DRY, Pen.PenType.PETTING, "Alan");
        }
    }

    private static ZooKeeper createZooKeeper(Pen.PenType type1, Pen.PenType type2, String keeperName) {
        Pen.PenType[] pensTrainedFor = {type1, type2};
        ArrayList<Pen.PenType> trainedForPensList = createTrainedForPensList(pensTrainedFor);
        ArrayList<Integer> assignedPens = new ArrayList<>();
        return new ZooKeeper(keeperName, trainedForPensList, assignedPens);
    }

    private static ArrayList<Pen.PenType> createTrainedForPensList(Pen.PenType[] pensList) {
        ArrayList<Pen.PenType> trainedForPensList = new ArrayList<>();
        trainedForPensList.addAll(Arrays.asList(pensList));
        return trainedForPensList;
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
            addNewPen();
        } else if (menuOption.equals("2")) {
            addNewAnimal();
        } else if (menuOption.equals("3")) {
            displayKeeperInfo();
        } else if (menuOption.equals("4")) {
            displayPenInfo();
        } else if (menuOption.equals("5")) {
            displayAnimalInfo();
        } else if (menuOption.equals("6")) {
            assignAnimalToPen();
        } else if (menuOption.equals("7")) {

        } else if (menuOption.equals("8")) {
            updateWeatherDisplay();
        } else if (menuOption.equals("0")) {
            appIsRunning = false;
        } else {
            System.out.println("That is not a valid option. Please enter in one of the numbered options.");
        }

    }

    private static void assignAnimalToPen() {
        System.out.println("Which of these animals do you want to assign to a pen:");
        displayAnimalInfo();
        System.out.print("> ");
        int animalId = Integer.parseInt(scanner.nextLine());
        System.out.println("Which pen do you want to assign the animal to:");
        displayPenInfo();
        System.out.print("> ");
        int penId = Integer.parseInt(scanner.nextLine());
        Animal animal = Animal.getAnimalWithAnimalId(animalId);
        animal.setAssignedPen(penId);
    }

    private static void addNewAnimal() {
        boolean isNotValidOption = true;
        ArrayList<String> validOptions = new ArrayList<>();
        validOptions.add("1");
        validOptions.add("2");
        validOptions.add("3");
        validOptions.add("4");
        validOptions.add("5");
        while (isNotValidOption) {
            displayAnimalOptions();
            String animalOption = scanner.nextLine();
            if (validOptions.contains(animalOption)) {
                isNotValidOption = false;
                createAnimal(animalOption);
            } else if (animalOption.equals("0")) {
                isNotValidOption = false;
            } else {
                System.out.println("That is not a valid option. Please enter in one of the numbered options.");
            }
        }

    }

    private static void createAnimal(String animalOption) {
        Animal animal;
        String name;
        String species;
        int assignedPenId;
        double landSpace;
        double waterSpace;
        System.out.println("Complete the following fields to add the animal:");
        System.out.print("Species: ");
        species = scanner.nextLine();
        System.out.print("Name: ");
        name = scanner.nextLine();
        System.out.println("Which pen do you want to assign the animal to? Pick a number from the list: ");
        displayPenInfo();
        System.out.print("> ");
        assignedPenId = Integer.parseInt(scanner.nextLine());
        switch (animalOption) {
            case "1"://Land Animal
                System.out.print("Land space required: ");
                landSpace = Double.parseDouble(scanner.nextLine());
                animal = new LandAnimal(name, species, assignedPenId, landSpace);
                Pen.writeDryPensToFile();
                Pen.writePettingPensToFile();
                //TODO - Don't I need to write the corresponding Pen file here?
                break;
            case "2"://Petting Animal
                System.out.print("Land space required: ");
                landSpace = Double.parseDouble(scanner.nextLine());
                animal = new PettingAnimal(name, species, assignedPenId, landSpace);
                Pen.writeDryPensToFile();
                Pen.writePettingPensToFile();
                break;
            case "3"://Amphibious Animal
                System.out.print("Land space required: ");
                landSpace = Double.parseDouble(scanner.nextLine());
                System.out.print("Water space required: ");
                waterSpace = Double.parseDouble(scanner.nextLine());
                animal = new AmphibiousAnimal(name, species, assignedPenId, landSpace, waterSpace);
                Pen.writePartDryWaterToFile();
                break;
            case "4"://Water Animal
                System.out.print("Water space required: ");
                waterSpace = Double.parseDouble(scanner.nextLine());
                animal = new WaterAnimal(name, species, assignedPenId, waterSpace);
                Pen.writeAquariumsToFile();
                break;
            default://Flying Animal
                System.out.print("Air space required: ");
                double airSpace = Double.parseDouble(scanner.nextLine());
                animal = new FlyingAnimal(name, species, assignedPenId, airSpace);
                Pen.writeAviariesToFile();
                break;
        }
    }

    private static void displayAnimalOptions() {
        System.out.println();
        System.out.println("What type of animal do you want to add? Select one of the options:");
        System.out.println("(1) Land Animal");
        System.out.println("(2) Petting Animal");
        System.out.println("(3) Amphibious Animal");
        System.out.println("(4) Water Animal");
        System.out.println("(5) Flying Animal");
        System.out.println("(0) Go back to main menu");
        System.out.print("> ");
    }

    private static void displayKeeperInfo() {
        for (int i = 0; i < ZooKeeper.listOfAllZooKeepers.size(); i++) {
            ZooKeeper keeper = ZooKeeper.listOfAllZooKeepers.get(i);
            System.out.println("[" + i + "] " + keeper.toString());
        }
    }

    private static void displayPenInfo() {
        if (!Pen.listOfAllPens.isEmpty()) {
            for (int i = 0; i < Pen.listOfAllPens.size(); i++) {
                Pen pen = Pen.listOfAllPens.get(i);
                System.out.println("[" + pen.penId + "] " + pen.displayInfo());
            }
        } else {
            System.out.println("There are no pens! Press 1 to add a new pen.");
        }
    }

    private static void displayAnimalInfo(){
        if (!Animal.allAnimalsInZooList.isEmpty()){
            for (int i=0; i<Animal.allAnimalsInZooList.size(); i++){
                Animal animal = Animal.getAnimalWithAnimalId(i);
                System.out.println("[" + i + "] " + animal.displayInfo());
            }
        } else {
            System.out.println("There are no animals! Press 2 to add a new animal.");
        }
    }

    private static void displayPenOptions() {
        System.out.println();
        System.out.println("What type of pen do you want to add? Select one of the options:");
        System.out.println("(1) Dry Pen");
        System.out.println("(2) Petting Pen");
        System.out.println("(3) Part Dry, Part Water Pen");
        System.out.println("(4) Aquarium");
        System.out.println("(5) Aviary");
        System.out.println("(0) Go back to main menu");
        System.out.print("> ");
    }

    private static void addNewPen() {
        boolean isNotValidOption = true;
        ArrayList<String> validOptions = new ArrayList<>();
        validOptions.add("1");
        validOptions.add("2");
        validOptions.add("3");
        validOptions.add("4");
        validOptions.add("5");
        while (isNotValidOption) {
            displayPenOptions();
            String penOption = scanner.nextLine();
            if (validOptions.contains(penOption)) {
                isNotValidOption = false;
                createPen(penOption);
            } else if (penOption.equals("0")) {
                isNotValidOption = false;
            } else {
                System.out.println("That is not a valid option. Please enter in one of the numbered options.");
            }
        }

    }

    private static void createPen(String penOption) {
        Pen pen;
        ArrayList<ZooKeeper> assignedKeeperList = autoAssignKeeper(penOption);
        ArrayList<Integer> animalsIdsInPen = new ArrayList<>();
        String name;
        int length;
        int width;
        int temp;
        int height;
        int volume;
        System.out.println("Complete the following fields to create the pen:");
        System.out.print("Pen name: ");
        name = scanner.nextLine();
        System.out.print("Pen length (metres): ");
        length = Integer.parseInt(scanner.nextLine());
        System.out.print("Pen width (metres): ");
        width = Integer.parseInt(scanner.nextLine());
        System.out.print("Pen temperature (\u00b0C): ");
        temp = Integer.parseInt(scanner.nextLine());
        switch (penOption) {
            case "1"://Dry Pen
                pen = new DryPen(name, length, width, temp, assignedKeeperList, animalsIdsInPen);
                break;
            case "2"://Petting Pen
                pen = new PettingPen(name, length, width, temp, assignedKeeperList, animalsIdsInPen);
                break;
            case "3"://Part Dry, Part Water Pen
                System.out.print("Water depth (metres): ");
                height = Integer.parseInt(scanner.nextLine());
                System.out.print("Land area (m^2): ");
                int area = Integer.parseInt(scanner.nextLine());
                System.out.print("Water volume (m^3): ");
                volume = Integer.parseInt(scanner.nextLine());
                pen = new PartDryWaterPen(name, length, width, height, area, volume, temp, assignedKeeperList, animalsIdsInPen);
                break;
            case "4"://Aquarium
                System.out.print("Height (m): ");
                height = Integer.parseInt(scanner.nextLine());
                pen = new Aquarium(name, length, width, height, temp, assignedKeeperList, animalsIdsInPen);
                break;
            default://Aviary
                System.out.print("Height (m): ");
                height = Integer.parseInt(scanner.nextLine());
                pen = new Aviary(name, length, width, height, temp, assignedKeeperList, animalsIdsInPen);
                break;
        }
        for (ZooKeeper keeper : assignedKeeperList) {
            keeper.addPenToAssignedPens(pen.getPenId());
            ZooKeeper.writeKeepersToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/zooKeeperData/keeperData.json");
        }
    }

    private static ArrayList<ZooKeeper> autoAssignKeeper(String penOption) {
        ZooKeeper keeper;
        ArrayList<ZooKeeper> keepersList = new ArrayList<>();
        switch (penOption) {
            case "1"://Dry Pen
                keeper = keeperWithFewerPens(0, 3);
                System.out.println(keeper.getName() + " will be the assigned keeper of this pen.");
                break;
            case "2"://Petting Pen
                keeper = ZooKeeper.listOfAllZooKeepers.get(3);
                System.out.println(keeper.getName() + " will be the assigned keeper of this pen.");
                break;
            case "3"://Part Dry, Part Water Pen
                keeper = ZooKeeper.listOfAllZooKeepers.get(1);
                System.out.println(keeper.getName() + " will be the assigned keeper of this pen.");
                break;
            case "4"://Aquarium
                keeper = keeperWithFewerPens(1, 2);
                System.out.println(keeper.getName() + " will be the assigned keeper of this pen.");
                break;
            default://Aviary
                keeper = keeperWithFewerPens(0, 2);
                System.out.println(keeper.getName() + " will be the assigned keeper of this pen.");
                break;
        }
        keepersList.add(keeper);
        return keepersList;
    }

    private static ZooKeeper keeperWithFewerPens(int keeper1, int keeper2) {
        int keeper1NumberOfPens = ZooKeeper.listOfAllZooKeepers.get(keeper1).getAssignedPenIds().size();
        int keeper2NumberOfPens = ZooKeeper.listOfAllZooKeepers.get(keeper2).getAssignedPenIds().size();
        return keeper1NumberOfPens > keeper2NumberOfPens ? ZooKeeper.listOfAllZooKeepers.get(keeper2) : ZooKeeper.listOfAllZooKeepers.get(keeper1);
    }

    private static void displayListOfKeepers() {
        System.out.println("Select a zookeeper from the below list of trained keepers to assign to this pen:");
        System.out.println("(1) Hardip      Dry Pens, Aviaries");
        System.out.println("(2) Alex        Aquariums, Part Water/Part Dry Pens");
        System.out.println("(3) Farhad      Aviaries, Aquariums");
        System.out.println("(4) Alan        Dry Pens, Petting Pens");
    }

    private static void updateWeatherDisplay() {
        LocalTime timeStamp = LocalTime.now();
        JsonObject weatherObject = Weather.getWeatherAsJsonObject();
        String weatherDesc = Weather.getWeatherDescription(weatherObject);
        String temp = Weather.getTemp(weatherObject);

        String weatherDisplay = "Weather: " + weatherDesc + "   Temperature: " + temp + "\nUpdated: " + timeStamp;
        System.out.println();
        System.out.println(weatherDisplay);
    }

    private static void loadPens() {
        loadDryPens();
        loadPettingPens();
        loadDryWaterPens();
        loadAquariums();
        loadAviaries();
    }

    static void loadDryPens() {
        Pen.listOfAllDryPens = DryPen.instantiatePensFromJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/dryPensData.json", DryPen.class);
    }

    static void loadPettingPens() {
        Pen.listOfAllPettingPens = PettingPen.instantiatePensFromJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/pettingPensData.json", PettingPen.class);
    }

    static void loadDryWaterPens() {
        Pen.listOfAllDryWaterPens = PartDryWaterPen.instantiatePensFromJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/partDryWaterPensData.json", PartDryWaterPen.class);
    }

    static void loadAquariums() {
        Pen.listOfAllAquariums = Aquarium.instantiatePensFromJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/aquariumsData.json", Aquarium.class);
    }

    static void loadAviaries() {
        Pen.listOfAllAviaries = Aviary.instantiatePensFromJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/aviariesData.json", Aviary.class);
    }

    private static void loadAnimals() {
        loadLandAnimals();
        loadPettingAnimals();
        loadAmphibiousAnimals();
        loadWaterAnimals();
        loadFlyingAnimals();
    }

    private static void loadLandAnimals() {
        Animal.allLandAnimals = LandAnimal.instantiateAnimalsFromJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/landAnimalData.json", LandAnimal.class);
    }

    private static void loadPettingAnimals() {
        Animal.allPettingAnimals = PettingAnimal.instantiateAnimalsFromJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/pettingAnimalData.json", PettingAnimal.class);
    }

    private static void loadAmphibiousAnimals() {
        Animal.allAmphibiousAnimals = AmphibiousAnimal.instantiateAnimalsFromJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/amphibiansData.json", AmphibiousAnimal.class);
    }

    private static void loadWaterAnimals() {
        Animal.allWaterAnimals = WaterAnimal.instantiateAnimalsFromJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/waterAnimalData.json", WaterAnimal.class);
    }

    private static void loadFlyingAnimals() {
        Animal.allFlyingAnimals = FlyingAnimal.instantiateAnimalsFromJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/flyingAnimalData.json", FlyingAnimal.class);
    }
}
