package zoo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Controller {

    private static final String KEEPER_DATA = "src/zoo/data/zooKeeperData/keeperData.json";

    private static final String DRY_PEN_DATA = "src/zoo/data/penData/dryPensData.json";
    private static final String PETTING_PEN_DATA = "src/zoo/data/penData/pettingPensData.json";
    private static final String PART_DRY_WATER_PEN_DATA = "src/zoo/data/penData/partDryWaterPensData.json";
    private static final String AQUARIUM_DATA = "src/zoo/data/penData/aquariumsData.json";
    private static final String AVIARY_DATA = "src/zoo/data/penData/aviariesData.json";

    private static final String LAND_ANIMAL_DATA = "src/zoo/data/animalData/landAnimalData.json";
    private static final String PETTING_ANIMAL_DATA = "src/zoo/data/animalData/pettingAnimalData.json";
    private static final String AMPHIBIANS_DATA = "src/zoo/data/animalData/amphibiansData.json";
    private static final String WATER_ANIMAL_DATA = "src/zoo/data/animalData/waterAnimalData.json";
    private static final String FLYING_ANIMAL_DATA = "src/zoo/data/animalData/flyingAnimalData.json";
    private static final String INCOMPATIBLE_SPECIES_DATA = "src/zoo/data/animalData/incompatibleSpeciesData.json";

    public static void loadKeepers() {
        File file = new File(KEEPER_DATA);
        if (file.length() != 0) {
            ZooKeeper.setListOfAllZooKeepers(Data.instantiateKeepersFromJsonFile(KEEPER_DATA));
        } else {
            ZooKeeper hardip = createZooKeeper(Pen.PenType.DRY, Pen.PenType.AVIARY, "Hardip");
            ZooKeeper alex = createZooKeeper(Pen.PenType.AQUARIUM, Pen.PenType.PARTDRYWATER, "Alex");
            ZooKeeper farhad = createZooKeeper(Pen.PenType.AVIARY, Pen.PenType.AQUARIUM, "Farhad");
            ZooKeeper alan = createZooKeeper(Pen.PenType.DRY, Pen.PenType.PETTING, "Alan");
            Data.writeKeepersToJsonFile(KEEPER_DATA);
        }
    }

    private static ZooKeeper createZooKeeper(Pen.PenType type1, Pen.PenType type2, String keeperName) {
        Pen.PenType[] pensTrainedFor = {type1, type2};
        ArrayList<Pen.PenType> trainedForPensList = new ArrayList<>(Arrays.asList(pensTrainedFor));
        ArrayList<Integer> assignedPens = new ArrayList<>();
        return new ZooKeeper(keeperName, trainedForPensList, assignedPens);
    }

    public static void loadPens() {
        loadDryPens();
        loadPettingPens();
        loadDryWaterPens();
        loadAquariums();
        loadAviaries();
    }

    private static void loadDryPens() {
        Pen.setListOfAllDryPens(Data.instantiatePensFromJsonFile(DRY_PEN_DATA, DryPen.class));
    }

    private static void loadPettingPens() {
        Pen.setListOfAllPettingPens(Data.instantiatePensFromJsonFile(PETTING_PEN_DATA, PettingPen.class));
    }

    private static void loadDryWaterPens() {
        Pen.setListOfAllDryWaterPens(Data.instantiatePensFromJsonFile(PART_DRY_WATER_PEN_DATA, PartDryWaterPen.class));
    }

    private static void loadAquariums() {
        Pen.setListOfAllAquariums(Data.instantiatePensFromJsonFile(AQUARIUM_DATA, Aquarium.class));
    }

    private static void loadAviaries() {
        Pen.setListOfAllAviaries(Data.instantiatePensFromJsonFile(AVIARY_DATA, Aviary.class));
    }

    public static void loadAnimals() {
        loadLandAnimals();
        loadPettingAnimals();
        loadAmphibiousAnimals();
        loadWaterAnimals();
        loadFlyingAnimals();
    }

    private static void loadLandAnimals() {
        Animal.setAllLandAnimals(Data.instantiateAnimalsFromJsonFile(LAND_ANIMAL_DATA, LandAnimal.class));
    }

    private static void loadPettingAnimals() {
        Animal.setAllPettingAnimals(Data.instantiateAnimalsFromJsonFile(PETTING_ANIMAL_DATA, PettingAnimal.class));
    }

    private static void loadAmphibiousAnimals() {
        Animal.setAllAmphibiousAnimals(Data.instantiateAnimalsFromJsonFile(AMPHIBIANS_DATA, AmphibiousAnimal.class));
    }

    private static void loadWaterAnimals() {
        Animal.setAllWaterAnimals(Data.instantiateAnimalsFromJsonFile(WATER_ANIMAL_DATA, WaterAnimal.class));
    }

    private static void loadFlyingAnimals() {
        Animal.setAllFlyingAnimals(Data.instantiateAnimalsFromJsonFile(FLYING_ANIMAL_DATA, FlyingAnimal.class));
    }

    public static void loadIncompatibleSpeciesMap() {
        File file = new File(INCOMPATIBLE_SPECIES_DATA);
        if (file.length() != 0) {
            Animal.setIncompatibleSpeciesMap(Data.instantiateIncompatibleSpeciesFromJsonFile());
        } else {
            Animal.setIncompatibleSpeciesMap(new HashMap<>());
        }

    }

    public static void addNewPen(Scanner scanner) {
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
                createPen(penOption, scanner);
            } else if (penOption.equals("0")) {
                isNotValidOption = false;
            } else {
                System.out.println("That is not a valid option. Please enter in one of the numbered options.");
            }
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

    private static void createPen(String penOption, Scanner scanner) {
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
                Data.writeDryPensToFile();
                break;
            case "2"://Petting Pen
                pen = new PettingPen(name, length, width, temp, assignedKeeperList, animalsIdsInPen);
                Data.writePettingAnimalsToFile();
                break;
            case "3"://Part Dry, Part Water Pen
                System.out.print("Water depth (metres): ");
                height = Integer.parseInt(scanner.nextLine());
                System.out.print("Land area (m^2): ");
                int area = Integer.parseInt(scanner.nextLine());
                System.out.print("Water volume (m^3): ");
                volume = Integer.parseInt(scanner.nextLine());
                pen = new PartDryWaterPen(name, length, width, height, area, volume, temp, assignedKeeperList, animalsIdsInPen);
                Data.writePartDryWaterToFile();
                break;
            case "4"://Aquarium
                System.out.print("Height (m): ");
                height = Integer.parseInt(scanner.nextLine());
                pen = new Aquarium(name, length, width, height, temp, assignedKeeperList, animalsIdsInPen);
                Data.writeAquariumsToFile();
                break;
            default://Aviary
                System.out.print("Height (m): ");
                height = Integer.parseInt(scanner.nextLine());
                pen = new Aviary(name, length, width, height, temp, assignedKeeperList, animalsIdsInPen);
                Data.writeAviariesToFile();
                break;
        }
        for (ZooKeeper keeper : assignedKeeperList) {
            keeper.addPenToAssignedPens(pen.getPenId());
            Data.writeKeepersToJsonFile(KEEPER_DATA);
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
                keeper = ZooKeeper.getListOfAllZooKeepers().get(3);
                System.out.println(keeper.getName() + " will be the assigned keeper of this pen.");
                break;
            case "3"://Part Dry, Part Water Pen
                keeper = ZooKeeper.getListOfAllZooKeepers().get(1);
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
        int keeper1NumberOfPens = ZooKeeper.getListOfAllZooKeepers().get(keeper1).getAssignedPenIds().size();
        int keeper2NumberOfPens = ZooKeeper.getListOfAllZooKeepers().get(keeper2).getAssignedPenIds().size();
        return keeper1NumberOfPens > keeper2NumberOfPens ? ZooKeeper.getListOfAllZooKeepers().get(keeper2) : ZooKeeper.getListOfAllZooKeepers().get(keeper1);
    }

    public static void addNewAnimal(Scanner scanner) {
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
                createAnimal(animalOption, scanner);
            } else if (animalOption.equals("0")) {
                isNotValidOption = false;
            } else {
                System.out.println("That is not a valid option. Please enter in one of the numbered options.");
            }
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

    private static void createAnimal(String animalOption, Scanner scanner) {
        Animal animal;
        String name;
        String species;
        int assignedPenId;
        double landSpace;
        double waterSpace;
        System.out.println("Complete the following fields to add the animal:");
        System.out.print("Species: ");
        species = scanner.nextLine();
        if (!Animal.getDistinctSpeciesInZoo().contains(species)) {
            inputIncompatibleSpecies(species, scanner);
        }
        System.out.print("Name: ");
        name = scanner.nextLine();
        assignedPenId = -1;
        switch (animalOption) {
            case "1"://Land Animal
                System.out.print("Land space required: ");
                landSpace = Double.parseDouble(scanner.nextLine());
                animal = new LandAnimal(name, species, assignedPenId, landSpace);
                Data.writeDryPensToFile();
                Data.writePettingPensToFile();
                Data.writeLandAnimalsToFile();
                break;
            case "2"://Petting Animal
                System.out.print("Land space required: ");
                landSpace = Double.parseDouble(scanner.nextLine());
                animal = new PettingAnimal(name, species, assignedPenId, landSpace);
                Data.writeDryPensToFile();
                Data.writePettingPensToFile();
                Data.writePettingAnimalsToFile();
                break;
            case "3"://Amphibious Animal
                System.out.print("Land space required: ");
                landSpace = Double.parseDouble(scanner.nextLine());
                System.out.print("Water space required: ");
                waterSpace = Double.parseDouble(scanner.nextLine());
                animal = new AmphibiousAnimal(name, species, assignedPenId, landSpace, waterSpace);
                Data.writePartDryWaterToFile();
                Data.writeAmphibiousAnimalsToFile();
                break;
            case "4"://Water Animal
                System.out.print("Water space required: ");
                waterSpace = Double.parseDouble(scanner.nextLine());
                animal = new WaterAnimal(name, species, assignedPenId, waterSpace);
                Data.writeAquariumsToFile();
                Data.writeWaterAnimalsToFile();
                break;
            default://Flying Animal
                System.out.print("Air space required: ");
                double airSpace = Double.parseDouble(scanner.nextLine());
                animal = new FlyingAnimal(name, species, assignedPenId, airSpace);
                Data.writeAviariesToFile();
                Data.writeFlyingAnimalsToFile();
                break;
        }
        autoAssignAnimalToPen(animal);
        Data.writeDistinctSpeciesToJsonFile(Animal.getDistinctSpeciesInZoo());
    }

    private static void autoAssignAnimalToPen(Animal animal) {
        Pen.PenType penType;
        switch (animal.getType()) {
            case LAND:
                penType = Pen.PenType.DRY;
                break;
            case PETTABLE:
                penType = Pen.PenType.PETTING;
                break;
            case AMPHIBIOUS:
                penType = Pen.PenType.PARTDRYWATER;
                break;
            case WATER:
                penType = Pen.PenType.AQUARIUM;
                break;
            default:
                penType = Pen.PenType.AVIARY;
                break;
        }
        ArrayList<Pen> listOfAllPens = Pen.getListOfAllPens();
        if (!listOfAllPens.isEmpty()) {
            boolean assignSuccessful = false;
            int i = 0;
            while (!assignSuccessful && i < listOfAllPens.size()) {
                Pen pen = listOfAllPens.get(i);
                if (pen.getType().equals(penType)) {
                    assignSuccessful = animal.setAssignedPen(pen.getPenId());
                }
                i++;
            }
            if (!assignSuccessful) {
                System.out.println("There are no suitable pens in the zoo for this animal. Please add more pens!");
            }
        } else {
            System.out.println("There are no pens! Press 1 to add a new pen.");
        }
    }

    //TODO - why was dog assigned to cat twice?
    private static void inputIncompatibleSpecies(String species, Scanner scanner) {
        HashMap<String, ArrayList<String>> incompatibleSpeciesMap = Animal.getIncompatibleSpeciesMap();
        ArrayList<String> incompatibleSpeciesList = new ArrayList<>();
        System.out.println("Which of these species is this animal incompatible with:");
        displayDistinctSpeciesInZoo();
        String speciesOption = "-1";
        while (!speciesOption.equals("x")) {
            System.out.print("> ");
            speciesOption = scanner.nextLine();
            ArrayList<String> distinctSpeciesInZooList = Animal.getDistinctSpeciesInZoo();
            if (!speciesOption.equals("x") && Integer.parseInt(speciesOption) < distinctSpeciesInZooList.size() && Integer.parseInt(speciesOption) >= 0) {
                incompatibleSpeciesList.add(distinctSpeciesInZooList.get(Integer.parseInt(speciesOption)));
                System.out.println("Declare another incompatible species or type x to finish.");
                System.out.print("> ");
            }
        }
        incompatibleSpeciesMap.put(species, incompatibleSpeciesList);
        for (String incompatibleSpecies : incompatibleSpeciesList) {
            incompatibleSpeciesMap.get(incompatibleSpecies).add(species);
        }
        Animal.setIncompatibleSpeciesMap(incompatibleSpeciesMap);
        Data.writeIncompatibleSpeciesToJsonFile(Animal.getIncompatibleSpeciesMap());
    }

    private static void displayDistinctSpeciesInZoo() {
        int i;
        for (i = 0; i < Animal.getDistinctSpeciesInZoo().size(); i++) {
            System.out.println("[" + i + "] " + Animal.getDistinctSpeciesInZoo().get(i));
        }
        System.out.println("[x] Finish adding incompatible species");
    }

    public static void displayKeeperInfo() {
        for (int i = 0; i < ZooKeeper.getListOfAllZooKeepers().size(); i++) {
            ZooKeeper keeper = ZooKeeper.getListOfAllZooKeepers().get(i);
            System.out.println("[" + i + "] " + keeper.toString());
        }
    }

    public static void displayPenInfo() {
        if (!Pen.getListOfAllPens().isEmpty()) {
            for (int i = 0; i < Pen.getListOfAllPens().size(); i++) {
                Pen pen = Pen.getListOfAllPens().get(i);
                System.out.println("[" + pen.penId + "] " + pen.displayInfo());
            }
        } else {
            System.out.println("There are no pens! Press 1 to add a new pen.");
        }
    }

    public static void displayAnimalInfo() {
        if (!Animal.getAllAnimalsInZooList().isEmpty()) {
            for (int i = 0; i < Animal.getAllAnimalsInZooList().size(); i++) {
                Animal animal = Animal.getAnimalWithAnimalId(i);
                System.out.println("[" + i + "] " + animal.displayInfo());
            }
        } else {
            System.out.println("There are no animals! Press 2 to add a new animal.");
        }
    }

    public static void assignAnimalToPen(Scanner scanner) {
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
        writeToRelevantAnimalFile(animalId);
        writeToRelevantPenFile(penId);
    }

    private static void writeToRelevantAnimalFile(int animalId) {
        Animal animal = Animal.getAnimalWithAnimalId(animalId);
        String type = animal.getType().toString();
        if (type.equals("LAND")) {
            Data.writeLandAnimalsToFile();
        } else if (type.equals("PETTABLE")) {
            Data.writePettingAnimalsToFile();
        } else if (type.equals("AMPHIBIOUS")) {
            Data.writeAmphibiousAnimalsToFile();
        } else if (type.equals("WATER")) {
            Data.writeWaterAnimalsToFile();
        } else if (type.equals("FLYING")) {
            Data.writeFlyingAnimalsToFile();
        }
    }

    private static void writeToRelevantPenFile(int penId) {
        Pen pen = Pen.getPenWithPenId(penId);
        String type = pen.getType().toString();
        if (type.equals("DRY")) {
            Data.writeDryPensToFile();
        } else if (type.equals("PETTING")) {
            Data.writePettingPensToFile();
        } else if (type.equals("PARTDRYWATER")) {
            Data.writePartDryWaterToFile();
        } else if (type.equals("AQUARIUM")) {
            Data.writeAquariumsToFile();
        } else if (type.equals("AVIARY")) {
            Data.writeAviariesToFile();
        }
    }

    public static void assignKeeperToPen(Scanner scanner) {
        System.out.println("Which of these pens do you want to assign a zookeeper:");
        displayPenInfo();
        int penId = Integer.parseInt(scanner.nextLine());
        Pen pen = Pen.getPenWithPenId(penId);
        System.out.print("> ");
        boolean isValidKeeperName = false;
        String keeperName = "";
        System.out.println("Which of these zookeeper do you want to assign to this pen:");
        while (!isValidKeeperName) {
            ArrayList<String> suitableKeepersNames = displaySuitableKeepersForPen(pen.getType());
            System.out.print("> ");
            keeperName = scanner.nextLine();
            if (suitableKeepersNames.contains(keeperName)) {
                isValidKeeperName = true;
            } else {
                System.out.println("That is not a valid keeper name. Please select the name of a keeper from the list:");
            }
        }
        pen.assignZooKeeper(getKeeperWithName(keeperName));
        Data.writeKeepersToJsonFile(KEEPER_DATA);
        writeToRelevantPenFile(penId);

    }

    private static ArrayList<String> displaySuitableKeepersForPen(Pen.PenType penType) {
        ArrayList<String> suitableKeeperNames = new ArrayList<>();
        for (ZooKeeper keeper : ZooKeeper.getListOfAllZooKeepers()) {
            if (keeper.isTrainedFor(penType)) {
                suitableKeeperNames.add(keeper.getName());
                System.out.println(keeper.toString());
            }
        }
        return suitableKeeperNames;
    }

    private static ZooKeeper getKeeperWithName(String keeperName) {
        for (ZooKeeper keeper : ZooKeeper.getListOfAllZooKeepers()) {
            if (keeper.getName().equals(keeperName)) {
                return keeper;
            }
        }
        return null;
    }

    public static void updateAndPrintWeatherDisplay() {
        Weather weather = new Weather();
        Thread thread = new Thread(weather);
        thread.start();
    }

    public static ArrayList<Animal> getAnimalsWithoutPens() {
        return Animal.getAnimalsWithoutPens();
    }

    public static void printUnassignedAnimals() {
        ArrayList<Animal> unassignedAnimals = getAnimalsWithoutPens();
        for (Animal animal : unassignedAnimals) {
            System.out.println(animal.displayInfo());
        }
    }

    public static void loadDistinctSpeciesList() {
        Animal.setDistinctSpeciesInZoo(Data.instantiateDistinctSpeciesFromJsonFile());
    }
}
